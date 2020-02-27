package com.cloud.logger2.converter.def;

import java.util.Map;
import java.util.Set;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 可解析Map格式
 */
public class MapConverter extends DefaultConverter {
    public String convert(Object object) {
        if (object instanceof Map){
            Map map = (Map) object;
            if (map == null){
                return null;
            }
            String divider = getDivider();
            StringBuilder builder = new StringBuilder();

            //开始打印
            builder.append(getDivider()).append(generateTag()).append(TOP_LINE).append(divider);

            //头部
            String mapClazzName = map.getClass().getName();
            builder.append(generateTag()).append(VER_LINE).append(mapClazzName).append("(size = ").append(map.size()).append(")").append(divider);
            builder.append(generateTag()).append(MIDDLE_LINE).append(divider);

            Set set = map.keySet();
            for (Object key: set){
                Object value = map.get(key);
                if (value == null){
                    value = "NULL";
                }
                builder.append(generateTag()).append(VER_LINE).append("    ");
                builder.append(key.toString()).append(" -> ").append(value.toString()).append(divider);
            }

            //尾部
            builder.append(generateTag()).append(BOTTOM_LINE).append(divider);
            return builder.toString();
        }
        return null;
    }
}
