package top.jlpan.utils;

import top.jlpan.config.GenConfig;
import top.jlpan.model.Column;
import top.jlpan.model.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 数据库表工具类
 * @Date 2019/7/13 12:59
 */
public class TableUtils {

    /**
     * 下划线
     */
    private static final String SEPARATOR = "_";

    /**
     * java 类型映射集合
     */
    private static final Map<String, String> JAVA_TYPE_MAP = new HashMap<String, String>();

    static {
        initJavaTypeMap();
    }


    /**
     * 数据库列格式化
     *
     * @param columns 列数据
     * @return 格式化结果
     */
    public static List<Column> columnTransJava(List<Column> columns) {
        List<Column> columnList = new ArrayList<>();
        for (Column column : columns) {
            String javaName = toUnderScoreCase(column.getColumnName());
            column.setAttrLowName(javaName);
            String capName = capToCap(javaName);
            column.setAttrCapName(capName);
            String attrType = JAVA_TYPE_MAP.get(column.getDataType());
            column.setAttrType(attrType);
            columnList.add(column);
        }
        return columnList;
    }

    /**
     * 数据库表数据格式化
     *
     * @param table   表数据
     * @param columns 列数据
     * @return 返回的结合表结构数据
     */
    public static Table tableTransJava(Table table, List<Column> columns) {
        String tableName = table.getTableName();
        tableName = tableName.substring(GenConfig.prefix.length());
        String className = toUnderScoreCase(tableName);
        String classCapName = capToCap(className);
        table.setClassCapName(classCapName);
        table.setClassLowName(className);
        table.setColumns(columns);
        table.setPrimaryKey(columns.get(0));
        return table;
    }


    /**
     * 下划线转驼峰命名
     *
     * @param param 参数
     * @return 返回值
     */
    private static String toUnderScoreCase(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile(SEPARATOR).matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();

    }


    /**
     * 首字符大写
     *
     * @param str 字符
     * @return 转换后
     */
    private static String capToCap(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
    }


    /**
     * 返回状态映射
     */
    private static void initJavaTypeMap() {
        JAVA_TYPE_MAP.put("tinyint", "Integer");
        JAVA_TYPE_MAP.put("smallint", "Integer");
        JAVA_TYPE_MAP.put("mediumint", "Integer");
        JAVA_TYPE_MAP.put("int", "Integer");
        JAVA_TYPE_MAP.put("integer", "integer");
        JAVA_TYPE_MAP.put("bigint", "Long");
        JAVA_TYPE_MAP.put("float", "Float");
        JAVA_TYPE_MAP.put("double", "Double");
        JAVA_TYPE_MAP.put("decimal", "BigDecimal");
        JAVA_TYPE_MAP.put("bit", "Boolean");
        JAVA_TYPE_MAP.put("char", "String");
        JAVA_TYPE_MAP.put("varchar", "String");
        JAVA_TYPE_MAP.put("tinytext", "String");
        JAVA_TYPE_MAP.put("text", "String");
        JAVA_TYPE_MAP.put("mediumtext", "String");
        JAVA_TYPE_MAP.put("longtext", "String");
        JAVA_TYPE_MAP.put("time", "Date");
        JAVA_TYPE_MAP.put("date", "Date");
        JAVA_TYPE_MAP.put("datetime", "Date");
        JAVA_TYPE_MAP.put("timestamp", "Date");
        JAVA_TYPE_MAP.put("decimal ", "BigDecimal");
    }


}
