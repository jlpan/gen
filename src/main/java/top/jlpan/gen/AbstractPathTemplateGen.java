package top.jlpan.gen;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import top.jlpan.config.GenConfig;

import java.io.StringWriter;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 通过模板路径生成
 * @Date 2020/1/19 10:10
 */
public abstract class AbstractPathTemplateGen extends AbstractTemplateGen {

    /**
     * 模板路径
     */
    private String templatePath;

    AbstractPathTemplateGen(String templatePath) {
        checkPath(templatePath);
        this.templatePath = templatePath;
    }

    private void checkPath(String templatePath) {
        if (templatePath == null || "".equals(templatePath)) {
            throw new NullPointerException("templatePath can not be null");
        }
    }

    @Override
    public StringWriter gen() {
        Template tpl = Velocity.getTemplate(templatePath, GenConfig.Character);
        StringWriter sw = new StringWriter();
        tpl.merge(context, sw);
        return sw;
    }


}
