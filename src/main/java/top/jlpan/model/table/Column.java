package top.jlpan.model.table;

import lombok.Data;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 数据库表列信息
 * @Date 2019/7/13 16:51
 */
@Data
public class Column {

    /** 字段名称 */
    private String columnName;

    /** 字段类型 */
    private String dataType;

    /** 列描述 */
    private String columnComment;

    /** Java 属性类型 */
    private String attrType;

    /** Java 属性名称 user_name => UserName */
    private String attrCapName;

    /** Java 属性名称 user_name => userName */
    private String attrLowName;


}
