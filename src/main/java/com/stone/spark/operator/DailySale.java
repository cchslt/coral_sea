package com.stone.spark.operator;

import com.google.common.collect.Lists;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chenchaohai
 * E-mail chenchaohai@startdt.com
 * 2018/6/4.
 */
public class DailySale {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("UDFTest").setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        SQLContext sc = new SQLContext(jsc);

        List<String> userSaleLog = Arrays.asList("2018-06-04,2.5,111",
                "2018-06-04,2.5,",
                "2018-06-04,2.5,112",
                "2018-06-04,2.5,113",
                "2018-06-05,2.5,111");

        JavaRDD<String> userSaleLogRDD = jsc.parallelize(userSaleLog, 4);
        JavaRDD<String> filteredUserLogRDD = userSaleLogRDD.filter(new Function<String, Boolean>() {
            @Override
            public Boolean call(String log) throws Exception {
                return log.split(",").length == 3 ? true : false;
            }
        });

        JavaRDD<Row> userSaleLogRowRDD = filteredUserLogRDD.map(new Function<String, Row>() {
            @Override
            public Row call(String log) throws Exception {
                return RowFactory.create(log.split(",")[0], Double.valueOf(log.split(",")[1]));
            }
        });

        List<StructField> fields = Lists.newArrayList();
        fields.add(DataTypes.createStructField("date", DataTypes.StringType, true));
        fields.add(DataTypes.createStructField("sale_amount", DataTypes.DoubleType, true));

        StructType structType = DataTypes.createStructType(fields);
        Dataset dataset = sc.createDataFrame(userSaleLogRowRDD, structType);
        dataset.registerTempTable("log");


        sc.sql("select date, sum(sale_amount) from log group by date").javaRDD()
        .foreachPartition(new VoidFunction<Iterator<Row>>() {
            @Override
            public void call(Iterator<Row> iterator) throws Exception {
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    System.out.println("日期: " + row.get(0) + " 金额:" + row.get(1));
                }
            }
        });

        jsc.close();
    }
}
