package com.stone.spider.cia;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class TT {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("%25E6%B255%258B%25E8%AF%2595","UTF-8"));
    }
}
