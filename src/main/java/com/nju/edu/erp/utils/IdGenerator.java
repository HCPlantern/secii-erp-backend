package com.nju.edu.erp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class IdGenerator {
    /**
     * 获取新单号
     *
     * @param prevId 上一次的单号
     * @return 新的单号
     */
    public static String generateSheetId(String prevId, String prefix) { // "{prefix}-20220216-00000"
        return generateSheetIdWithTime(prevId, prefix, new Date());
    }

    public static String generateSheetIdWithTime(String prevId, String prefix, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(date);
        if (prevId == null) {
            return prefix + "-" + today + "-" + String.format("%05d", 0);
        }
        String lastDate = prevId.split("-")[1];
        if (lastDate.equals(today)) {
            String prevNum = prevId.split("-")[2];
            return prefix + "-" + today + "-" + String.format("%05d", Integer.parseInt(prevNum) + 1);
        } else {
            return prefix + "-" + today + "-" + String.format("%05d", 0);
        }
    }
}
