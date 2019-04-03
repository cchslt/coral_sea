package com.stone.spark.operator;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author chen
 * @create 2019-04-02 00:05
 **/

public class LineToPairOperator {

    public static void main(String[] args) {
        SparkConf conf =new SparkConf().setAppName("LineToPairOperator").setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        JavaRDD lines = jsc.textFile("testPair.txt");

        //mapParition方式
        JavaPairRDD<String, Integer> linesToPairRDD = lines.mapPartitionsToPair(new PairFlatMapFunction<Iterator<String>, String, Integer>() {
            @Override
            public Iterator<Tuple2<String, Integer>> call(Iterator<String> iterator) throws Exception {
                List<Tuple2<String, Integer>> list = new ArrayList<>();
                while (iterator.hasNext()) {
                    String line = iterator.next();
                    String[] lineInfo = line.split(",");
                    list.add(new Tuple2(lineInfo[0], Integer.parseInt(lineInfo[1])));
                }
                return list.iterator();
            }
        });


        //普通map方式
//        JavaPairRDD<String, Integer>  linesToPairRDD = lines.mapToPair(new PairFunction() {
//            @Override
//            public Tuple2<String, Integer> call(Object o) throws Exception {
//                String line = o.toString();
//                String[] lineInfo = line.split(",");
//                return new Tuple2(lineInfo[0], Integer.parseInt(lineInfo[1]));
//            }
//        });


        //groupByKey
//        linesToPairRDD.groupByKey().foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
//            @Override
//            public void call(Tuple2<String, Iterable<Integer>> tuple2) throws Exception {
//                System.out.println("key: " + tuple2._1 +  "  value: " +tuple2._2);
//            }
//        });

        linesToPairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1.intValue() + v2.intValue();
            }
        }).foreachPartition(new VoidFunction<Iterator<Tuple2<String, Integer>>>() {
            @Override
            public void call(Iterator<Tuple2<String, Integer>> iterator) throws Exception {
                while (iterator.hasNext()) {
                    Tuple2<String, Integer> tuple2 = iterator.next();
                    System.out.println("key: " + tuple2._1 +  "  value: " +tuple2._2);
                }
            }
        });
    }
}
