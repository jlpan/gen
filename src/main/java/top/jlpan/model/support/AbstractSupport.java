package top.jlpan.model.support;

import java.util.HashMap;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description
 * @Date 2019/7/13 11:58
 */
public abstract class AbstractSupport {

    private HashMap<String, Object> map = new HashMap<>();

    public HashMap<String, Object> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }

}
