package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GroupByKeyOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("GroupByKey").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Tuple2<String, Integer>> list = Arrays.asList(
                new Tuple2<>("chen", 100),
                new Tuple2<>("shi", 100),
                new Tuple2<>("chen", 90),
                new Tuple2<>("ting", 90),
                new Tuple2<>("chen", 10));

        JavaPairRDD<String, Integer> scoreRDD = sc.parallelizePairs(list);

        /**
         *
         */
        scoreRDD.groupByKey().foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
            @Override
            public void call(Tuple2<String, Iterable<Integer>> tuple2) throws Exception {
                System.out.println(tuple2._1 + "   " + tuple2._2);
            }
        });

        sc.close();

    }
}
