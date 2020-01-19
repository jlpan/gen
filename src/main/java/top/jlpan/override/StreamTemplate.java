package top.jlpan.override;

import org.apache.velocity.Template;
import org.apache.velocity.exception.*;
import org.apache.velocity.runtime.parser.ParseException;

import java.io.*;


/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 重写template 增加stream流读取数据
 * @Date 2020/1/17 17:19
 */
public class StreamTemplate extends Template {

    private InputStream stream;

    public void setStream(InputStream resource) {
        this.stream = resource;
    }

    @Override
    public boolean process()
            throws ResourceNotFoundException, ParseErrorException {
        data = null;
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(stream, encoding));
            data = rsvc.parse(br, name);
        } catch (ParseException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        initDocument();
        return true;
    }


}
