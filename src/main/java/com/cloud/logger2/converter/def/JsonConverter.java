package com.cloud.logger2.converter.def;

import com.cloud.logger2.json.core.JsonParser;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 可解析Json格式
 */
public class JsonConverter extends DefaultConverter {
    public String convert(Object object) {
        if (object instanceof String){
            StringBuilder builder = new StringBuilder();
            String tag = generateTag();
            String jsonStr = (String) object;

            //开始打印
            builder.append(getDivider()).append(generateTag()).append(TOP_LINE).append(getDivider());

            //头部
            builder.append(generateTag()).append(VER_LINE).append("Json Format String")
                    .append("  ( ").append(JsonParser.isArray(jsonStr)?"JsonArray":"JsonObject")
                    .append(" )").append(getDivider());
            builder.append(generateTag()).append(MIDDLE_LINE).append(getDivider());

            try {
                String formatJson = JsonParser.formatJson(jsonStr);
                for (String msg: formatJson.split(getDivider())){
                    builder.append(tag).append(VER_LINE).append(msg).append(getDivider());
                }


                //尾部
                builder.append(generateTag()).append(BOTTOM_LINE).append(getDivider());
                return builder.toString();
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}
