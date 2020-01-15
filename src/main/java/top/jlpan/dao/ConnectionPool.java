package top.jlpan.dao;

import top.jlpan.config.GenConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName ConnectionPool
 * @Description
 * @Date 2019/7/17 9:44
 */
public class ConnectionPool extends AbstractObjectPool<Connection> {

    /** 数据库路径 */
    private String url = GenConfig.url;
    /** 用户名 */
    private String username = GenConfig.username;
    /** 密码 */
    private String password = GenConfig.password;

    public ConnectionPool() {
        try {
            String driver = GenConfig.driver;
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Connection create() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public boolean validate(Connection o, long createTime) {
        return System.currentTimeMillis() - createTime <= MAX_KEEP_ALIVE_TIME;
    }

    @Override
    public void expire(Connection o) {
        try {
            o.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}