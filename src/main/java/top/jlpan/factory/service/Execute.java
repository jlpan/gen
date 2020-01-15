package top.jlpan.factory.service;

import top.jlpan.model.support.*;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName Execute
 * @Description
 * @Date 2019/7/12 17:44
 */
public interface Execute {

    /**
     * 执行普通生成
     * @return
     */
    AbstractSupport execute();

    /**
     * 执行表内容生成
     * @param tableName
     * @return
     */
    AbstractSupport execute(String tableName);

}
