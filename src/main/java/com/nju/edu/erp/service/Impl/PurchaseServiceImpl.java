package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.dao.ProductDao;
import com.nju.edu.erp.dao.PurchaseSheetDao;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.PurchaseSheetState;
import com.nju.edu.erp.model.po.CustomerPO;
import com.nju.edu.erp.model.po.ProductPO;
import com.nju.edu.erp.model.po.PurchaseSheetContentPO;
import com.nju.edu.erp.model.po.PurchaseSheetPO;
import com.nju.edu.erp.model.queryObject.PurchaseSheetQuery;
import com.nju.edu.erp.model.vo.ProductInfoVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetContentVO;
import com.nju.edu.erp.model.vo.purchase.PurchaseSheetVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormContentVO;
import com.nju.edu.erp.model.vo.warehouse.WarehouseInputFormVO;
import com.nju.edu.erp.service.ProductService;
import com.nju.edu.erp.service.PurchaseService;
import com.nju.edu.erp.service.WarehouseService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    PurchaseSheetDao purchaseSheetDao;

    ProductService productService;

    ProductDao productDao;

    CustomerDao customerDao;

    WarehouseService warehouseService;

    @Autowired
    public PurchaseServiceImpl(PurchaseSheetDao purchaseSheetDao, ProductService productService, CustomerDao customerDao, WarehouseService warehouseService, ProductDao productDao) {
        this.purchaseSheetDao = purchaseSheetDao;
        this.productService = productService;
        this.customerDao = customerDao;
        this.warehouseService = warehouseService;
        this.productDao = productDao;
    }

    /**
     * ???????????????
     *
     * @param purchaseSheetVO ?????????
     */
    @Override
    @Transactional
    public void makePurchaseSheet(UserVO userVO, PurchaseSheetVO purchaseSheetVO) {
        PurchaseSheetPO purchaseSheetPO = new PurchaseSheetPO();
        BeanUtils.copyProperties(purchaseSheetVO, purchaseSheetPO);
        // ?????????????????????????????????????????????
        purchaseSheetPO.setCreateTime(new Date());
        PurchaseSheetPO latest = purchaseSheetDao.getLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "JHD");
        purchaseSheetPO.setId(id);
        purchaseSheetPO.setState(PurchaseSheetState.PENDING_LEVEL_1);
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<PurchaseSheetContentPO> pContentPOList = new ArrayList<>();
        for (PurchaseSheetContentVO content : purchaseSheetVO.getPurchaseSheetContent()) {
            // ??????????????? ????????????????????????0
            assert (content.getQuantity() >= 0 && content.getUnitPrice().compareTo(BigDecimal.ZERO) >= 0) : "???????????????????????????0";

            PurchaseSheetContentPO pContentPO = new PurchaseSheetContentPO();
            BeanUtils.copyProperties(content, pContentPO);

            pContentPO.setPurchaseSheetId(id);
            BigDecimal unitPrice = pContentPO.getUnitPrice();
            if (unitPrice == null) {
                ProductPO product = productDao.findById(content.getPid());
                unitPrice = product.getPurchasePrice();
                pContentPO.setUnitPrice(unitPrice);
            }
            pContentPO.setTotalPrice(unitPrice.multiply(BigDecimal.valueOf(pContentPO.getQuantity())));
            pContentPOList.add(pContentPO);
            totalAmount = totalAmount.add(pContentPO.getTotalPrice());
        }
        purchaseSheetDao.saveBatch(pContentPOList);
        purchaseSheetPO.setTotalAmount(totalAmount);
        purchaseSheetDao.save(purchaseSheetPO);
    }

    /**
     * ???????????????????????????[?????????content??????](state == null ????????????????????????)
     *
     * @param state ???????????????
     * @return ?????????
     */
    @Override
    public List<PurchaseSheetVO> getPurchaseSheetByState(PurchaseSheetState state) {
        List<PurchaseSheetVO> res = new ArrayList<>();
        List<PurchaseSheetPO> all;
        all = purchaseSheetDao.findAll(PurchaseSheetQuery.builder().state(state).build());
        for (PurchaseSheetPO po : all) {
            PurchaseSheetVO vo = new PurchaseSheetVO();
            BeanUtils.copyProperties(po, vo);
            List<PurchaseSheetContentPO> alll = purchaseSheetDao.findContentByPurchaseSheetId(po.getId());
            List<PurchaseSheetContentVO> vos = new ArrayList<>();
            for (PurchaseSheetContentPO p : alll) {
                PurchaseSheetContentVO v = new PurchaseSheetContentVO();
                BeanUtils.copyProperties(p, v);
                vos.add(v);
            }
            vo.setPurchaseSheetContent(vos);
            res.add(vo);
        }
        return res;
    }

    /**
     * ???????????????id????????????(state == "???????????????"/"????????????"/"????????????")
     * ???controller?????????????????????
     *
     * @param purchaseSheetId ?????????id
     * @param state           ???????????????????????????
     */
    @Override
    @Transactional
    public void approval(String purchaseSheetId, BaseEnum state) {
        if (state.equals(PurchaseSheetState.FAILURE)) {
            PurchaseSheetPO purchaseSheet = purchaseSheetDao.findOneById(purchaseSheetId);
            if (purchaseSheet.getState() == PurchaseSheetState.SUCCESS) throw new RuntimeException("??????????????????");
            int effectLines = purchaseSheetDao.updateState(purchaseSheetId, state);
            if (effectLines == 0) throw new RuntimeException("??????????????????");
        } else {
            PurchaseSheetState prevState;
            if (state.equals(PurchaseSheetState.SUCCESS)) {
                prevState = PurchaseSheetState.PENDING_LEVEL_2;
            } else if (state.equals(PurchaseSheetState.PENDING_LEVEL_2)) {
                prevState = PurchaseSheetState.PENDING_LEVEL_1;
            } else {
                throw new RuntimeException("??????????????????");
            }
            int effectLines = purchaseSheetDao.updateStateV2(purchaseSheetId, prevState, state);
            if (effectLines == 0) throw new RuntimeException("??????????????????");
            if (state.equals(PurchaseSheetState.SUCCESS)) {
                // ??????????????????????????????
                // ??????purchaseSheetId???????????????content -> ????????????id?????????
                // ????????????id?????????????????????????????????recentPp
                List<PurchaseSheetContentPO> purchaseSheetContent = purchaseSheetDao.findContentByPurchaseSheetId(purchaseSheetId);
                List<WarehouseInputFormContentVO> warehouseInputFormContentVOS = new ArrayList<>();

                for (PurchaseSheetContentPO content : purchaseSheetContent) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setId(content.getPid());
                    productInfoVO.setRecentPp(content.getUnitPrice());
                    productService.updateProduct(productInfoVO);

                    WarehouseInputFormContentVO wiContentVO = new WarehouseInputFormContentVO();
                    wiContentVO.setPurchasePrice(content.getUnitPrice());
                    wiContentVO.setQuantity(content.getQuantity());
                    wiContentVO.setRemark(content.getRemark());
                    wiContentVO.setPid(content.getPid());
                    warehouseInputFormContentVOS.add(wiContentVO);
                }
                // ???????????????(??????????????????)
                // ???????????? payable
                PurchaseSheetPO purchaseSheet = purchaseSheetDao.findOneById(purchaseSheetId);
                CustomerPO customerPO = customerDao.findOneById(purchaseSheet.getSupplier());
                customerPO.setPayable(customerPO.getPayable().add(purchaseSheet.getTotalAmount()));
                customerDao.updateOne(customerPO);

                // ?????????????????????(????????????????????????)
                // ??????????????????????????????
                WarehouseInputFormVO warehouseInputFormVO = new WarehouseInputFormVO();
                warehouseInputFormVO.setOperator(purchaseSheet.getOperator()); // ?????????????????????(??????????????????????????????)
                warehouseInputFormVO.setPurchaseSheetId(purchaseSheetId);
                warehouseInputFormVO.setList(warehouseInputFormContentVOS);
                warehouseService.productWarehousing(warehouseInputFormVO);
            }
        }
    }

    /**
     * ???????????????Id?????????????????????
     *
     * @param purchaseSheetId ?????????Id
     * @return ?????????????????????
     */
    @Override
    public PurchaseSheetVO getPurchaseSheetById(String purchaseSheetId) {
        PurchaseSheetPO purchaseSheetPO = purchaseSheetDao.findOneById(purchaseSheetId);
        if (purchaseSheetPO == null) return null;
        List<PurchaseSheetContentPO> contentPO = purchaseSheetDao.findContentByPurchaseSheetId(purchaseSheetId);
        PurchaseSheetVO pVO = new PurchaseSheetVO();
        BeanUtils.copyProperties(purchaseSheetPO, pVO);
        List<PurchaseSheetContentVO> purchaseSheetContentVOList = new ArrayList<>();
        for (PurchaseSheetContentPO content :
                contentPO) {
            PurchaseSheetContentVO pContentVO = new PurchaseSheetContentVO();
            BeanUtils.copyProperties(content, pContentVO);
            purchaseSheetContentVOList.add(pContentVO);
        }
        pVO.setPurchaseSheetContent(purchaseSheetContentVOList);
        return pVO;
    }
}
