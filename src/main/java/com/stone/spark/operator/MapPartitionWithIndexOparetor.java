package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MapPartitionWithIndexOparetor {

    public static void main(String[] args) {
        SparkConf sc = new SparkConf().setAppName("MapPartitionWithIndexOparetor").setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(sc);


        List<String> list = Arrays.asList("shi","chen", "zhang");

        //数据分到两个分区中，但是不知道具体分到哪一个partition下
        //可以通过mapPartitionsWithIndex拿到partition的编号
        JavaRDD<String> nameRdd = jsc.parallelize(list, 2);

        JavaRDD<String> namePartitionWithIndex = nameRdd.mapPartitionsWithIndex(new Function2<Integer, Iterator<String>, Iterator<String>>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Iterator<String> call(Integer integer, Iterator<String> iterator) throws Exception {

                List<String> indexList = new ArrayList<>();
                while (iterator.hasNext()) {
                    String name = iterator.next();
                    String result = integer + " , " + name;
                    indexList.add(result);

                }
                return indexList.iterator();
            }
        }, true);


        /**
         * 两种方式遍历rdd的数据，在数据量较小、访问数据库和网络IO的情况下，推荐使用foreachPartition性能上比较好
         */
        namePartitionWithIndex.foreachPartition(new VoidFunction<Iterator<String>>() {
            @Override
            public void call(Iterator<String> iterator) throws Exception {
                while (iterator.hasNext()) {
                    String name = iterator.next();
                    System.out.println(name);
                }
            }
        });


        System.out.println("___________________________________________");

        namePartitionWithIndex.foreach(new VoidFunction<String>() {
            @Override
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
