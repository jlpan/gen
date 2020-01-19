package top.jlpan.gen;

import top.jlpan.config.enums.InitType;


/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 加载绝对路径模板文件
 * @Date 2020/1/19 10:10
 */
public class AbsoluteTemplateGen extends AbstractPathTemplateGen {

    public AbsoluteTemplateGen(String templatePath) {
        super(templatePath);
    }

    @Override
    InitType getType() {
        return InitType.ABSOLUTE;
    }
}
