package top.jlpan.dao;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName AbstractObjectPool
 * @Description
 * @Date 2019/7/17 9:44
 */
public abstract class AbstractObjectPool<T> {

    /**
     * 创建对象
     * @return
     */
    protected abstract T create();

    /**
     * 验证对象有效性
     * @param t
     * @param createTime
     * @return
     */
    public abstract boolean validate(T t, long createTime);

    /**
     * 使对象失效
     * @param t
     */
    public abstract void expire(T t);

    /**
     * 正在使用的，保存新建的资源
     */
    private ConcurrentHashMap<T, Long> used;

    /**
     * 未被使用的，保存释放的、未失效的资源，池子里面保存有效可用的对象
     */
    private ConcurrentHashMap<T, Long> pool;

    /**
     * 最大连接数
     */
    private static int MAX_CONNECT_SIZE = 10;

    /**
     * 最大生存时间，这个时间也决定了创建连接的频率
     */
    public static int MAX_KEEP_ALIVE_TIME = 3000;

    /**
     * 获得资源
     */
    public synchronized T getResource() {
        T t = null;
        // 初始化
        if (null == pool) {
            pool = new ConcurrentHashMap<>(10);
        }
        if (null == used) {
            used = new ConcurrentHashMap<>(10);
        }

        // 超过最大连接数，等待（注意：这里可以加一个拒绝策略，即超时拒绝连接还是继续等待）
        while (used.size() >= MAX_CONNECT_SIZE) {
            try {
                this.wait(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 默认先从池子里面去取
        if (pool.size() > 0) {
            for (Entry<T, Long> entry : pool.entrySet()) {
                t = entry.getKey();
                if (null != t) {
                    break;
                }
            }
            pool.remove(t);
        }
        // 在池子中未取到，创建一个新的
        if (null == t) {
            t = create();
        }
        used.put(t, System.currentTimeMillis());
        this.notifyAll();
        return t;
    }

    /**
     * 释放某个资源
     */
    public synchronized void release(T t) {
        if (null == t) {
            return;
        }

        while (used.size()==0) {
            try {
                this.wait(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 判断是否过有效期
        if (validate(t, used.get(t))) {
            // 放入池中
            pool.put(t, System.currentTimeMillis());
            used.remove(t);
        } else {
            expire(t);
            used.remove(t);
        }

        this.notifyAll();
    }

}