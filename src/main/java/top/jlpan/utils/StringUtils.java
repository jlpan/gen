package top.jlpan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName StringUtils
 * @Description
 * @Date 2019/1/7 10:09
 */
public class StringUtils {

    /** 下划线 */
    private static final String SEPARATOR = "_";

    /**
     * 首字符大写
     * @param str
     * @return
     */
    public static String capToCap(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        else {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
    }

    /**
     * 下划线转驼峰命名
     * @param param
     * @return
     */
    public static String toUnderScoreCase(String param) {
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile(SEPARATOR).matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();

    }

}
