package top.jlpan.utils;

import top.jlpan.model.table.Table;
import org.apache.velocity.VelocityContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2019/7/13 13:01
 */
public class VelocityUtils {

    /**
     * 填充map
     * @param map
     * @return
     */
    public static VelocityContext getVelocityContext(HashMap<String, Object> map)
    {
        VelocityContext context = new VelocityContext();
        initMap(context, map);
        return context;
    }

    /**
     * 填充map和table
     * @param map
     * @return
     */
    public static VelocityContext getVelocityContext(HashMap<String, Object> map, Table table)
    {
        VelocityContext context = new VelocityContext();
        initMap(context, map);
        initTable(context, table);
        return context;
    }

    /**
     * 注入table到上下文
     * @param context
     * @param table
     */
    private static void initTable(VelocityContext context, Table table) {
        context.put("tableName", table.getTableName());
        context.put("tableComment", table.getTableComment());
        context.put("primaryKey", table.getPrimaryKey());
        context.put("classCapName", table.getClassCapName());
        context.put("classLowName", table.getClassLowName());
        context.put("columns", table.getColumns());
    }

    /**
     * 注入map到上下文
     * @param context
     * @param map
     */
    private static void initMap(VelocityContext context, HashMap<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
    }
}
