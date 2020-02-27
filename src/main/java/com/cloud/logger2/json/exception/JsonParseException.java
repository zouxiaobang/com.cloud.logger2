package com.cloud.logger2.json.exception;

import java.io.IOException;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option Json匹配错误
 */
public class JsonParseException extends IOException {
    public JsonParseException(String msg){
        super(msg);
    }
}
