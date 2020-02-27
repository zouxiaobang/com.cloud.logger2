package com.cloud.logger2.json.core;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option Json匹配
 */
public class JsonParser {
    private static final String TAB_STRING = "    ";
    private static final String NEXT_LINE = "\r\n";

    private static JsonEnum mSaveType;
    private static boolean isArray = false;

    public static String formatJson(String jsonStr) throws Exception {
        StringBuilder builder = new StringBuilder();


        int tabCount = 0;
        Tokenizer tokenizer = new Tokenizer(new BufferedReader(new StringReader(jsonStr)));
        tokenizer.tokenize();

        while (tokenizer.hasNext()){
            Token token = tokenizer.next();
            JsonEnum type = token.getType();
            String value = token.getValue();

            if (type == JsonEnum.START_ARRAY || type == JsonEnum.START_OBJ){
                builder.append(NEXT_LINE);
                for (int i = 0;i <= tabCount; i++){
                    builder.append(TAB_STRING);
                }
                builder.append(value);
                tabCount ++;
            } else if (type == JsonEnum.END_ARRAY || type == JsonEnum.END_OBJ){
                builder.append(NEXT_LINE);
                tabCount --;
                for (int i = 0;i <= tabCount; i++){
                    builder.append(TAB_STRING);
                }
                builder.append(value);
            } else if (type == JsonEnum.COMMA){
                builder.append(" ").append(value);
            } else if (type == JsonEnum.COLON){
                builder.append(" -> ");
            } else {
                if (mSaveType != JsonEnum.COLON){
                    builder.append(NEXT_LINE);
                    for (int i = 0;i <= tabCount; i++){
                        builder.append(TAB_STRING);
                    }
                }
                builder.append(value);
            }

            mSaveType = type;
        }


        return builder.toString();
    }


    public static boolean isArray(String jsonStr){
        if (jsonStr.startsWith("[")){
            isArray = true;
        }
        return isArray;
    }
}
