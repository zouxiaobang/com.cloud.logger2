package com.cloud.logger2.converter;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 转换器接口类
 */
public interface IConverter {
    /**
     * 将对象转换为字符串
     */
    String convert(Object object);

    /**
     * 设置分频器
     */
    void setDivider(String divider);
}
