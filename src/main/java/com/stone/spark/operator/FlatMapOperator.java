package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FlatMapOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("FlatMapOperator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> words = Arrays.asList("Hello chen", "Hello shi", "Hello zhou");
        JavaRDD<String> wordsRDD = sc.parallelize(words);

        //将一行数据打散分离
        JavaRDD<String> result = wordsRDD.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        });

        result.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println("word : " + s);
            }
        });

        sc.close();
    }
}
