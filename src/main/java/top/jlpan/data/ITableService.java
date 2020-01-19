package top.jlpan.data;

import top.jlpan.model.Column;
import top.jlpan.model.Table;

import java.util.List;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2020/1/16 14:02
 */
public interface ITableService {

    /**
     * 根据表名查询表信息
     *
     * @param tableName
     * @return
     */
    Table selectTableByName(String tableName);

    /**
     * 根据表名查询列信息
     *
     * @param tableName
     * @return
     */
    List<Column> selectTableColumnsByName(String tableName);

}
