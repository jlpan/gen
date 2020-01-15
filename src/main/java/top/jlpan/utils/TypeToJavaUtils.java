package top.jlpan.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName TypeToJavaUtils
 * @Description
 * @Date 2019/1/7 10:37
 */
public class TypeToJavaUtils {

    public static Map<String, String> javaTypeMap = new HashMap<String, String>();

    static
    {
        initJavaTypeMap();
    }

    /**
     * 返回状态映射
     */
    private static void initJavaTypeMap()
    {
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
