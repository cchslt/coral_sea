package com.stone.spider.util;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class JDBCConnectionFactory {
    private final static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String passwd = "123456";
    private final static String url ="jdbc:mysql://localhost:3306/spider?useUnicode=true&characterEncoding=utf-8";

    private JDBCConnectionFactory(){
    }

    private static HikariDataSource ds = null;

    /**
     * 初始化Hikari数据库连接池信息
     */
    static {
        ds = new HikariDataSource();
        ds.setDriverClassName(driver);
        ds.setJdbcUrl(url);
        ds.setUsername(user);
        ds.setPassword(passwd);
        ds.setAutoCommit(true);
        ds.setMaxLifetime(30 * 60 * 1000);
        ds.setMaximumPoolSize(10);
        ds.setConnectionTimeout(15000);
    }

    /**
     * 连接数据库
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return con;
    }



}

