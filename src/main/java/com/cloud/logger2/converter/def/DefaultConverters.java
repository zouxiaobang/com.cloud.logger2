package com.cloud.logger2.converter.def;

import com.cloud.logger2.converter.IConverter;
import com.cloud.logger2.converter.IConverters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option IConverters默认实现 -- 一般使用该类即可，如果想要实现不同的管理流程，可自行定义
 */
public class DefaultConverters implements IConverters {
    private List<IConverter> converterList = new ArrayList<IConverter>();
    public DefaultConverters() {
        addConverter(new ObjectConverter());
        addConverter(new MapConverter());
        addConverter(new ListConverter());
        addConverter(new DefaultConverter());
        addConverter(new JsonConverter());
    }

    public void addConverter(IConverter converter) {
        // 默认添加到链中第一位
        addConverter(converter, 0);
    }

    public void addConverter(IConverter converter, int index) {
        boolean isContains = isContain(converter.getClass().getSimpleName());
        if (isContains) {
            removeConverter(converter);
        }

        int size = converterList.size();
        if (index >= size && size != 0) {
            // 此处需要注意，-1是为了保证最后一个Converter为ObjectConverter
            converterList.add(size - 1, converter);
        } else {
            converterList.add(index, converter);
        }
    }

    public List<IConverter> getConverterList() {
        return converterList;
    }

    public String convert(Object obj) {
        String message = null;
        for (IConverter converter : converterList) {
            message = converter.convert(obj);
            if (message != null) {
                break;
            }
        }

        return message;
    }

    public void setDivider(String divider) {
        for (IConverter converter : converterList) {
            converter.setDivider(divider);
        }
    }

    private boolean isContain(String converterName) {
        for (IConverter converter : converterList) {
            if (converterName.equals(converter.getClass().getSimpleName())) {
                return true;
            }
        }

        return false;
    }

    private void removeConverter(IConverter converter) {
        Iterator<IConverter> iterator = converterList.iterator();
        while (iterator.hasNext()) {
            IConverter iConverter = iterator.next();
            if (converter.getClass().getSimpleName().equals(iConverter.getClass().getSimpleName())) {
                iterator.remove();
            }
        }
    }
}
