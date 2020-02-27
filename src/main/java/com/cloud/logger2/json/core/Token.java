package com.cloud.logger2.json.core;

/**
 * @author xb.zou
 * @date 2018/05/06
 */
public class Token {
    private JsonEnum type;
    private String value;

    public Token(JsonEnum type, String value) {
        this.type = type;
        this.value = value;
    }

    public JsonEnum getType() {
        return type;
    }

    public void setType(JsonEnum type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
