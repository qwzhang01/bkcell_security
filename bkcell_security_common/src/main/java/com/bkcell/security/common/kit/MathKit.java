package com.bkcell.security.common.kit;

import java.text.DecimalFormat;

public class MathKit {

    /**
     * 格式化金额，并千分符化
     *
     * @param money
     * @return Created By cc on 2016年12月15日
     */
    public static String formatMoney(Object money) {
        if (money == null) {
            return "0.00";
        }
        DecimalFormat d1 = new DecimalFormat("#,##0.##;(#)");
        return d1.format(money);
    }
}
