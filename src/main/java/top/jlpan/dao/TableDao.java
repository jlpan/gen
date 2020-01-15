package top.jlpan.dao;

import top.jlpan.model.table.Column;
import top.jlpan.model.table.Table;
import top.jlpan.utils.JdbcUtils;

import java.util.List;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName TableDao
 * @Description
 * @Date 2019/1/8 13:38
 */
public class TableDao {

    private static volatile TableDao tableDao;

    public static TableDao getInstance() {
        if (tableDao == null) {
            synchronized (TableDao.class) {
                if (tableDao == null) {
                    tableDao = new TableDao();
                }
            }
        }
        return tableDao;
    }

    /**
     * 根据表名查询表信息
     * @param tableName
     * @return
     */
    public Table selectTableByName(String tableName) {

        String sql = "select table_name, table_comment, create_time, update_time " +
                "from information_schema.tables " +
                "where table_schema = (select database()) " +
                "and table_name = ?";
        List<Table> tables = JdbcUtils.executeQuery(sql, Table.class, tableName);
        if(null != tables && tables.size() == 1) {
            return tables.get(0);
        }
        return null;
    }

    /**
     * 根据表名查询列信息
     * @param tableName
     * @return
     */
    public List<Column> selectTableColumnsByName(String tableName) {
        String sql = "select column_name, data_type, column_comment " +
                "from information_schema.columns " +
                "where table_name = ? " +
                "and table_schema = (select database()) " +
                "order by ordinal_position";
        return JdbcUtils.executeQuery(sql, Column.class, tableName);
    }
}
