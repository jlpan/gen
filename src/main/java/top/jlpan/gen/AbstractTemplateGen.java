package top.jlpan.gen;

import lombok.Data;
import org.apache.velocity.VelocityContext;
import top.jlpan.config.VelocityInitializer;
import top.jlpan.config.enums.InitType;
import top.jlpan.model.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 生成器超类
 * @Date 2020/1/19 10:03
 */
@Data
public abstract class AbstractTemplateGen implements Gen {

    /**
     * 模板填充map
     */
    HashMap<String, Object> fillMap;

    /**
     * 表信息
     */
    Table table;

    /**
     * 模板引擎上下文
     */
    VelocityContext context = new VelocityContext();


    AbstractTemplateGen() {

    }

    /**
     * 获取模板类型
     * @return 初始化类型
     */
    abstract InitType getType();

    /**
     * 初始化所有资源
     */
    public void initData(HashMap<String, Object> fillMap, Table table) {
        this.fillMap = fillMap == null ? new HashMap<String, Object>(1) : fillMap;
        this.table = table == null ? new Table() : table;
        initResource();
        initTable();
        initMap();
    }

    /**
     * 初始化模板文件加载路径方式
     */
    private void initResource() {
        if (getType() == InitType.CLASSPATH) {
            VelocityInitializer.initClassPathResource();
        }
        if (getType() == InitType.ABSOLUTE) {
            VelocityInitializer.initAbsoluteResource();
        }
        if (getType() == InitType.STREAM) {
            VelocityInitializer.initStreamResource();
        }
    }


    /**
     * 注入table到上下文
     */
    private void initTable() {
        context.put("tableName", table.getTableName());
        context.put("tableComment", table.getTableComment());
        context.put("primaryKey", table.getPrimaryKey());
        context.put("classCapName", table.getClassCapName());
        context.put("classLowName", table.getClassLowName());
        context.put("columns", table.getColumns());
    }

    /**
     * 注入map到上下文
     */
    private void initMap() {
        for (Map.Entry<String, Object> entry : fillMap.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
    }


}
