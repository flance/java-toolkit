package com.github.flance.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间格式化工具
 *
 * @author zhangying
 * @date 2019/2/16 20:32
 */

public class DateFormatUtils {
    /**
     * ISO标准日期时间格式(精确到毫秒)
     */
    public static final String DATETIME_ISO_ON_MS = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

    /**
     * ISO标准日期时间格式(精确到秒)
     */
    public static final String DATETIME_ISO = "yyyy-MM-dd'T'HH:mm:ssZZ";

    /**
     * 默认的通用日期时间格式(精确到秒)
     */
    public static final String DATETIME_COMMON = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认的通用日期时间格式(精确到毫秒)
     */
    public static final String DATETIME_COMMON_ON_MS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 格式化指定日期
     *
     * @param date 日期对象
     * @return 格式化为 yyyy-MM-dd HH:mm:ss 字符串
     */
    public static String formatCommonDatetime(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATETIME_COMMON).format(date);
    }

    /**
     * 格式化指定日期
     *
     * @param date 日期对象
     * @return 格式化为 yyyy-MM-dd HH:mm:ss 字符串
     */
    public static String formatCommonDatetimeOnMS(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATETIME_COMMON_ON_MS).format(date);
    }

    /**
     * 格式化指定日期
     *
     * @param date 日期
     * @return 格式化为 yyyy-MM-dd'T'HH:mm:ssZZ 字符串
     */
    public static String formatIsoDatetime(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATETIME_ISO).format(date);
    }

    /**
     * 格式化指定日期
     *
     * @param date 日期
     * @return 格式化为 yyyy-MM-dd'T'HH:mm:ss.SSSZZ 字符串
     */
    public static String formatIsoDatetimeOnMS(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATETIME_ISO_ON_MS).format(date);
    }
}
