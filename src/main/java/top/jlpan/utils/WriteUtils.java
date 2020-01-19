package top.jlpan.utils;

import top.jlpan.config.GenConfig;

import java.io.*;
import java.util.Scanner;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2020/1/19 10:38
 */
public class WriteUtils {

    /**
     * 写文件
     *
     * @param path
     * @param sw
     */
    public static void writeFile(StringWriter sw, String path) {
        try {
            System.out.println("路径:" + path + " 生成中....");
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("文件路径:" + path + " 不存在,正在新建...");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
            } else {
                while (true) {
                    System.out.println("文件路径:" + path + "已存在,请选择覆盖，取消或重命名？");
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
                        System.out.println("取消执行！");
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
