package top.jlpan.utils;

import top.jlpan.data.jdbc.JdbcConnection;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName JdbcUtils
 * @Description jdbc操作封装
 * @Date 2019/1/8 13:52
 */
public class JdbcUtils {

    /**
     * 执行查询的通用SQL
     *
     * @param sql   sql
     * @param obj   返回数据类型
     * @param param 传入参数
     * @param <T>   泛型
     * @return 返回数据集
     */
    public static <T> ArrayList<T> executeQuery(String sql, Class<T> obj, Object... param) {
        Connection conn = JdbcConnection.getConnection();
        ResultSet rs;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    st.setObject((i + 1), param[i]);
                }
            }
            rs = st.executeQuery();
            ArrayList<T> list = putResult(rs, obj);
            conn.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 把ResultSet的结果放到java对象中
     *
     * @param rs  ResultSet的结果
     * @param obj java对象
     * @param <T> 泛型
     * @return 返回集合
     */
    private static <T> ArrayList<T> putResult(ResultSet rs, Class<T> obj) {
        try {
            ArrayList<T> arrayList = new ArrayList<T>();
            ResultSetMetaData metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            while (rs.next()) {
                T newInstance = obj.newInstance();
                for (int i = 1; i <= count; i++) {
                    String name = metaData.getColumnName(i).toLowerCase();
                    name = toJavaField(name);
                    String substring = name.substring(0, 1);
                    String replace = name.replaceFirst(substring, substring.toUpperCase());
                    Class<?> type;
                    try {
                        type = obj.getDeclaredField(name).getType();
                    } catch (NoSuchFieldException e) {
                        continue;
                    }
                    Method method = obj.getMethod("set" + replace, type);

                    if (type.isAssignableFrom(String.class)) {
                        method.invoke(newInstance, rs.getString(i));
                    } else if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
                        method.invoke(newInstance, rs.getInt(i));
                    } else if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)) {
                        method.invoke(newInstance, rs.getBoolean(i));
                    } else if (type.isAssignableFrom(Date.class)) {
                        method.invoke(newInstance, rs.getDate(i));
                    } else if (type.isAssignableFrom(BigDecimal.class)) {
                        method.invoke(newInstance, rs.getBigDecimal(i));
                    }
                }
                arrayList.add(newInstance);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 数据库命名格式转java命名格式
     *
     * @param str 字符串
     * @return 返回结果
     */
    private static String toJavaField(String str) {
        String[] split = str.split("_");
        StringBuilder builder = new StringBuilder();
        builder.append(split[0]);
        if (split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                String string = split[i];
                String substring = string.substring(0, 1);
                split[i] = string.replaceFirst(substring, substring.toUpperCase());
                builder.append(split[i]);
            }
        }
        return builder.toString();
    }
}
