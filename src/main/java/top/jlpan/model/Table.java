package top.jlpan.model;

import java.util.List;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 数据库表信息
 * @Date 2019/7/13 16:51
 */
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


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Column getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Column primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getClassCapName() {
        return classCapName;
    }

    public void setClassCapName(String classCapName) {
        this.classCapName = classCapName;
    }

    public String getClassLowName() {
        return classLowName;
    }

    public void setClassLowName(String classLowName) {
        this.classLowName = classLowName;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", primaryKey=" + primaryKey +
                ", columns=" + columns +
                ", classCapName='" + classCapName + '\'' +
                ", classLowName='" + classLowName + '\'' +
                '}';
    }
}
