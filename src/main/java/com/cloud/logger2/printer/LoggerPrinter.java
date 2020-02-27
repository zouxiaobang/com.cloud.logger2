package com.cloud.logger2.printer;

import com.cloud.logger2.converter.def.DefaultConverter;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 普通Log打印
 */
public class LoggerPrinter implements IPrinter {
    public void print(PRINTER_TYPE type, String message) {
        String[] split = message.split(DefaultConverter.getDivider());
        System.out.print(printWithType(type));
        for (String msg : split) {
            System.out.println(msg);
        }
    }

    protected String printWithType(PRINTER_TYPE type) {
        if (type == PRINTER_TYPE.DEBUG) {
            return "【LOGGER2】 -- DEBUG" + DefaultConverter.getDivider();
        } else if (type == PRINTER_TYPE.WARN) {
            return "【LOGGER2】 -- WARN" + DefaultConverter.getDivider();
        } else if (type == PRINTER_TYPE.ERROR) {
            return "【LOGGER2】 -- ERROR" + DefaultConverter.getDivider();
        }

        return "";
    }
}
