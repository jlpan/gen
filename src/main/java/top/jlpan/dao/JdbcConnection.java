package top.jlpan.dao;

import java.sql.Connection;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName JdbcConnection
 * @Description
 * @Date 2019/7/17 9:44
 */
public class JdbcConnection {

    private JdbcConnection () {

    }

    private static class Singleton {
        private static ConnectionPool pool = new ConnectionPool();
    }

    public static Connection getConnection() {
        return Singleton.pool.getResource();
    }

    public static void release(Connection conn) {
        Singleton.pool.release(conn);
    }

}