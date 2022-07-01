package com.nju.edu.erp.dao;

import com.nju.edu.erp.enums.BaseEnum;
import com.nju.edu.erp.enums.sheetState.SaleReturnsSheetState;
import com.nju.edu.erp.model.po.SaleReturnsSheetContentPO;
import com.nju.edu.erp.model.po.SaleReturnsSheetPO;
import com.nju.edu.erp.model.po.SheetPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SaleReturnsSheetDao extends Dao {
    /**
     * 获取最近一条销售退货单
     *
     * @return 最近一条销售退货单
     */
    SaleReturnsSheetPO getLatest();

    /**
     * 存入一条销售退货单记录
     *
     * @param toSave 一条销售退货单记录
     * @return 影响的行数
     */
    int save(SaleReturnsSheetPO toSave);

    /**
     * 把销售退货单上的具体内容存入数据库
     *
     * @param SaleReturnsSheetContent 销售退货单上的具体内容
     */
    void saveBatch(List<SaleReturnsSheetContentPO> SaleReturnsSheetContent);

    /**
     * 返回所有销售退货单
     *
     * @return 销售退货单列表
     */
    List<SaleReturnsSheetPO> findAll();

    /**
     * 根据state返回销售退货单
     *
     * @param state 销售退货单状态
     * @return 销售退货单列表
     */
    List<SaleReturnsSheetPO> findAllByState(SaleReturnsSheetState state);

    /**
     * 根据 purchaseReturnsSheetId 找到条目， 并更新其状态为state
     *
     * @param SaleReturnsSheetId 销售退货单id
     * @param state              销售退货单状态
     * @return 影响的条目数
     */
    int updateState(String SaleReturnsSheetId, BaseEnum state);

    /**
     * 根据 purchaseReturnsSheetId 和 prevState 找到条目， 并更新其状态为state
     *
     * @param SaleReturnsSheetId 销售退货单id
     * @param prevState          销售退货单之前的状态
     * @param state              销售退货单状态
     * @return 影响的条目数
     */
    int updateStateV2(String SaleReturnsSheetId, BaseEnum prevState, BaseEnum state);

    /**
     * 通过SaleReturnsSheetId找到对应条目
     *
     * @param saleReturnsSheetId 销售退货单id
     * @return 销售退货单PO
     */
    SaleReturnsSheetPO findOneById(String saleReturnsSheetId);

    /**
     * 通过SaleReturnsSheetId找到对应的content条目
     *
     * @param saleReturnsSheetId 销售退货单id
     * @return 销售退货单内容POList
     */
    List<SaleReturnsSheetContentPO> findContentBySaleReturnsSheetId(String saleReturnsSheetId);

    /**
     * 通过时间段找到基本的表信息
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 表的基本信息
     */
    List<SheetPO> findAllBasicSheetInfo(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 找到销售单相关联的销售退货单
     *
     * @param saleSheetId 销售单Id
     * @return 关联的销售退货单Id
     */
    List<SaleReturnsSheetPO> findBySaleSheetId(@Param("saleSheetId") String saleSheetId);
}
