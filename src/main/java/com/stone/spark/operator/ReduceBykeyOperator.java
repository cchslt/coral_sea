package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class ReduceBykeyOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("GroupByKey").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Tuple2<String, Integer>> list = Arrays.asList(new Tuple2<>("chen", 100),
                new Tuple2<>("shi", 100),
                new Tuple2<>("chen", 90));

        JavaPairRDD<String, Integer> scoreRDD = sc.parallelizePairs(list);

        scoreRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        }).foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> tuple2) throws Exception {
                System.out.println("name :" + tuple2._1 + " score:" + tuple2._2);
            }
        });

        sc.close();
    }
}
