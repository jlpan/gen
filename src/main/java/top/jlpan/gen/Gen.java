package top.jlpan.gen;

import java.io.StringWriter;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName Execute
 * @Description
 * @Date 2019/7/12 17:44
 */
public interface Gen {


    /**
     * 执行内容生成
     * @return
     */
    StringWriter gen();


}
