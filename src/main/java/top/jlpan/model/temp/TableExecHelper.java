package top.jlpan.model.temp;

import lombok.Setter;
import top.jlpan.factory.ExecuteFactory;
import top.jlpan.model.support.TableSupport;
import top.jlpan.model.temp.table.*;
import top.jlpan.model.temp.table.controller.ApiTemp;
import top.jlpan.model.temp.table.controller.ControllerTemp;
import top.jlpan.model.temp.table.controller.VoTemp;
import top.jlpan.model.temp.table.service.*;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName TableExecHelper
 * @Description
 * @Date 2019/7/17 10:10
 */
@Setter
@EqualsAndHashCode
public class TableExecHelper {

    private String tableName;
    private TableSupport support = new TableSupport();

    /** service */
    private static ModelTemp modelTemp = new ModelTemp();
    private static MapperTemp mapperTemp = new MapperTemp();
    private static XmlTemp xmlTemp = new XmlTemp();
    private static ServiceTemp serviceTemp = new ServiceTemp();
    private static ServiceImplTemp serviceImplTemp = new ServiceImplTemp();

    /** controller */
    private static ApiTemp apiControllerTemp = new ApiTemp();
    private static ControllerTemp controllerTemp = new ControllerTemp();
    private static VoTemp voTemp = new VoTemp();

    private static List<AbstractTableTemp> temps = new ArrayList<>();

    static {
        temps.add(modelTemp);
        temps.add(mapperTemp);
        temps.add(xmlTemp);
        temps.add(serviceTemp);
        temps.add(serviceImplTemp);
        temps.add(apiControllerTemp);
        temps.add(controllerTemp);
        temps.add(voTemp);

    }

    public TableExecHelper(HashMap<String, Object> map, String tableName) {
        this.support.setMap(map);
        this.tableName = tableName;
    }

    /**
     * 执行生成
     */
    public void exec() {
        for (AbstractTableTemp temp : temps) {
            if (check(temp)) {
                support.setTableTemp(temp);
                ExecuteFactory.create(support).execute(tableName);
            }
        }
    }

    private boolean check(AbstractTableTemp temp) {
        return temp.getTemplate() != null && !"".equals(temp.getTemplate());
    }

    public void setModelTemplate(String template) {
        modelTemp.setTemplate(template);
    }


}
