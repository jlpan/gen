package top.jlpan.gen;

import java.io.StringWriter;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName Execute
 * @Description 生成数据接口
 * @Date 2019/7/12 17:44
 */
public interface Gen {


    /**
     * 执行内容生成
     * @return 字节流
     */
    StringWriter gen();


}
