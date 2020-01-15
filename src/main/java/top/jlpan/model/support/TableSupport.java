package top.jlpan.model.support;

import top.jlpan.model.temp.table.AbstractTableTemp;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2019/7/13 16:51
 */
public class TableSupport extends AbstractSupport {

    private AbstractTableTemp tableTemp;

    public AbstractTableTemp getTableTemp() {
        return tableTemp;
    }

    public void setTableTemp(AbstractTableTemp tableTemp) {
        this.tableTemp = tableTemp;
    }
}
