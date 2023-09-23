package com.fnd.psi.utils;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ArrayIter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * 时区转换类
 * 将服务器时间转换成用户时区时间

 */
@Slf4j
@UtilityClass
public class DateTimeZoneConvertUtils {

    /**
     * 服务器时区
     */
    public static final int SERVER_TIME_ZONE = 8;


    @SneakyThrows
    private void timeZoneConvertObj(Object obj, int sourceTimeZone, int targetTimeZone) {
        Field field;
        Collection<BeanDesc.PropDesc> props = BeanUtil.getBeanDesc(obj.getClass()).getProps();
        for (BeanDesc.PropDesc prop : props) {
            field = prop.getField();
            Method getterMethod = prop.getGetter();
            Method setterMethod = prop.getSetter();
            if (null == setterMethod && !ModifierUtil.isPublic(field)) {
                // Setter方法不存在或者字段为非public跳过
                //5.1.0新增支持public字段注入支持
                continue;
            }
            //包装类、基本类型  除了日期时间型
            if (isBaseTypeExceptDate(prop.getFieldClass())) {
                continue;
            }
            Object value = getterMethod.invoke(obj);
            if (value == null) {
                continue;
            }
            if (isDateType(prop.getFieldClass())) {
                //日期时间型
//                log.info("{}------->{}-------{}", prop.getFieldClass().getName(), prop.getFieldName(), userTimeZone);
                Date date = (Date) value;
                Date targetDate = timeZoneTransfer(date, sourceTimeZone, targetTimeZone);
                if (null == setterMethod) {
                    // 直接注入值
                    ReflectUtil.setFieldValue(obj, field, targetDate);
                } else {
                    // 执行set方法注入值
                    ReflectUtil.invoke(obj, setterMethod, targetDate);
                }
                continue;
            }
            timeZoneConvert(value, sourceTimeZone, targetTimeZone);
//            //集合类
//            Iterator<?> iter = getIterator(value);
//            if (iter != null) {
//                while (iter.hasNext()) {
//                    timeZoneConvertObj(iter.next(), userTimeZone);
//                }
//            } else {//class类
//                timeZoneConvertObj(value, userTimeZone);
//            }
        }
    }

    /**
     * 时区转换 不区分集合和单实体
     *
     * @param obj
     * @param targetTimeZone
     */
    public void timeZoneConvert(Object obj, int sourceTimeZone, int targetTimeZone) {
        if (obj != null) {
            if (!tryTimeZoneConvertColl(obj, sourceTimeZone, targetTimeZone)) {
                timeZoneConvertObj(obj, sourceTimeZone, targetTimeZone);
            }
        }
    }

    /**
     * 服务器时区转换成目标时区时间 不区分集合和单实体
     *
     * @param obj
     * @param targetTimeZone
     */
    public void serverTimeConvert(Object obj, int targetTimeZone) {
        timeZoneConvert(obj, SERVER_TIME_ZONE, targetTimeZone);
    }

    private boolean tryTimeZoneConvertColl(Object obj, int sourceTimeZone, int targetTimeZone) {
        Iterator<?> iter = getIterator(obj);
        if (iter != null) {
            while (iter.hasNext()) {
                Object item = iter.next();
                if (item != null) {
                    timeZoneConvertObj(item, sourceTimeZone, targetTimeZone);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 获取迭代器
     *
     * @param obj
     * @return
     */
    private Iterator<?> getIterator(Object obj) {
        Iterator<?> iter = null;
        if (obj.getClass().isArray()) {// 数组
            iter = new ArrayIter<>(obj);
        } else if (obj instanceof Iterator<?>) {// Iterator
            iter = ((Iterator<?>) obj);
        } else if (obj instanceof Iterable<?>) {// Iterable
            iter = ((Iterable<?>) obj).iterator();
        }
        return iter;
    }


    private static boolean isDateType(Class clazz) {
        return clazz.equals(Date.class);
    }

    private static boolean isBaseTypeExceptDate(Class clazz) {
        return
                (
                        clazz.equals(String.class) ||
                                clazz.equals(Integer.class) ||
                                clazz.equals(Byte.class) ||
                                clazz.equals(Long.class) ||
                                clazz.equals(Double.class) ||
                                clazz.equals(Float.class) ||
                                clazz.equals(Character.class) ||
                                clazz.equals(Short.class) ||
                                clazz.equals(BigDecimal.class) ||
                                clazz.equals(BigInteger.class) ||
                                clazz.equals(Boolean.class) ||
                                clazz.isPrimitive()
                );
    }

    /**
     * 服务器时区时间转换成目标时间
     *
     * @param serverDateTime 服务器时间
     * @param targetTimeZone 目标时区
     * @return 目标时区时间
     */
    public static Date serverTimeTransfer(Date serverDateTime, int targetTimeZone) {
        return timeZoneTransfer(serverDateTime, SERVER_TIME_ZONE, targetTimeZone);
    }

    /**
     * 时区转换
     *
     * @param dateTime       日期时间
     * @param sourceTimeZone 来源时区
     * @param targetTimeZone 目标时区 +8，0，+9，-1 等等
     * @return 目标时区时间
     */
    public static Date timeZoneTransfer(Date dateTime, int sourceTimeZone, int targetTimeZone) {
        if (dateTime == null) {
            return null;
        }
        if (sourceTimeZone == targetTimeZone) {
            return dateTime;
        }
        return DateUtil.offsetHour(dateTime, targetTimeZone - sourceTimeZone);
    }
}