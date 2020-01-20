package top.jlpan.data;

import top.jlpan.model.Column;
import top.jlpan.model.Table;

import java.util.List;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 数据库查询service
 * @Date 2020/1/16 14:02
 */
public interface ITableService {

    /**
     * 根据表名查询表信息
     *
     * @param tableName 表名
     * @return 表数据
     */
    Table selectTableByName(String tableName);

    /**
     * 根据表名查询列信息
     *
     * @param tableName 表名
     * @return 列数据
     */
    List<Column> selectTableColumnsByName(String tableName);

}
