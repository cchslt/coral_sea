package com.fnd.psi.utils;

import java.util.Objects;

/**
 * @Date: 2022/2/27/027 20:37
 * @Desc: 提供通用 Integer类型数据 运算函数
 * @See:
 */
public class IntegerUtils {

    private static final Integer zero = 0;

    public static Boolean transitionToBoolean(Integer integer) {
        if (Objects.isNull(integer)) {
            return false;
        }
        if (integer.equals(1)) {
            return true;
        }
        return false;
    }

    /**
     * 对Integer 做非空处理 把null 转换成0  防止运算空指针
     * @param integer1
     * @return
     */
    public static Integer optionalToZero(Integer integer1) {
        if (Objects.isNull(integer1)) {
             return new Integer("0");
        }
        return integer1;
    }

    /**
     * 提供 两个Integer类型相加 主要处理为null 情况 避免空指针
     *
     * @param integer1
     * @param integer2
     * @return
     */
    public static Integer add(Integer integer1, Integer integer2) {
        if (Objects.isNull(integer1)) {
            integer1 = new Integer("0");
        }
        if (Objects.isNull(integer2)) {
            integer2 = new Integer("0");
        }
        return integer1.intValue() + integer2.intValue();
    }

    public static Integer addAll(Integer... values) {
        Integer sum = zero;
        for (Integer value : values) {
            sum = add(sum, value);
        }
        return sum;
    }

    /**
     * 提供 两个Integer类型相减 主要处理为null 情况 避免空指针
     *
     * @param integer1
     * @param integer2
     * @return
     */
    public static Integer sub(Integer integer1, Integer integer2) {
        if (Objects.isNull(integer1)) {
            integer1 = new Integer("0");
        }
        if (Objects.isNull(integer2)) {
            integer2 = new Integer("0");
        }
        return integer1.intValue() - integer2.intValue();
    }

    /**
     * 提供 两个Integer类型相减
     * 当为负数时候 返回0
     *
     * @param integer1
     * @param integer2
     * @return
     */
    public static Integer subNotMinus(Integer integer1, Integer integer2) {
        if (Objects.isNull(integer1)) {
            integer1 = new Integer("0");
        }
        if (Objects.isNull(integer2)) {
            integer2 = new Integer("0");
        }
        if(isGreater(integer1,integer2)){
            return integer1.intValue() - integer2.intValue();
        }
        return 0;
    }

    /**
     * 提供  判断第一个Integer是否大于第二个Integer
     *
     * @param integer1
     * @param integer2
     * @return
     */
    public static Boolean isGreater(Integer integer1, Integer integer2) {
        if (Objects.isNull(integer1)) {
            integer1 = new Integer("0");
        }
        if (Objects.isNull(integer2)) {
            integer2 = new Integer("0");
        }

        int compareToResult = integer1.compareTo(integer2);

        if (compareToResult == -1) {
            return false;
        } else if (compareToResult == 0) {
            return false;
        } else if (compareToResult == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断两个BigDecimal是否相等
     *
     * @param integer1
     * @param integer2
     * @return
     */
    public static Boolean isEquality(Integer integer1, Integer integer2) {
        if (Objects.isNull(integer1)) {
            integer1 = new Integer("0");
        }
        if (Objects.isNull(integer2)) {
            integer2 = new Integer("0");
        }
        int i = integer1.compareTo(integer2);
        if (i == 0) {
            return true;
        }
        return false;
    }

    /**
     * 输入大于0返回原值，输入小于0返回0兜底
     *
     * @param num
     * @return
     */
    public static Integer zeroFloor(Integer num) {
        if (num == null || num.compareTo(zero) < 0) {
            return zero;
        } else {
            return num;
        }
    }

}
