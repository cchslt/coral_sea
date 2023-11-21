package com.stone.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: chenchaohai
 * @Date: 2023-11-09 16:40
 * @Desc:
 * @See:
 */
public class MatespaceOOM {

    public static void main(String[] args) {
        System.out.println("dfdf");

        String d = new String();
        String printTable = ClassLayout.parseInstance(d).toPrintable();

        System.out.println(printTable);
    }
}
