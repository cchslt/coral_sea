package com.stone.spark.operator;

import com.google.common.collect.Lists;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chenchaohai
 * E-mail chenchaohai@startdt.com
 * 2018/6/4.
 */
public class UDFTest {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("UDFTest").setMaster("local");
        JavaSparkContext jsc = new JavaSparkContext(conf);
        SQLContext sc = new SQLContext(jsc);

        List<String> names = Arrays.asList("ab", "bbbb", "cb", "dss");
        JavaRDD<String> nameRDD = jsc.parallelize(names, 5);

        JavaRDD<Row> nameRowRDD = nameRDD.map(new Function<String, Row>() {
            @Override
            public Row call(String name) throws Exception {
                return RowFactory.create(name);
            }
        });

        List<StructField> fields = Lists.newArrayList();
        fields.add(DataTypes.createStructField("name", DataTypes.StringType, true));

        StructType structType = DataTypes.createStructType(fields);
        Dataset dataset =  sc.createDataFrame(nameRowRDD, structType);
        dataset.registerTempTable("names");

        sc.udf().register("strLen", new UDF1<String, Integer>() {
            @Override
            public Integer call(String s) throws Exception {
                return s.length();
            }
        }, DataTypes.IntegerType);

        List<Row> rows = sc.sql("select name, strLen(name) from names").javaRDD().collect();
        for (Row row : rows) {
            System.out.println("name:" + row.get(0) + "  长:" + row.get(1));
        }

        jsc.close();
    }
}
