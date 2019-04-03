package com.stone;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.*;

/**
 * Created by chenchen on 2018/2/4.
 */
public class Test {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Test").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);

        final List<String> lines = Arrays.asList("zhangsan", "lisi", "zhaowu");
        JavaRDD<String> nameRDD = sc.parallelize(lines);

        final Map<String, Integer> scoreMap = new HashMap<>();
        scoreMap.put("zhangsan", 100);
        scoreMap.put("lisi", 90);
        scoreMap.put("zhaowu", 88);


        JavaRDD<Integer> scoreRDD = nameRDD.mapPartitions(new FlatMapFunction<Iterator<String>, Integer>() {
            @Override
            public Iterator<Integer> call(Iterator<String> iterator) throws Exception {
                List<Integer> list = new ArrayList<Integer>();

                while(iterator.hasNext()) {
                    String name = iterator.next();
                    Integer score = scoreMap.get(name);
                    list.add(score);
                }

                return list.iterator();
            }
        });


        scoreRDD.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
