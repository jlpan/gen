package top.jlpan.config;


import top.jlpan.config.enums.InitType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName GenConfig
 * @Description
 * @Date 2019/1/7 9:40
 */
public class GenConfig {

    /** 数据库路径 */
    public static String url = "jdbc:mysql://127.0.0.1:3306/gen";

    /** 数据库账号 */
    public static String username = "root";

    /** 数据库密码 */
    public static String password = "root";

    /** 数据库驱动 */
    public static String driver = "com.mysql.cj.jdbc.Driver";

    /** UTF-8 字符集 */
    public static String Character  = "UTF-8";

    /** 系统分隔符 */
    public static String separator = java.io.File.separator;

    /** 项目根路径 */
    public static String root = System.getProperty("user.dir");

    /** service层生成路径 */
    public static StringBuilder servicePath = new StringBuilder();

    /** controller层生成路径 */
    public static StringBuilder controllerPath = new StringBuilder();

    /** vue前端生成路径 */
    public static StringBuilder vuePath = new StringBuilder();

    /** 表前缀 */
    public static String prefix = "";

    /** 加载模板路径方式 默认为相对路径classpath*/
    public static InitType resource = InitType.RELATIVE;


    /**
     * 获取创建时间
     * @return
     */
    public static String getCreateDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

}
