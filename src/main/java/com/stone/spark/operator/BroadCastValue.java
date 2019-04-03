package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

public class BroadCastValue {


    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("BroadCastValue").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);


        final int f =3;
        //使用广播变量，即将只读值都缓存到每个worker下
        final Broadcast<Integer> broadcastFactor = sc.broadcast(f);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> rdd = sc.parallelize(list);

        JavaRDD<Integer> result = rdd.map(new Function<Integer, Integer>() {
            @Override
            public Integer call(Integer num) throws Exception {
                int factor = broadcastFactor.getValue();
                return num * factor;
            }
        });

        result.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println("结果为: " + integer);
            }
        });

        sc.close();
    }
}
