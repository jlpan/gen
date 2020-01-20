package top.jlpan.template;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 模板处理超类
 * @Date 2020/1/20 14:42
 */
public abstract class AbstractTemplate implements Template {


    /**
     * 获取模板输入流
     * @return 输入流
     */
    public InputStream getTemplate() {
        return new ByteArrayInputStream(getTemplateString().getBytes());
    }



}
