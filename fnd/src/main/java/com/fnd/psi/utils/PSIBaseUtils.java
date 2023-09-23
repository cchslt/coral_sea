package com.fnd.psi.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fnd.psi.dto.BasePageQuery;
import com.google.common.collect.Sets;

import java.util.Set;


public class PSIBaseUtils {

    /**
     * 默认分页数据行数
     */
    private final static Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 默认分页页码
     */
    private final static Integer DEFAULT_PAGE_NO = 1;

    /**
     * 根据继承过BasePageQuery实体 mybatis-plus Page构参
     *
     * @param basePageQuery 需判断非空和必要的传参
     * @return
     */
    public static Page buildPageByQuery(BasePageQuery basePageQuery) {
        if(ObjectUtil.isNotNull(basePageQuery)){
            if(ObjectUtil.isNull(basePageQuery.getPageNo()) || ObjectUtil.isNull(basePageQuery.getPageSize())){
                return new Page(DEFAULT_PAGE_NO,DEFAULT_PAGE_SIZE);
            }
        }
        return new Page(basePageQuery.getPageNo(), basePageQuery.getPageSize());
    }
    /**
     * 判断多个参数 都不为空  如有一个为空 则返回true
     *
     * @param cs
     * @return true false
     */
    public static boolean strParametersIsBlank(CharSequence... cs) {
        for (CharSequence charSequence : cs) {
            if (StrUtil.isBlank(charSequence)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个参数 都不为空  如有一个为空 则返回true
     *
     * @param objects
     * @return true false
     */
    public static boolean objParametersIsBlank(Object... objects) {
        for (Object o : objects) {
            if (ObjectUtil.isNull(o)) {
                return true;
            }
        }
        return false;
    }

    public static boolean enableStatusIsIllegal(Integer status) {
        Set<Integer> set = Sets.newHashSet(0, 1);
        return !set.contains(status);
    }


}
