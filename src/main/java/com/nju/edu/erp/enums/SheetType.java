package com.nju.edu.erp.enums;

public enum SheetType implements BaseEnum<SheetType, String> {
    SALE_SHEET("销售单"),
    SALE_RETURN_SHEET("销售退货单"),
    PURCHASE_SHEET("进货单"),
    PURCHASE_RETURN_SHEET("进货退货单"),
    PAYMENT_SHEET("付款单"),
    COLLECTION_SHEET("收款单"),
    SALARY_SHEET("工资单"),
    WAREHOUSE_SHEET("入库单"),
    WAREHOUSE_RETURN_SHEET("出库单");

    private final String value;

    SheetType(String value) {
        this.value = value;
    }

    /**
     * 获取枚举的值
     *
     * @return 枚举的值
     */
    @Override
    public String getValue() {
        return this.value;
    }

    }
