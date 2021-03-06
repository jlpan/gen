package top.jlpan.config;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 模板初始化
 * @Date 2019/1/7 9:40
 */
public class VelocityInitializer {

    /**
     * 加载classpath路径下的模板文件
     */
    public static void initClassPathResource() {
        Properties p = new Properties();
        try {
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty(Velocity.ENCODING_DEFAULT, GenConfig.Character);
            p.setProperty(Velocity.OUTPUT_ENCODING, GenConfig.Character);
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载绝对路径模板文件
     */
    public static void initAbsoluteResource() {
        Properties p = new Properties();
        try {
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "");
            p.setProperty(Velocity.ENCODING_DEFAULT, GenConfig.Character);
            p.setProperty(Velocity.OUTPUT_ENCODING, GenConfig.Character);
            Velocity.init(p);
            Velocity.init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 加载流模板文件
     */
    public static void initStreamResource() {
        Properties p = new Properties();
        try {
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, " .");
            p.setProperty(Velocity.ENCODING_DEFAULT, GenConfig.Character);
            p.setProperty(Velocity.OUTPUT_ENCODING, GenConfig.Character);
            p.setProperty("resource.manager.class", "top.jlpan.override.StreamResourceManagerImpl");
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
