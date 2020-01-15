package top.jlpan.utils;

import top.jlpan.config.GenConfig;
import top.jlpan.dao.TableDao;
import top.jlpan.model.table.Column;
import top.jlpan.model.table.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2019/7/13 12:59
 */
public class TableUtils {

    /**
     * 查询表信息
     * @param tableName
     * @return
     */
    public static Table getTableInfo(String tableName) {
        TableDao dao = TableDao.getInstance();
        Table table = dao.selectTableByName(tableName);
        List<Column> columns = dao.selectTableColumnsByName(tableName);
        return tableFormat(table, columns);
    }

    /**
     * 表对象格式化
     * @param table
     * @param columns
     * @return
     */
    private static Table tableFormat(Table table, List<Column> columns) {
        columns = columnTransJava(columns);
        table = tableTransJava(table, columns);
        return table;
    }

    /**
     * 数据库列格式化
     * @param columns
     * @return
     */
    private static List<Column> columnTransJava(List<Column> columns) {
        List<Column> columnList = new ArrayList<>();
        for (Column column : columns)
        {
            String javaName = StringUtils.toUnderScoreCase(column.getColumnName());
            column.setAttrLowName(javaName);
            String capName = StringUtils.capToCap(javaName);
            column.setAttrCapName(capName);
            String attrType = TypeToJavaUtils.javaTypeMap.get(column.getDataType());
            column.setAttrType(attrType);
            columnList.add(column);
        }
        return columnList;
    }

    /**
     * 数据库表数据格式化
     * @param table
     * @param columns
     * @return
     */
    private static Table tableTransJava(Table table, List<Column> columns) {
        String tableName = table.getTableName();
        tableName = tableName.substring(GenConfig.prefix.length());
        String className = StringUtils.toUnderScoreCase(tableName);
        String classCapName = StringUtils.capToCap(className);
        table.setClassCapName(classCapName);
        table.setClassLowName(className);
        table.setColumns(columns);
        table.setPrimaryKey(columns.get(0));
        return table;
    }
}
