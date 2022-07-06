package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.dao.SaleSheetDao;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.SaleSheetState;
import com.nju.edu.erp.model.po.*;
import com.nju.edu.erp.model.queryObject.SaleSheetQuery;
import com.nju.edu.erp.model.vo.CustomerPurchaseAmountVO;
import com.nju.edu.erp.model.vo.CustomerVO;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.sale.SaleDetailVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetContentVO;
import com.nju.edu.erp.model.vo.sale.SaleSheetVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormContentVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseOutputFormVO;
import com.nju.edu.erp.service.CustomerService;
import com.nju.edu.erp.service.ProductService;
import com.nju.edu.erp.service.SaleService;
import com.nju.edu.erp.service.WarehouseService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleSheetDao saleSheetDao;

    private final CustomerDao customerDao;

    private final ProductService productService;

    private final CustomerService customerService;

    private final WarehouseService warehouseService;

    @Autowired
    public SaleServiceImpl(SaleSheetDao saleSheetDao, CustomerDao customerDao, ProductService productService, CustomerService customerService, WarehouseService warehouseService) {
        this.saleSheetDao = saleSheetDao;
        this.customerDao = customerDao;
        this.productService = productService;
        this.customerService = customerService;
        this.warehouseService = warehouseService;
    }

    @Override
    @Transactional
    public void makeSaleSheet(UserVO userVO, SaleSheetVO saleSheetVO) {
        // TODO
        // 需要持久化销售单（SaleSheet）和销售单content（SaleSheetContent），其中总价或者折后价格的计算需要在后端进行
        // 需要的service和dao层相关方法均已提供，可以不用自己再实现一遍
        SaleSheetPO saleSheetPO = new SaleSheetPO();
        BeanUtils.copyProperties(saleSheetVO, saleSheetPO);
        // 设置生成时间
        saleSheetPO.setCreateTime(new Date());
        // 设置状态(等待一级审批)
        saleSheetPO.setState(SaleSheetState.PENDING_LEVEL_1);
        // 设置Id
        SaleSheetPO latest = saleSheetDao.getLatestSheet();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "XSD");
        saleSheetPO.setId(id);
        // 得到销售内容
        List<SaleSheetContentVO> saleSheetContentVOS = saleSheetVO.getSaleSheetContent();
        BigDecimal rawTotalAmount = BigDecimal.ZERO;
        List<SaleSheetContentPO> saleSheetContentPOS = new ArrayList<>();
        for (SaleSheetContentVO saleSheetContentVO : saleSheetContentVOS) {
            // 防御式编程
            assert saleSheetContentVO.getQuantity() >= 0 && saleSheetContentVO.getUnitPrice().compareTo(BigDecimal.ZERO) >= 0 : "销售单内容的数量和单价必须大于等于0";
            SaleSheetContentPO saleSheetContentPO = new SaleSheetContentPO();
            BeanUtils.copyProperties(saleSheetContentVO, saleSheetContentPO);
            saleSheetContentPO.setSaleSheetId(id);
            saleSheetContentPO.setTotalPrice(saleSheetContentVO.getUnitPrice().multiply(BigDecimal.valueOf(saleSheetContentVO.getQuantity())));
            rawTotalAmount = rawTotalAmount.add(saleSheetContentPO.getTotalPrice());
            saleSheetContentPOS.add(saleSheetContentPO);
        }
        saleSheetPO.setRawTotalAmount(rawTotalAmount);
        BigDecimal finalAmount = saleSheetPO.getRawTotalAmount().multiply(saleSheetPO.getDiscount()).subtract(saleSheetPO.getVoucherAmount());
        saleSheetPO.setFinalAmount(finalAmount);
        // 保存
        saleSheetDao.saveBatchSheetContent(saleSheetContentPOS);
        saleSheetDao.saveSheet(saleSheetPO);
    }

    @Override
    @Transactional
    public List<SaleSheetVO> getSaleSheetByState(SaleSheetState state) {
        // TODO
        // 根据单据状态获取销售单（注意：VO包含SaleSheetContent）
        // 依赖的dao层部分方法未提供，需要自己实现
        List<SaleSheetVO> res = new ArrayList<>();
        List<SaleSheetPO> all;
        all = saleSheetDao.findAllSheet(SaleSheetQuery.builder().state(state).build());
        for (SaleSheetPO saleSheetPO : all) {
            SaleSheetVO saleSheetVO = new SaleSheetVO();
            BeanUtils.copyProperties(saleSheetPO, saleSheetVO);
            List<SaleSheetContentPO> saleSheetContentPOS = saleSheetDao.findContentBySheetId(saleSheetPO.getId());
            List<SaleSheetContentVO> saleSheetContentVOS = new ArrayList<>();
            for (SaleSheetContentPO saleSheetContentPO : saleSheetContentPOS) {
                SaleSheetContentVO saleSheetContentVO = new SaleSheetContentVO();
                BeanUtils.copyProperties(saleSheetContentPO, saleSheetContentVO);
                saleSheetContentVOS.add(saleSheetContentVO);
            }
            saleSheetVO.setSaleSheetContent(saleSheetContentVOS);
            res.add(saleSheetVO);
        }
        return res;
    }

    /**
     * 根据销售单id进行审批(state == "待二级审批"/"审批完成"/"审批失败")
     * 在controller层进行权限控制
     *
     * @param saleSheetId 销售单id
     * @param state       销售单要达到的状态
     */
    @Override
    @Transactional
    public void approval(String saleSheetId, BaseEnum state) {
        // TODO
        // 需要的service和dao层相关方法均已提供，可以不用自己再实现一遍
        /* 一些注意点：
            1. 二级审批成功之后需要进行
                 1. 修改单据状态
                 2. 更新商品表
                 3. 更新客户表
                 4. 新建出库草稿
            2. 一级审批状态不能直接到审批完成状态； 二级审批状态不能回到一级审批状态
         */
        if (state.equals(SaleSheetState.FAILURE)) {
            SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
            if (saleSheetPO.getState() == SaleSheetState.SUCCESS) {
                throw new RuntimeException("状态更新失败");
            }
            int effectLines = saleSheetDao.updateSheetState(saleSheetId, state);
            if (effectLines == 0) {
                throw new RuntimeException("状态更新失败");
            }
        } else {
            SaleSheetState prevState;
            if (state.equals(SaleSheetState.SUCCESS)) {
                prevState = SaleSheetState.PENDING_LEVEL_2;
            } else if (state.equals(SaleSheetState.PENDING_LEVEL_2)) {
                prevState = SaleSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("状态更新失败");
            }
            int effectiveLines = saleSheetDao.updateSheetStateOnPrev(saleSheetId, prevState, state);
            if (effectiveLines == 0) {
                throw new RuntimeException("状态更新失败");
            }
            // 更新2级审批
            if (state.equals(SaleSheetState.SUCCESS)) {
//                更新商品列表和出库草稿
//                首先得到内容
                List<SaleSheetContentPO> saleSheetContentPOS = saleSheetDao.findContentBySheetId(saleSheetId);
                List<WarehouseOutputFormContentVO> warehouseOutputFormContentVOS = new ArrayList<>();
                for (SaleSheetContentPO saleSheetContentPO : saleSheetContentPOS) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setId(saleSheetContentPO.getPid());
                    productInfoVO.setRecentRp(saleSheetContentPO.getUnitPrice());
                    productService.updateProduct(productInfoVO);

                    WarehouseOutputFormContentVO wOFormContentVO = new WarehouseOutputFormContentVO();
                    wOFormContentVO.setPid(saleSheetContentPO.getPid());
                    wOFormContentVO.setQuantity(saleSheetContentPO.getQuantity());
                    wOFormContentVO.setSalePrice(saleSheetContentPO.getUnitPrice());
                    wOFormContentVO.setRemark(saleSheetContentPO.getRemark());

                    warehouseOutputFormContentVOS.add(wOFormContentVO);
                }
//                更新用户
                SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
                CustomerPO customerPO = customerService.findCustomerById(saleSheetPO.getSupplier());
                customerPO.setReceivable(customerPO.getReceivable().add(saleSheetPO.getFinalAmount()));
                customerDao.updateOne(customerPO);

//                新建出库草稿
                WarehouseOutputFormVO warehouseOutputFormVO = new WarehouseOutputFormVO();
                warehouseOutputFormVO.setList(warehouseOutputFormContentVOS);
                warehouseOutputFormVO.setOperator(saleSheetPO.getOperator());
                warehouseOutputFormVO.setSaleSheetId(saleSheetId);
                warehouseService.productOutOfWarehouse(warehouseOutputFormVO);
            }
        }
    }

    /**
     * 获取某个销售人员某段时间内消费总金额最大的客户(不考虑退货情况,销售单不需要审批通过,如果这样的客户有多个，仅保留一个)
     *
     * @param salesman     销售人员的名字
     * @param beginDateStr 开始时间字符串
     * @param endDateStr   结束时间字符串
     * @return 客户
     */
    public CustomerPurchaseAmountVO getMaxAmountCustomerOfSalesmanByTime(String salesman, String beginDateStr, String endDateStr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date beginTime = dateFormat.parse(beginDateStr);
            Date endTime = dateFormat.parse(endDateStr);
            if (beginTime.compareTo(endTime) > 0) {
                return null;
            } else {
                CustomerPurchaseAmountPO po = saleSheetDao.getMaxAmountCustomerOfSalesmanByTime(salesman, beginTime, endTime);
                if (po == null) {
                    return null;
                }
                CustomerPO customerPO = po.getCustomerPO();
                CustomerPurchaseAmountVO vo = new CustomerPurchaseAmountVO();
                CustomerVO customerVO = new CustomerVO();

                BeanUtils.copyProperties(customerPO, customerVO);
                customerVO.setType(customerPO.getType());
                vo.setCustomerVO(customerVO);
                vo.setTotalFinalAmount(po.getTotalFinalAmount());
                return vo;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据销售单Id搜索销售单信息
     *
     * @param saleSheetId 销售单Id
     * @return 销售单全部信息
     */
    @Override
    public SaleSheetVO getSaleSheetById(String saleSheetId) {
        SaleSheetPO saleSheetPO = saleSheetDao.findSheetById(saleSheetId);
        if (saleSheetPO == null) return null;
        List<SaleSheetContentPO> contentPO = saleSheetDao.findContentBySheetId(saleSheetId);
        SaleSheetVO sVO = new SaleSheetVO();
        BeanUtils.copyProperties(saleSheetPO, sVO);
        List<SaleSheetContentVO> saleSheetContentVOList = new ArrayList<>();
        for (SaleSheetContentPO content :
                contentPO) {
            SaleSheetContentVO sContentVO = new SaleSheetContentVO();
            BeanUtils.copyProperties(content, sContentVO);
            saleSheetContentVOList.add(sContentVO);
        }
        sVO.setSaleSheetContent(saleSheetContentVOList);
        return sVO;
    }

    /**
     * 根据时间段搜索销售详细信息
     *
     * @param beginDateStr 开始时间字符串
     * @param endDateStr   结束时间字符串
     * @return 销售详细信息
     */
    @Override
    public List<SaleDetailVO> findAllSaleDetailByTime(String beginDateStr, String endDateStr) {
        List<SaleDetailVO> result = new ArrayList<>();
        // find all sale detail
        List<SaleDetailPO> saleDetailPOList = saleSheetDao.findAllSaleDetailByTime(beginDateStr, endDateStr);
        for (SaleDetailPO saleDetailPO :
                saleDetailPOList) {
            SaleDetailVO saleDetailVO = new SaleDetailVO();
            BeanUtils.copyProperties(saleDetailPO, saleDetailVO);
            result.add(saleDetailVO);
        }
        return result;
    }


}
