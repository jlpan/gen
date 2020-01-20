package top.jlpan.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static top.jlpan.config.GenConfig.*;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName JdbcConnection
 * @Description jdbc连接
 * @Date 2019/7/17 9:44
 */
public class JdbcConnection {

    private JdbcConnection() {
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


}