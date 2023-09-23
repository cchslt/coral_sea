package com.fnd.psi.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Desc: 通用实体属性复制工具类
 * @See:
 */
@UtilityClass
public class CopyBeanUtils {

    /**
     * 将实体类转为DTO类型
     */
    public <S, T> T convertEntityToDto(S source, T target) {
        BeanUtil.copyProperties(source, target);
        return target;
    }

    /**
     * 封装通用集合转换
     *
     * @param list
     * @param mapper
     * @param <T>
     * @param <R>
     * @return 转换后的集合
     */
    public <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        return BeanUtil.isEmpty(list) ? new ArrayList() : list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 复制集合
     *
     * @param sourceList
     * @param clazz
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<T> copyList(List<R> sourceList, Class<T> clazz) {
        if (CollUtil.isEmpty(sourceList)) {
            return CollUtil.newArrayList();
        }
        List<T> target = new ArrayList<>();
        sourceList.forEach(k -> target.add(convert(k, clazz)));
        return target;
    }

    /**
     * 复制对象
     */
    public static <T, K> T convert(K source, Class<T> clazz) {
        return BeanUtil.copyProperties(source, clazz);
    }
}
