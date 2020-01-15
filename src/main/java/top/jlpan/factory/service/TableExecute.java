package top.jlpan.factory.service;

import top.jlpan.model.support.AbstractSupport;
import top.jlpan.model.support.TableSupport;
import top.jlpan.model.table.Table;
import top.jlpan.model.temp.table.AbstractTableTemp;
import top.jlpan.utils.TableUtils;

import java.io.StringWriter;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2019/7/13 16:50
 */
public class TableExecute extends AbstractExecute {

    public TableExecute(AbstractSupport support) {
        super(support);
    }

    @Override
    public AbstractSupport execute(String tableName) {
        TableSupport support = (TableSupport) abstractSupport;
        AbstractTableTemp temp = support.getTableTemp();
        Table table = TableUtils.getTableInfo(tableName);
        StringWriter sw = execute(support.getMap(), temp.getTemplate(), table);
        writeFile(temp.getPath(table), sw);
        return support;
    }
}
