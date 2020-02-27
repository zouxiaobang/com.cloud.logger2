package com.cloud.logger2.converter.def;

import java.util.List;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 可解析List格式
 */
public class ListConverter extends DefaultConverter {
    public String convert(Object object) {
        if (object instanceof List) {
            List list = (List) object;
            if (list == null || list.size() == 0) {
                return null;
            }
            String divider = getDivider();
            StringBuilder builder = new StringBuilder();

            //开始打印
            builder.append(getDivider()).append(generateTag()).append(TOP_LINE).append(divider);

            //头部
            String listClazzName = list.getClass().getName();
            builder.append(generateTag()).append(VER_LINE).append(listClazzName).append("(size = ").append(list.size()).append(")").append(divider);
            builder.append(generateTag()).append(MIDDLE_LINE).append(divider);

            builder.append(generateTag()).append(VER_LINE).append("    ");
            builder.append(list).append(divider);

            //尾部
            builder.append(generateTag()).append(BOTTOM_LINE).append(divider);
            return builder.toString();
        }

        return null;
    }
}
