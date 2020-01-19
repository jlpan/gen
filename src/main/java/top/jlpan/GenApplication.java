package top.jlpan;

import top.jlpan.config.GenConfig;
import top.jlpan.data.ITableService;
import top.jlpan.data.TableServiceImpl;
import top.jlpan.model.Table;
import top.jlpan.gen.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName GenApplication
 * @Description 启动类
 * @Date 2019/8/21 9:44
 */
public class GenApplication {

    public static void main(String[] args) {

        /* 参数配置 */
        GenConfig.url = "jdbc:mysql://127.0.0.1:3306/free?usdeUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        GenConfig.username = "root";
        GenConfig.password = "root";

        String tableName = "user_account";

        String absolutePath = "C:\\Users\\Administrator\\Desktop\\自有项目相关资料\\gen\\src\\main\\resources\\test.vm";
        String classPath = "test.vm";
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

    }

}

