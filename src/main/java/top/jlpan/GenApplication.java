package top.jlpan;

import top.jlpan.config.GenConfig;
import top.jlpan.model.temp.TableExecHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName GenApplication
 * @Description 启动类
 * @Date 2019/8/21 9:44
 */
public class GenApplication {

    public static void main(String[] args) {

        /** 参数配置 */
        GenConfig.url = "jdbc:mysql://127.0.0.1:3306/free?usdeUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        GenConfig.username = "root";
        GenConfig.password = "root";

        HashMap<String, String> tableMap = autoTable();
        HashMap<String, Object> paramsMap = autoParams();

        /** 循环生成 */
        for (Map.Entry<String, String> entry : tableMap.entrySet()) {

            String tableName = entry.getValue();


            exec(paramsMap, tableName);
        }
        System.out.println("执行完毕");
    }

    /**
     * 注入参数
     * @return
     */
    private static HashMap<String, String> autoTable() {

        /** 模块名和数据库名对应关系 */
        HashMap<String, String> map = new HashMap<>(20);
        map.put("user_account", "user_account");

        return map;
    }

    /**
     * 注入表数据
     * @return
     */
    private static HashMap<String, Object> autoParams() {
        HashMap<String, Object> map = new HashMap<>(10);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("createDate", format.format(new Date()));
        map.put("author", "panliang");
        map.put("version", "1.0");
        return map;
    }


    /**
     * 执行生成
     * @param paramsMap
     * @param tableName
     */
    private static void exec(HashMap<String, Object> paramsMap, String tableName) {
        TableExecHelper helper = new TableExecHelper(paramsMap, tableName);

        helper.setModelTemplate("template/service/model.java.vm");

        helper.exec();
    }


}

