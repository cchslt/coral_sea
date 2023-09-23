package com.fnd.psi.annotation.convertor;

/**
 * @Date: 2022/2/21/021 20:33
 * @Desc: 定义转换使用方法工厂
 * @See:
 */
public class ConvertFactory {

    public static Convertor getObjectConvertor() {
        return new ObjectConvertor();
    }
    public static Convertor getListConvertor() {
        return new ListConvertor();
    }
    public static Convertor getSetConvertor() {
        return new SetConvertor();
    }
    public static Convertor getMapConvertor() {
        return new MapConvertor();
    }
}
