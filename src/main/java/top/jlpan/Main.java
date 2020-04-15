package top.jlpan;

import top.jlpan.config.GenConfig;
import top.jlpan.data.ITableService;
import top.jlpan.data.TableServiceImpl;

import top.jlpan.model.Table;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2020/4/15 11:25
 */
public class Main {
    public static void main(String[] args) {

        /* 参数配置 */
        GenConfig.url = "jdbc:mysql://127.0.0.1:3306/database?usdeUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        GenConfig.username = "root";
        GenConfig.password = "root";

        String tableName = "user_account";

        String absolutePath = "";
        String classPath = "";
        InputStream stream = new ByteArrayInputStream("${tableName}${key}".getBytes());

        HashMap<String, Object> fillMap = new HashMap<>();
        Table table;

        ITableService tableService = new TableServiceImpl();
        table = tableService.selectTableByName(tableName);
        fillMap.put("key", "value");

        /* 绝对路径加载模板 */
//        AbsoluteTemplateGen gen = new AbsoluteTemplateGen(absolutePath);
//        gen.initData(fillMap, table);
//        System.out.println(gen.gen().toString());


        /* 相对路径加载模板 */
//        ClassPathTemplateGen gen1 = new ClassPathTemplateGen(classPath);
//        gen1.initData(fillMap, table);
//        System.out.println(gen1.gen().toString());

        /* 流文件加载模板 */
//        StreamTemplateGen gen2 = new StreamTemplateGen(stream);
//        gen2.initData(fillMap, table);
//        System.out.println(gen2.gen().toString());
//        mvn clean deploy -P sonatype-oss-release -Darguments="gpg.passphrase=123"

    }
}
