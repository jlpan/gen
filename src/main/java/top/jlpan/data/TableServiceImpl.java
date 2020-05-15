package top.jlpan.data;

import top.jlpan.model.Column;
import top.jlpan.model.Table;
import top.jlpan.utils.JdbcUtils;
import top.jlpan.utils.TableUtils;

import java.util.List;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName TableDao
 * @Description 表数据查询实现
 * @Date 2019/1/8 13:38
 */
public class TableServiceImpl implements ITableService {

    /**
     * 根据表名查询表信息
     *
     * @param tableName 表名
     * @return 表数据
     */
    @Override
    public Table selectTableByName(String tableName) {

        String sql = "select table_name, table_comment, create_time, update_time " +
                "from information_schema.tables " +
                "where table_schema = (select database()) " +
                "and table_name = ?";
        List<Table> tables = JdbcUtils.executeQuery(sql, Table.class, tableName);
        Table table;
        if (null != tables && tables.size() != 1) {
            return null;
        }
        table = tables.get(0);
        List<Column> columnList = selectTableColumnsByName(tableName);
        return TableUtils.tableTransJava(table, columnList);
    }

    /**
     * 根据表名查询列信息
     *
     * @param tableName 表名
     * @return 列数据
     */
    @Override
    public List<Column> selectTableColumnsByName(String tableName) {
        String sql = "select column_name, data_type, column_comment, extra " +
                "from information_schema.columns " +
                "where table_name = ? " +
                "and table_schema = (select database()) " +
                "order by ordinal_position";
        List<Column> columnList = JdbcUtils.executeQuery(sql, Column.class, tableName);
        return TableUtils.columnTransJava(columnList);
    }


}
