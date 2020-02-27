package com.cloud.logger2.converter.def;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 可解析所有格式 -- 注意该Converter需要处于链表最后一位
 */
public class ObjectConverter extends DefaultConverter {
    public String convert(Object object) {
        return generateTag() + object;
    }
}
