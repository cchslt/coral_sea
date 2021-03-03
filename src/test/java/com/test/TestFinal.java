package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen
 * @create 2019-01-08 16:30
 **/

public class TestFinal {

    public static void main(String[] args) {

        final List<String> strList = new ArrayList<>(10);
        strList.add(2, "ooo");
        strList.add("Hello");
        strList.add("world");

        strList.remove(1);

//        List<String> unmodifyList = List.of("Hello", "World");  //java 9特性

        System.out.println(strList);


        StringBuffer stringBuffer = new StringBuffer(32);

        StringBuilder sb = new StringBuilder(32);
    }
}
