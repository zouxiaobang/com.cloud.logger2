package com.cloud.logger2.json.core;

/**
 * @author xb.zou
 * @date 2018/05/06
 *
 * [
 *  {
 *     "date" : 20160517,
 *     "id" : 1
 *  },
 *  {
 *     "date" : 20160517,
 *     "id" : 1
 *  }
 * ]
 **/
public enum JsonEnum {
    /**
     * 对象的起始符 -- ｛
     */
    START_OBJ,
    /**
     * 对象的终止符 -- ｝
     */
    END_OBJ,
    /**
     * 数组的起始符 -- ｛
     */
    START_ARRAY,
    /**
     * 数组的终止符 -- ｝
     */
    END_ARRAY,
    /**
     * 类型 -- null
     */
    NULL,
    /**
     * 类型 -- 数字
     */
    NUMBER,
    /**
     * 类型 -- 字符串
     */
    STRING,
    /**
     * 类型 -- 布尔
     */
    BOOLEAN,
    /**
     * 符号 -- 冒号
     */
    COLON,
    /**
     * 符号 -- 逗号
     */
    COMMA,
    /**
     * JSON数据结束符
     */
    END_DOC
}
