package top.jlpan.factory;

import top.jlpan.factory.service.*;
import top.jlpan.model.support.*;


/**
 * @author panliang
 * @version 1.0
 * @ProjectName GenFactory
 * @Description
 * @Date 2019/1/8 13:35
 */
public class ExecuteFactory {

    public static Execute create(AbstractSupport support) {
        if (support instanceof TableSupport) {
            return new TableExecute(support);
        }
        return null;
    }

}
