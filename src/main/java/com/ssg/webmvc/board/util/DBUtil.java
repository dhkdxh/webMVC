package com.ssg.webmvc.board.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum DBUtil {
    INSTANCE;

    private HikariDataSource ds;

    DBUtil() {
        HikariConfig config = new HikariConfig(); //다시 정의할 필요없음
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/db1?serverTimezone=Asia/Seoul");
        config.setUsername("root");
        config.setPassword("wjsdPdnjs0404!");

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        //prepareStatement를 사용해서 data를 mapping 하겠다는 의미

        ds = new HikariDataSource(config);
    }

    //ConnectionUtil.INSTANCE.getConnection()으로 처리
    public Connection getConnection() throws Exception{
        return ds.getConnection();
    }
}
