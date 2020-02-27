package com.cloud.logger2.printer;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 日志输出接口类
 */
public interface IPrinter {
    enum PRINTER_TYPE {
        DEBUG,
        WARN,
        ERROR
    }

    /**
     * 日志输入
     */
    void print(PRINTER_TYPE type, String message);
}
