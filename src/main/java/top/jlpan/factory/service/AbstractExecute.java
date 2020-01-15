package top.jlpan.factory.service;

import top.jlpan.config.GenConfig;
import top.jlpan.config.VelocityInitializer;
import top.jlpan.config.enums.InitType;
import top.jlpan.model.support.AbstractSupport;
import top.jlpan.model.table.Table;
import top.jlpan.utils.VelocityUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2019/7/13 13:27
 */
public abstract class AbstractExecute implements Execute {

    protected AbstractSupport abstractSupport;

    public AbstractExecute(AbstractSupport support) {
        this.abstractSupport = support;
    }

    @Override
    public AbstractSupport execute() {
        return null;
    }

    @Override
    public AbstractSupport execute(String tableName) {
        return null;
    }

    public StringWriter execute(HashMap<String, Object> map, String templatePath) {
        initResource();
        VelocityContext context = VelocityUtils.getVelocityContext(map);
        return render(context, templatePath);
    }

    StringWriter execute(HashMap<String, Object> map, String templatePath, Table table) {
        initResource();
        VelocityContext context = VelocityUtils.getVelocityContext(map, table);
        return render(context, templatePath);
    }

    private StringWriter render(VelocityContext context, String templatePath) {
        Template tpl = Velocity.getTemplate(templatePath, GenConfig.Character);
        StringWriter sw = new StringWriter();
        tpl.merge(context, sw);
        return sw;
    }


    private void initResource() {
        if (GenConfig.resource == InitType.RELATIVE) {
            VelocityInitializer.initRelativeResource();
        }
        if (GenConfig.resource == InitType.ABSOLUTE) {
            VelocityInitializer.initAbsoluteResource();
        }
    }

    /**
     * 写文件
     * @param path
     * @param sw
     */
    @SuppressWarnings("all")
    void writeFile(String path, StringWriter sw) {
        try {
            System.out.println("路径:"+path+" 生成中....");
            File file = new File(path);
            if(!file.exists()) {
                System.out.println("文件路径:"+path+" 不存在,正在新建...");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
            } else {
                while (true) {
                    System.out.println("文件路径:"+path+"已存在,是否选择覆盖，取消或重命名？");
                    System.out.println("1:覆盖 2:取消 3:重命名");
                    Scanner scanner = new Scanner(System.in);
                    String an = scanner.nextLine();
                    if ("1".equals(an)) {
                        System.out.println("覆盖中...");
                        file.delete();
                        System.out.println("覆盖完毕！");
                        break;
                    }
                    if ("2".equals(an)) {
                        System.out.println("跳过执行！");
                        return;
                    }
                    if ("3".equals(an)) {
                        int index = path.lastIndexOf(GenConfig.separator);
                        String fileName = path.substring(index + 1);
                        String head = path.substring(0, index + 1);
                        String copy = head + "Copy" + fileName;
                        file = new File(copy);
                        System.out.println("已重命名！");
                        break;
                    }
                    System.out.println("输入错误，请重新输入！");
                }
            }
            OutputStream out = new FileOutputStream(file);
            out.write(sw.toString().getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
