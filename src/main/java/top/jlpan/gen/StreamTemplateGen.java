package top.jlpan.gen;

import org.apache.velocity.app.Velocity;
import top.jlpan.config.GenConfig;
import top.jlpan.config.enums.InitType;
import top.jlpan.override.StreamTemplate;

import java.io.InputStream;
import java.io.StringWriter;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 流模板读取
 * @Date 2020/1/19 10:30
 */
public class StreamTemplateGen extends AbstractTemplateGen {

    /**
     * 模板流
     */
    private InputStream is;

    public StreamTemplateGen(InputStream is) {
        checkPath(is);
        this.is = is;
    }

    private void checkPath(InputStream is) {
        if (is == null) {
            throw new NullPointerException("stream can not be null");
        }
    }

    @Override
    public StringWriter gen() {
        StreamTemplate tpl = (StreamTemplate) Velocity.getTemplate("", GenConfig.Character);
        tpl.setStream(is);
        tpl.process();
        StringWriter sw = new StringWriter();
        tpl.merge(context, sw);
        return sw;
    }

    @Override
    InitType getType() {
        return InitType.STREAM;
    }


}
