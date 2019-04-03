package com.stone.spider.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ListToStringUtils {


    public static String listToString(List list, char separator) {
        return StringUtils.join(list.toArray(),separator);
    }



}
