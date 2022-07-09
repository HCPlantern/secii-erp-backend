package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.*;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.queryObject.SaleReturnSheetQuery;
import com.nju.edu.erp.model.queryObject.SaleSheetQuery;
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
import java.math.RoundingMode;
import java.util.*;

@Service
public class SaleReturnsServiceImpl implements SaleReturnsService {
    SaleReturnsSheetDao srsDao;

    SaleSheetDao ssDao;

    WarehouseOutputSheetDao wosDao;

    ProductService productService;

    WarehouseDao warehouseDao;

    CustomerService customerService;

    CustomerDao customerDao;


    @Autowired
    SaleReturnsServiceImpl(SaleReturnsSheetDao srsDao, SaleSheetDao ssDao, WarehouseOutputSheetDao wosDao, ProductService productService, WarehouseDao warehouseDao, CustomerService customerService, CustomerDao customerDao) {
        this.srsDao = srsDao;
        this.ssDao = ssDao;
        this.wosDao = wosDao;
        this.productService = productService;
        this.warehouseDao = warehouseDao;
        this.customerService = customerService;
        this.customerDao = customerDao;
    }

    /**
     * 制定销售退货单<br>
     * 需要设置新id 操作人员 状态 创建时间<br>
     * 具体销售退货单内容中的单价设定和总金额设定需要结合对应销售单中的商品单价和商品数量<br>
     * 因为前端VO传进来的销售退货单内容不一定可靠，需要这里再查询销售单<br>
     * 优惠券部分，因为支持部分退货，所以优惠券也要按照比例考虑
     *
     * @param userVO 操作人员
     * @param srsVO  销售退货单
     */
    @Override
    @Transactional
    public void makeSaleReturnSheet(UserVO userVO, SaleReturnsSheetVO srsVO) {
        SaleReturnsSheetPO srsPO = new SaleReturnsSheetPO();
        BeanUtils.copyProperties(srsVO, srsPO);

        System.out.println(srsPO);


        // Get prev id and generate new id
        SaleReturnsSheetPO latest = srsDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSTHD");
        srsPO.setId(id);
        srsPO.setState(SaleReturnsSheetState.PENDING_LEVEL_1);
        srsPO.setCreateTime(new Date());

        System.out.println(srsPO);

        // 设置销售退货单的折扣和客户
        SaleSheetPO ssPO = ssDao.findSheetById(srsPO.getSaleSheetId());
        srsPO.setSupplier(ssPO.getSupplier());
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
        System.out.println(map.toString());
        // 挨个创建 srscPO
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (SaleReturnsSheetContentVO srscVO : srscVOLIst) {
            assert (srscVO.getQuantity() >= 0 && srscVO.getUnitPrice().compareTo(BigDecimal.ZERO) >= 0) : "销售退货单的数量和单价必须大于等于0";
            SaleReturnsSheetContentPO srscPO = new SaleReturnsSheetContentPO();
            // 这里传进来的的 srscVO 中可能单价和总金额不一定对，需要参考 sscPO
            // 传进来的数量不能改，退多少货需要参考这个
            BeanUtils.copyProperties(srscVO, srscPO);
            srscPO.setSaleReturnsSheetId(id);
            System.out.println(srscPO.getPid());
            SaleSheetContentPO sscPO = map.get(srscPO.getPid());
            System.out.println(sscPO);
            // 获取ssc中的单价
            BigDecimal unitPrice = sscPO.getUnitPrice();
            BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(srscPO.getQuantity()));
            srscPO.setUnitPrice(unitPrice);
            srscPO.setTotalPrice(totalPrice);
            srscPOList.add(srscPO);
            totalAmount = totalAmount.add(totalPrice);
        }
        srsPO.setRawTotalAmount(totalAmount);
        // 计算该退货单的商品在销售时占销售单所使用优惠券的金额大小
        srsPO.setVoucherAmount(ssPO.getVoucherAmount().multiply(srsPO.getRawTotalAmount().divide(ssPO.getRawTotalAmount(), 6, RoundingMode.HALF_DOWN)));
        srsPO.setFinalAmount(totalAmount.multiply(srsPO.getDiscount()).subtract(srsPO.getVoucherAmount()));
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
    public void approval(String saleReturnsSheetId, BaseEnum state) {
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
                for (SaleReturnsSheetContentPO srscPO : srscPOList) {
                    String pid = srscPO.getPid();
                    Integer quantity = srscPO.getQuantity();
                    // 找到出货时对应的商品信息（主要包括批次）
                    List<WarehouseOutputSheetContentPO> woscPOList = wosDao.findBatchBySaleSheetIdAndPId(srsPO.getSaleSheetId(), srscPO.getPid());
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

                        quantity -= returnQuantity;
                        if (quantity <= 0) break;
                    }
                }

                SaleSheetPO saleSheetPO = ssDao.findSheetById(srsPO.getSaleSheetId());
                Integer supplier = saleSheetPO.getSupplier();
                CustomerPO customerPO = customerService.findCustomerById(supplier);

                customerPO.setPayable(customerPO.getPayable().add(srsPO.getFinalAmount()));
                customerDao.updateOne(customerPO);
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
        srsPOList = srsDao.findAll(SaleReturnSheetQuery.builder().state(state).build());
        for (SaleReturnsSheetPO srsPO : srsPOList) {
            SaleReturnsSheetVO srsVO = new SaleReturnsSheetVO();
            setVODetail(srsPO, srsVO);
            srsVOList.add(srsVO);
        }
        return srsVOList;
    }


    public SaleReturnsSheetVO getSaleReturnsSheetById(String id) {
        SaleReturnsSheetPO srsPO = srsDao.findOneById(id);
        if (srsPO == null) return null;
        SaleReturnsSheetVO srsVO = new SaleReturnsSheetVO();
        setVODetail(srsPO, srsVO);
        return srsVO;
    }

    private void setVODetail(SaleReturnsSheetPO srsPO, SaleReturnsSheetVO srsVO) {
        BeanUtils.copyProperties(srsPO, srsVO);
        List<SaleReturnsSheetContentPO> srscPOList = srsDao.findContentBySaleReturnsSheetId(srsPO.getId());
        List<SaleReturnsSheetContentVO> srscVOList = new ArrayList<>();
        for (SaleReturnsSheetContentPO srscPO : srscPOList) {
            SaleReturnsSheetContentVO srscVO = new SaleReturnsSheetContentVO();
            BeanUtils.copyProperties(srscPO, srscVO);
            srscVOList.add(srscVO);
        }
        srsVO.setSaleReturnsSheetContent(srscVOList);
    }
}
