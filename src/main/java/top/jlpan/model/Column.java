package top.jlpan.model;


/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 数据库表列信息
 * @Date 2019/7/13 16:51
 */
public class Column {

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String dataType;

    /**
     * 列描述
     */
    private String columnComment;

    /**
     * Java 属性类型
     */
    private String attrType;

    /**
     * Java 属性名称 user_name => UserName
     */
    private String attrCapName;

    /**
     * Java 属性名称 user_name => userName
     */
    private String attrLowName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrCapName() {
        return attrCapName;
    }

    public void setAttrCapName(String attrCapName) {
        this.attrCapName = attrCapName;
    }

    public String getAttrLowName() {
        return attrLowName;
    }

    public void setAttrLowName(String attrLowName) {
        this.attrLowName = attrLowName;
    }

    @Override
    public String toString() {
        return "Column{" +
                "columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", attrType='" + attrType + '\'' +
                ", attrCapName='" + attrCapName + '\'' +
                ", attrLowName='" + attrLowName + '\'' +
                '}';
    }
}
