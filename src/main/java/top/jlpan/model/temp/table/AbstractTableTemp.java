package top.jlpan.model.temp.table;

import lombok.Data;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2019/7/13 16:40
 */
@Data
public abstract class AbstractTableTemp {

    protected String prefix = "";

    protected String suffix = "";

    protected String extension = "";

    protected String template = "";

}
