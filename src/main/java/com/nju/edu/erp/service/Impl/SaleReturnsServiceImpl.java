package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.SaleReturnsSheetDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.dao.WarehouseDao;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetContentVO;
import com.nju.edu.erp.model.vo.saleReturns.SaleReturnsSheetVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.ProductService;
import com.nju.edu.erp.service.SaleReturnsService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SaleReturnsServiceImpl implements SaleReturnsService {
    SaleReturnsSheetDao srsDao;

    SaleSheetDao ssDao;

    ProductService productService;

    WarehouseDao warehouseDao;

    CustomerService customerService;


    @Autowired
    SaleReturnsServiceImpl (SaleReturnsSheetDao srsDao, SaleSheetDao ssDao, ProductService productService, WarehouseDao warehouseDao, CustomerService customerService) {
        this.srsDao = srsDao;
        this.ssDao = ssDao;
        this.productService = productService;
        this.warehouseDao = warehouseDao;
        this.customerService = customerService;
    }

    // TODO : 处理优惠券相关逻辑 目前采取的方法是退 总价*折扣 优惠券金额不考虑 这样会多退钱

    /**
     * 制定销售退货单<br>
     * 需要设置新id 操作人员 状态 创建时间<br>
     * 具体销售退货单内容中的单价设定和总金额设定需要结合对应销售单中的商品单价和商品数量<br>
     * 具体优惠券金额该如何处理还未确定
     * @param userVO             操作人员
     * @param srsVO 销售退货单
     */
    @Override
    @Transactional
    public void makeSaleReturnSheet(UserVO userVO, SaleReturnsSheetVO srsVO) {
        SaleReturnsSheetPO srsPO = new SaleReturnsSheetPO();
        BeanUtils.copyProperties(srsVO, srsPO);
        // Get prev id and generate new id
        SaleReturnsSheetPO latest = srsDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSTHD");
        srsPO.setId(id);
        srsPO.setOperator(userVO.getName());
        srsPO.setState(SaleReturnsSheetState.PENDING_LEVEL_1);
        srsPO.setCreateTime(new Date());

        // 设置销售退货单的折扣
        SaleSheetPO ssPO = ssDao.findSheetById(srsPO.getSaleSheetId());
        srsPO.setDiscount(ssPO.getDiscount());
        // 获取销售单内容
        List<SaleSheetContentPO> sscPOList = ssDao.findContentBySheetId(srsPO.getSaleSheetId());
        // 创建新销售退货单内容POList 以及获取 传入的销售退货单内容VOList
        List<SaleReturnsSheetContentPO> srscPOList = new ArrayList<>();
        List<SaleReturnsSheetContentVO> srscVOLIst = srsVO.getSaleReturnsSheetContent();
        Map<String, SaleSheetContentPO> map = new HashMap<>();
        // 建立商品 pid 与 sscPO 的 Map 为了获取每个商品准确的单价
        for (SaleSheetContentPO item : sscPOList) {
            map.put(item.getPid(), item);
        }
        // 挨个创建 srscPO
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (SaleReturnsSheetContentVO srscVO : srscVOLIst) {
            SaleReturnsSheetContentPO srscPO = new SaleReturnsSheetContentPO();
            // 这里传进来的的 srscVO 中可能单价和总金额不一定对，需要参考 sscPO
            // 传进来的数量不能改，退多少货需要参考这个
            BeanUtils.copyProperties(srscVO, srscPO);
            srscPO.setSaleReturnsSheetId(id);
            SaleSheetContentPO sscPO = map.get(srscPO.getPid());
            // 获取ssc中的单价
            BigDecimal unitPrice = sscPO.getUnitPrice();
            BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(srscPO.getQuantity()));
            srscPO.setUnitPrice(unitPrice);
            srscPO.setTotalPrice(totalPrice);
            srscPOList.add(srscPO);
            totalAmount = totalAmount.add(totalPrice);
        }
        srsPO.setRawTotalAmount(totalAmount);
        srsPO.setFinalAmount(totalAmount.multiply(srsPO.getDiscount()));
        srsDao.save(srsPO);
        srsDao.saveBatch(srscPOList);
    }

    /**
     * 根据销售退货单状态进行审批<br>
     * (state == "待二级审批"/"审批完成"/"审批失败") 在controller层进行权限控制<br>
     * 审批完成后不创建入库单，而是直接修改仓库和商品数量
     *
     * @param saleReturnsSheetId 销售退货单ID
     * @param state              销售退货单状态
     */
    @Override
    @Transactional
    public void approval(String saleReturnsSheetId, SaleReturnsSheetState state) {
        SaleReturnsSheetPO srsPO = srsDao.findOneById(saleReturnsSheetId);
        if (state.equals(SaleReturnsSheetState.FAILURE)) {
            if (srsPO.getState().equals(SaleReturnsSheetState.SUCCESS)) {
                throw new RuntimeException("状态更新失败");
            }
            int effectLine = srsDao.updateState(saleReturnsSheetId, state);
            if (effectLine == 0) throw new RuntimeException("状态更新失败");
        } else {
            SaleReturnsSheetState prevState;
            if (state.equals(SaleReturnsSheetState.SUCCESS)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_2;
            } else if (state.equals(SaleReturnsSheetState.PENDING_LEVEL_2)) {
                prevState = SaleReturnsSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }
            int effectLines = srsDao.updateStateV2(saleReturnsSheetId, prevState, state);
            if (effectLines == 0) throw new RuntimeException("状态更新失败");
            if (state.equals(SaleReturnsSheetState.SUCCESS)) {
                List<SaleReturnsSheetContentPO> srscPOList = srsDao.findContentBySaleReturnsSheetId(saleReturnsSheetId);
                BigDecimal payableToAdd = BigDecimal.ZERO;

                for (SaleReturnsSheetContentPO srscPO : srscPOList) {
                    String pid = srscPO.getPid();
                    Integer quantity = srscPO.getQuantity();
                    // 找到出货时对应的商品信息（主要包括批次）
                    List<WarehouseOutputSheetContentPO> woscPOList = srsDao.findBatchBySaleSheetIdAndPId(srsPO.getSaleSheetId(), srscPO.getPid());
                    // 按照价格从低到高返回不同批次的商品
                    for (WarehouseOutputSheetContentPO woscPO : woscPOList) {
                        Integer batchQuantity = woscPO.getQuantity();
                        Integer returnQuantity = Integer.min(quantity, batchQuantity);
                        Integer batchId = woscPO.getBatchId();
                        WarehousePO warehousePO = warehouseDao.findOneByPidAndBatchId(pid, batchId);
                        if (warehousePO == null) throw new RuntimeException("单据发生错误！请联系管理员");
                        // 增加仓库商品数量，这方法有点迷惑
                        warehousePO.setQuantity(returnQuantity);
                        warehouseDao.addQuantity(warehousePO);
                        // 增加产品数量
                        ProductInfoVO productInfoVO = productService.getOneProductByPid(pid);
                        productInfoVO.setQuantity(productInfoVO.getQuantity() + returnQuantity);
                        productService.updateProduct(productInfoVO);
                        // 单价乘数量乘折扣
                        payableToAdd = payableToAdd.add(srscPO.getUnitPrice().multiply(BigDecimal.valueOf(returnQuantity)).multiply(srsPO.getDiscount()));

                        quantity -= returnQuantity;
                        if (quantity <= 0) break;
                    }
                }

                SaleSheetPO saleSheetPO = ssDao.findSheetById(srsPO.getSaleSheetId());
                Integer supplier = saleSheetPO.getSupplier();
                CustomerPO customerPO = customerService.findCustomerById(supplier);

                customerPO.setPayable(customerPO.getPayable().add(payableToAdd));
                customerService.updateCustomer(customerPO);
            }
        }
    }

    /**
     * 根据状态获取销售退货单
     * 若 state == null 则获取所有销售退货单
     *
     * @param state 需要查询的销售退货单状态
     * @return 满足条件的销售退货单
     */
    @Override
    public List<SaleReturnsSheetVO> getSaleReturnsSheetByState(SaleReturnsSheetState state) {
        List<SaleReturnsSheetVO> srsVOList = new ArrayList<>();
        List<SaleReturnsSheetPO> srsPOList;
        if (state == null) {
            srsPOList = srsDao.findAll();
        } else {
            srsPOList = srsDao.findAllByState(state);
        }
        for (SaleReturnsSheetPO srsPO : srsPOList) {
            SaleReturnsSheetVO srsVO = new SaleReturnsSheetVO();
            BeanUtils.copyProperties(srsPO, srsVO);
            List<SaleReturnsSheetContentPO> srscPOList = srsDao.findContentBySaleReturnsSheetId(srsPO.getId());
            List<SaleReturnsSheetContentVO> srscVOList = new ArrayList<>();
            for (SaleReturnsSheetContentPO srscPO : srscPOList) {
                SaleReturnsSheetContentVO srscVO = new SaleReturnsSheetContentVO();
                BeanUtils.copyProperties(srscPO, srscVO);
                srscVOList.add(srscVO);
            }
            srsVO.setSaleReturnsSheetContent(srscVOList);
            srsVOList.add(srsVO);
        }
        return srsVOList;
    }
}
