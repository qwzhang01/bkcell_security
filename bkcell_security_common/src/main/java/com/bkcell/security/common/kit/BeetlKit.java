package com.bkcell.security.common.kit;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BeetlKit {

    /**
     * 金额千分符化
     *
     * @param money
     * @return Created By cc on 2016年12月23日
     */
    public static String formatMoney(Object money) {
        if (money == null || money == "") {
            return "￥0.00";
        }
        if ("--".equals(money)) {
            return "--";
        }
        return "￥" + MathKit.formatMoney((BigDecimal) money);
    }

    public static String numFormat(String num) {
        if (StrUtil.isBlank(num)) {
            return "0";
        }
        DecimalFormat df = new DecimalFormat("###,###.##");
        double number = 0.0;
        try {
            number = Double.parseDouble(num);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }
}
