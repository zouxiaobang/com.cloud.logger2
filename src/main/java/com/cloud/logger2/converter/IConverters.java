package com.cloud.logger2.converter;

import java.util.List;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 转换器聚集类
 */
public interface IConverters {
    /**
     * 增加新的Converter类
     * @param converter 该类可以为自定义转换器
     */
    void addConverter(IConverter converter);

    /**
     * 增加新的Converter类
     * @param converter 该类可以为自定义转换器
     * @param index 新的转换器位于链中位置
     */
    void addConverter(IConverter converter, int index );

    /**
     * 获取所有的Converter
     */
    List<IConverter> getConverterList();

    /**
     * 将对象转换为字符串
     */
    String convert(Object obj);

    /**
     * 为所有转换器设置换行符
     */
    void setDivider(String divider);
}
