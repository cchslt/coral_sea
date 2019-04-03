package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CountByKeyOperator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("CountByKeyOperator").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Tuple2<String, String>> list = Arrays.asList(new Tuple2<>("90S", "chen"),
                new Tuple2<>("90S", "shi"),
                new Tuple2<>("00S", "chen"));

        JavaPairRDD<String, String> countsRDD = sc.parallelizePairs(list);

        Map<String, Long> counts = countsRDD.countByKey();

        for (String key : counts.keySet()) {
            System.out.println(key + "----------" + counts.get(key));
        }
    }
}
