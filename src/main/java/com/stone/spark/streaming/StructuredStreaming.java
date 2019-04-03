package com.stone.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * @author chen
 * @create 2019-04-03 00:13
 **/

public class StructuredStreaming {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("StructuredStreaming").setMaster("local");
        SparkSession session = SparkSession.builder()
                .config(conf)
                .getOrCreate();

        Dataset<String> ds = session.readStream()
                .textFile("");

    }
}
