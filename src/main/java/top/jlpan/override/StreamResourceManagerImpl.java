package top.jlpan.override;

import org.apache.velocity.runtime.resource.*;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description 重写resource管理，兼容流模板读取
 * @Date 2020/1/17 17:47
 */
public class StreamResourceManagerImpl extends ResourceManagerImpl {

    /**
     * 获取资源
     *
     * @param resourceName 资源名
     * @param resourceType 资源类型
     * @param encoding     编码
     * @return 资源
     */
    @Override
    public Resource getResource(final String resourceName, final int resourceType, final String encoding) {
        return loadResource(resourceName, resourceType, encoding);
    }

    /**
     * 加载资源
     *
     * @param resourceName 资源名
     * @param resourceType 资源类型
     * @param encoding     编码
     * @return 资源
     */
    @Override
    protected Resource loadResource(String resourceName, int resourceType, String encoding) {
        Resource resource = createResource(resourceName, resourceType);
        resource.setRuntimeServices(rsvc);
        return resource;
    }

    /**
     * 新建流模板资源文件
     *
     * @param resourceName 资源名
     * @param resourceType 资源类型
     * @return 资源
     */
    @Override
    protected Resource createResource(String resourceName, int resourceType) {
        return new StreamTemplate();
    }


}
