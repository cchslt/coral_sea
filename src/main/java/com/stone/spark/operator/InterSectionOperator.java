package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

/**
 * 求RDD的交集
 */
public class InterSectionOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("InterSectionOperator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> names = Arrays.asList("chen", "liu", "chen", "shi");
        List<String> names1 = Arrays.asList("zhao", "chen", "chen", "zhou");

        JavaRDD<String> namesRDD = sc.parallelize(names);
        JavaRDD<String> names1RDD = sc.parallelize(names1);

        namesRDD.intersection(names1RDD).foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println("两个RDD的交集：" + s);
            }
        });


        sc.close();

    }
}
