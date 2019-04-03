package com.stone.spark.operator;

import com.stone.DTO.Students;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.ForeachPartitionFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.util.Iterator;
import java.util.List;

public class RDD2DataFrameReflection {

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf().setAppName("dataframe").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new SQLContext(sc);

        JavaRDD<String> lines = sc.textFile("students.txt");
        JavaRDD<Students> studentRDD = lines.map(new Function<String, Students>() {
            @Override
            public Students call(String line) throws Exception {
                String[] linesInfo = line.split(",");
                Students student = new Students();
                student.setId(Integer.valueOf(linesInfo[0].trim()));
                student.setName(linesInfo[1].trim());
                student.setAge(Integer.valueOf(linesInfo[2].trim()));
                return student;
            }
        });


        //读取文件数据
//        List<Students> studentsList = studentRDD.collect();
//        for (Students students : studentsList) {
//            System.out.println(students.toString());
//        }


        Dataset<Row> studensDF = sqlContext.createDataFrame(studentRDD, Students.class);
        studensDF.registerTempTable("student");
        sqlContext.cacheTable("student");

        System.out.println("转化成sql执行后。。。。");
        sqlContext.sql("select id, name, age from student where id>=3")
                .foreachPartition(new ForeachPartitionFunction<Row>() {
                    @Override
                    public void call(Iterator<Row> iterator) throws Exception {
                        while (iterator.hasNext()) {
                            Row row = iterator.next();
                            System.out.println("ID: " + row.get(0)
                                + " 姓名: " + row.get(1) + " 年龄: " + row.get(2));
                        }
                    }
                });
        System.out.println("执行完成！！！");

        sqlContext.uncacheTable("student");


    }
}
