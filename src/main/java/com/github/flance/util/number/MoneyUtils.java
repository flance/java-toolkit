package com.github.flance.util.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 货币处理工具
 *
 * @author zhangying
 * @date 2019/2/16 20:37
 */
public class MoneyUtils {
    /**
     * 漂亮的千分位格式
     */
    private static final ThreadLocal<DecimalFormat> PRETTY_THOUSANDS_FORMAT = createThreadLocalNumberformat("#,##0.00");

    /**
     * 格式化为千分位方式，如 12334563456.23 输出位 12,334,563,456.23
     *
     * @param number 数字或金额
     * @return 千分位格式化之后的字符串
     */
    public static String prettyFormat(BigDecimal number) {
        return prettyFormat(number.doubleValue());
    }

    /**
     * 格式化为千分位方式，如 12334563456.23 输出位 12,334,563,456.23
     *
     * @param number 数字或金额
     * @return 千分位格式化之后的字符串
     */
    public static String prettyFormat(double number) {
        return PRETTY_THOUSANDS_FORMAT.get().format(number);
    }

    private static ThreadLocal<DecimalFormat> createThreadLocalNumberformat(final String pattern) {
        return new ThreadLocal<DecimalFormat>() {
            @Override
            protected DecimalFormat initialValue() {
                DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
                df.applyPattern(pattern);
                return df;

            }
        };
    }
}
