package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.CollectionDao;
import com.nju.edu.erp.dao.CompanyAccountDao;
import com.nju.edu.erp.dao.CustomerDao;
import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.CollectionSheetState;
import com.nju.edu.erp.model.po.CollectionSheetPO;
import com.nju.edu.erp.model.po.TransferListSheetPO;
import com.nju.edu.erp.model.vo.CollectionSheetVO;
import com.nju.edu.erp.model.vo.TransferListSheetVO;
import com.nju.edu.erp.model.vo.UserVO;
import com.nju.edu.erp.service.CollectionService;
import com.nju.edu.erp.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionDao collectionDao;

    private final CompanyAccountDao companyAccountDao;

    private final CustomerDao customerDao;

    @Autowired
    public CollectionServiceImpl(CollectionDao collectionDao, CompanyAccountDao companyAccountDao, CustomerDao customerDao) {
        this.collectionDao = collectionDao;
        this.companyAccountDao = companyAccountDao;
        this.customerDao = customerDao;
    }




    //TODO:制定收款单@hcx
    /**
     * 制定收款单
     * 注意需要保存收款单据和转账列表
     * 注意还需要写防御式编程
     * @param collectionSheetVO 收款单VO
     */
    @Override
    public void makeCollectionSheet(CollectionSheetVO collectionSheetVO) {
        // 需要保存到数据库
        CollectionSheetPO collectionSheetPO=new CollectionSheetPO();
        BeanUtils.copyProperties(collectionSheetVO,collectionSheetPO);
        CollectionSheetPO latest=collectionDao.findLatest();
        String id = IdGenerator.generateSheetId(latest == null ? null : latest.getId(), "SKD");
        collectionSheetPO.setId(id);
        collectionSheetPO.setCreateTime(new Date());
        collectionSheetPO.setState(CollectionSheetState.PENDING);
        List<TransferListSheetPO> collectionSheetContentPOS=new ArrayList<>();
        // 收款单的内容
        List<TransferListSheetVO> collectionSheetContentVOS=collectionSheetVO.getCollectionContent();
        BigDecimal totalAmount= BigDecimal.valueOf(0);
        for(TransferListSheetVO collectionContentVO:collectionSheetContentVOS){
            TransferListSheetPO collectionContentPO=new TransferListSheetPO();
            BeanUtils.copyProperties(collectionContentVO,collectionContentPO);
            collectionContentPO.setCollectionSheetId(id);
            // 加上每一个的转账的钱
            totalAmount=totalAmount.add(collectionContentVO.getTransferAmount());
            collectionSheetContentPOS.add(collectionContentPO);
        }
        // 设置总金额 其他的都copy过了
        collectionSheetPO.setTotalAmount(totalAmount);
        collectionDao.saveCollectionSheetSheet(collectionSheetPO);
        collectionDao.saveTransferList(collectionSheetContentPOS);

    }

    //TODO:审批收款单@hcx
    /**
     * 审批收款单(为了简化业务,这里只由总经理审批)
     *
     * 审批完成之后需要修改对应客户的应付数据和对应的银行账户数据
     * @param collectionSheetId 收款单id
     * @param state 收款单状态 (state == "审批完成"/"审批失败")
     */
    @Override
    public void approval(String collectionSheetId, BaseEnum state) {
        // 得到对应的收款单
        CollectionSheetPO collectionSheetPO=collectionDao.findCollectionSheetById(collectionSheetId);
        Integer customerId=collectionSheetPO.getCustomer();
        if(state.equals(CollectionSheetState.FAILURE)){
            if(collectionSheetPO.getState().equals(CollectionSheetState.SUCCESS)){
                throw new RuntimeException("状态更新失败");
            }
        }
        int effectedLine=collectionDao.updateState(collectionSheetId,state);
        if(effectedLine==0){
            throw new RuntimeException("状态更新失败");
        }
        // 修改对应客户的应付数据和对应的银行账户数据
        if(state.equals(CollectionSheetState.SUCCESS)){
            // 得到当前收款单的内容集合(有多个转账列表)
            List<TransferListSheetPO> collectionSheetContentPOS=collectionDao.findAllCollectionSheetContent(collectionSheetId);
            // 根据每个内容更新对应的银行账户和客户
            for(TransferListSheetPO collectionSheetContentPO:collectionSheetContentPOS){
                companyAccountDao.collectionUpdateCompanyAccountAmountById(collectionSheetContentPO.getCompanyAccountId(),collectionSheetContentPO.getTransferAmount());
                customerDao.updatePayableById(customerId,collectionSheetContentPO.getTransferAmount());
            }
        }
    }

    /**
     * 按照状态查找收款单
     * @param state 对应的状态(可以为空)
     * @return 所有的满足条件的收款单
     */
    @Override
    public List<CollectionSheetVO> findAllCollectionSheetByState(CollectionSheetState state) {
        List<CollectionSheetPO> collectionSheetPOS;
        List<CollectionSheetVO> collectionSheetVOS=new ArrayList<>();
        if(state==null){
            collectionSheetPOS=collectionDao.findAllCollectionSheet();
        }else {
            collectionSheetPOS=collectionDao.findAllCollectionSheetByState(state);
        }
        for(CollectionSheetPO collectionSheetPO:collectionSheetPOS){
            CollectionSheetVO collectionSheetVO=new CollectionSheetVO();
            BeanUtils.copyProperties(collectionSheetPO,collectionSheetVO);
            List<TransferListSheetPO> collectionSheetContentPOS=collectionDao.findAllCollectionSheetContent(collectionSheetPO.getId());
            System.out.println(collectionSheetContentPOS.get(0));
            List<TransferListSheetVO> collectionSheetContentVOS=new ArrayList<>();
            for(TransferListSheetPO collectionSheetContentPO:collectionSheetContentPOS){
                TransferListSheetVO transferListSheetVO=new TransferListSheetVO();
                BeanUtils.copyProperties(collectionSheetContentPO,transferListSheetVO);
                System.out.println(transferListSheetVO);
                collectionSheetContentVOS.add(transferListSheetVO);
            }
            collectionSheetVO.setCollectionContent(collectionSheetContentVOS);
            collectionSheetVOS.add(collectionSheetVO);
        }
        return collectionSheetVOS;
    }
}
