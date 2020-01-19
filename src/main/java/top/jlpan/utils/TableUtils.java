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
 * @Description
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
    private static final Map<String, String> javaTypeMap = new HashMap<String, String>();

    static {
        initJavaTypeMap();
    }


    /**
     * 数据库列格式化
     *
     * @param columns
     * @return
     */
    public static List<Column> columnTransJava(List<Column> columns) {
        List<Column> columnList = new ArrayList<>();
        for (Column column : columns) {
            String javaName = toUnderScoreCase(column.getColumnName());
            column.setAttrLowName(javaName);
            String capName = capToCap(javaName);
            column.setAttrCapName(capName);
            String attrType = javaTypeMap.get(column.getDataType());
            column.setAttrType(attrType);
            columnList.add(column);
        }
        return columnList;
    }

    /**
     * 数据库表数据格式化
     *
     * @param table
     * @param columns
     * @return
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
     * @param param
     * @return
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
     * @param str
     * @return
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
        javaTypeMap.put("tinyint", "Integer");
        javaTypeMap.put("smallint", "Integer");
        javaTypeMap.put("mediumint", "Integer");
        javaTypeMap.put("int", "Integer");
        javaTypeMap.put("integer", "integer");
        javaTypeMap.put("bigint", "Long");
        javaTypeMap.put("float", "Float");
        javaTypeMap.put("double", "Double");
        javaTypeMap.put("decimal", "BigDecimal");
        javaTypeMap.put("bit", "Boolean");
        javaTypeMap.put("char", "String");
        javaTypeMap.put("varchar", "String");
        javaTypeMap.put("tinytext", "String");
        javaTypeMap.put("text", "String");
        javaTypeMap.put("mediumtext", "String");
        javaTypeMap.put("longtext", "String");
        javaTypeMap.put("time", "Date");
        javaTypeMap.put("date", "Date");
        javaTypeMap.put("datetime", "Date");
        javaTypeMap.put("timestamp", "Date");
        javaTypeMap.put("decimal ", "BigDecimal");
    }


}
