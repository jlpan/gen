package top.jlpan.model;

import lombok.Data;

import java.util.List;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 数据库表信息
 * @Date 2019/7/13 16:51
 */
@Data
public class Table {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 表的主键列信息
     */
    private Column primaryKey;

    /**
     * 表的其余列名
     */
    private List<Column> columns;

    /**
     * 首字符大写类名 UserController
     */
    private String classCapName;

    /**
     * 首字符小写类名 userController
     */
    private String classLowName;

}
