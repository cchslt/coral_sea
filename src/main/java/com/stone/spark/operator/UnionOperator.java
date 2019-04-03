package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class UnionOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("UnionOperator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> names = Arrays.asList("chen", "liu", "chen", "shi");
        List<String> names1 = Arrays.asList("zhao", "chen", "chen", "zhou");

        JavaRDD<String> namesRDD = sc.parallelize(names, 2);
        JavaRDD<String> names1RDD = sc.parallelize(names1, 3);


        /**
         * 多个RDD数据集合成一个数据集
         * 只是逻辑上合成数据集，partition个数等于多个数据集之和，数据存储地址不变
         */
        namesRDD.union(names1RDD).foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

}
