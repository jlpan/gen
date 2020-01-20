package top.jlpan.template;

import java.io.InputStream;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 模板接口定义
 * @Date 2020/1/20 14:41
 */
public interface Template {

    /**
     * 获取模板输入流
     * @return 输入流
     */
    InputStream getTemplate();

    /**
     * 获取模板字符
     * @return 字符
     */
    String getTemplateString();

}
