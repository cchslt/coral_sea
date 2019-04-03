package com.stone.spark.operator;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class AccumulatorValue {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("AccumulatorValue").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);


        final Accumulator sum = sc.accumulator(0);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> rdd = sc.parallelize(list);

        rdd.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer num) throws Exception {
                sum.add(num);
                System.out.println(sum);
            }
        });


        System.out.println("最终结果:" + sum);
        sc.close();
    }
}
