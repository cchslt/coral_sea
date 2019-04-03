package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class DistinctOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("DistinctOperator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> names = Arrays.asList("chen", "chen", "chen", "shi");

        JavaRDD<String> distinctRDD = sc.parallelize(names);

        distinctRDD.distinct().foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println("name: " + s);
            }
        });
    }
}
