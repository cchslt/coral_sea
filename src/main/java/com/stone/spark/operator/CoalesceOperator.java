package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class CoalesceOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("CoalesceOperator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> names = Arrays.asList("chen", "liu", "chen", "shi");
        List<String> names1 = Arrays.asList("zhao", "chen", "chen", "zhou");

        JavaRDD<String> nameRdd = sc.parallelize(names, 4);
        JavaRDD<String> name2Rdd = sc.parallelize(names1).coalesce(1);

        nameRdd.coalesce(1).foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println("名称： " + s);
            }
        });


        name2Rdd.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println("名称： " + s);
            }
        });

    }
}
