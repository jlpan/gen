package top.jlpan;

import top.jlpan.config.GenConfig;
import top.jlpan.data.TableServiceImpl;
import top.jlpan.gen.ClassPathTemplateGen;
import top.jlpan.model.Table;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 代码生成管理配置
 * 使用时只需要自定义作者（author）即可
 * 选择需要的表执行即可
 * @Date 2020/4/15 11:19
 */
@SuppressWarnings("all")
public class GenMain {

    /* 项目地址  */
    private static String root = System.getProperty("user.dir");
    /* FIXME 作者  */
    private static String author = "panliang";
    /* 命名空间 */
    private static String namespace = "xxx.xxx.xxx.xxx";
    /* 版本 */
    private static String version = "1.0";
    /* 合并标识 */
    private static String origin = "CODE_OLD_AND_NEW_DISTINCTION_FLAG";

    /* entity 生成路径 && 模板路径 */
    private static String entityPath = "\\farm-service\\src\\main\\java\\com\\jimi\\farm\\upms\\entity\\{a}.java";
    private static String entityTemplate = "vm/entity.java.vm";

    /* mapper 生成路径 && 模板路径 */
    private static String mapperPath = "\\farm-service\\src\\main\\java\\com\\jimi\\farm\\upms\\dao\\{a}Mapper.java";
    private static String mapperTemplate = "vm/mapper.java.vm";

    /* xml 生成路径 && 模板路径 */
    private static String xmlPath = "\\farm-service\\src\\main\\resources\\mapper\\{a}Mapper.xml";
    private static String xmlTemplate = "vm/mapper.xml.vm";

    /* map 数据 */
    private static HashMap<String, Object> map = new HashMap<>();


    public static void main(String[] args) throws IOException {
        HashMap<String, String> table = new HashMap<>();
        table.put("t_user", "User");

        /* 通用参数配置 */
        GenConfig.url = "jdbc:mysql://127.0.0.1:3306/gen?usdeUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        GenConfig.username = "root";
        GenConfig.password = "root";
        GenConfig.prefix = "t_";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("createDate", format.format(new Date()));
        map.put("author", author);
        map.put("version", version);
        map.put("namespace", namespace);

        for (Map.Entry<String, String> entry : table.entrySet()) {
            genAll(entry.getKey(), entry.getValue());
        }
    }

    static void genAll(String tableName, String modelName) throws IOException {
        genEntity(tableName, modelName);
        genMapper(tableName, modelName);
        genXml(tableName, modelName);
    }

    static void genMapper(String tableName, String modelName) {
        /* 声明生成路径 */
        String path = getPath(mapperPath, modelName);
        /* 加载模板 */
        ClassPathTemplateGen mapperGen = new ClassPathTemplateGen(mapperTemplate);
        /* 表数据声明 */
        Table table = new TableServiceImpl().selectTableByName(tableName);
        /* 注入参数 */
        mapperGen.initData(map, table);
        /* 写文件 */
        writeFile(path, mapperGen.gen());
    }

    static void genEntity(String tableName, String modelName) {
        /* 表数据声明 */
        Table table = new TableServiceImpl().selectTableByName(tableName);
        /* 声明生成路径 */
        String path = getPath(entityPath, modelName);
        /* 加载模板 */
        ClassPathTemplateGen entityGen = new ClassPathTemplateGen(entityTemplate);
        /* 注入参数 */
        entityGen.initData(map, table);
        /* 写文件 */
        writeFile(path, entityGen.gen());

    }

    static void genXml(String tableName, String modelName) {
        /* 表数据声明 */
        Table table = new TableServiceImpl().selectTableByName(tableName);
        /* 声明生成路径 */
        String path = getPath(xmlPath, modelName);
        /* 加载模板 */
        ClassPathTemplateGen xmlGen = new ClassPathTemplateGen(xmlTemplate);
        /* 注入参数 */
        xmlGen.initData(map, table);
        /* 写文件 */
        writeFile(path, xmlGen.gen());
    }


    /**
     * 写文件
     *
     * @param path
     * @param sw
     */
    static void writeFile(String path, StringWriter sw) {

        try {
            String newString = sw.toString();
            System.out.println("路径:" + path + " 生成中....");
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("文件路径:" + path + " 不存在,正在新建...");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
            } else {
                while (true) {
                    System.out.println("文件路径:" + path + "已存在,选择覆盖,取消,根据标识合并或重命名？");
                    System.out.println("1:覆盖 2:取消 3:合并 4:重命名");
                    Scanner scanner = new Scanner(System.in);
                    String an = scanner.nextLine();
                    if ("1".equals(an)) {
                        System.out.println("覆盖中...");
                        file.delete();
                        System.out.println("覆盖完毕！");
                        break;
                    }
                    if ("2".equals(an)) {
                        System.out.println("跳过执行！");
                        return;
                    }
                    if ("3".equals(an)) {

                        FileInputStream stream = new FileInputStream(file);
                        int size = stream.available();
                        byte[] buffer = new byte[size];
                        stream.read(buffer);
                        stream.close();
                        String oldString = new String(buffer);
                        int oldIndex = oldString.lastIndexOf(origin);
                        int newIndex = newString.lastIndexOf(origin);
                        if (oldIndex == -1 || newIndex == -1) {
                            System.out.println("不存在" + origin + ",跳过执行！");
                            return;
                        }
                        stream.close();
                        String oldNeed = oldString.substring(oldIndex + origin.length());
                        String newNeed = newString.substring(0, newIndex + origin.length() + 1);
                        newString = newNeed + oldNeed;
                        System.out.println("已合并！");
                        break;
                    }
                    if ("4".equals(an)) {
                        int index = path.lastIndexOf(".");
                        String fileName = path.substring(index);
                        String head = path.substring(0, index);
                        String copy = head + "Copy" + fileName;
                        file = new File(copy);
                        System.out.println("已重命名！");
                        break;
                    }
                    System.out.println("输入错误，请重新输入！");
                }
            }

            OutputStream out = new FileOutputStream(file);
            out.write(newString.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    static String getPath(String path, String model) {
        return root + path.replace("{a}", model);
    }
}
