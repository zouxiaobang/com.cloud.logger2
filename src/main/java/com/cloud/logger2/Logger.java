package com.cloud.logger2;

import com.cloud.logger2.converter.IConverters;
import com.cloud.logger2.printer.IPrinter;
import com.cloud.logger2.printer.annotation.Logger2Print;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 日志类
 */
public class Logger {

    public static class Builder {
        private IConverters mConverters;
        private IPrinter[] mPrinters;
        private VERSION_TYPE mVersionType;
        private String mDivider;

        public Builder converters(IConverters converters) {
            this.mConverters = converters;
            return this;
        }

        public Builder printers(IPrinter... printers) {
            this.mPrinters = printers;
            return this;
        }

        public Builder versionType(VERSION_TYPE versionType) {
            this.mVersionType = versionType;
            return this;
        }

        public Builder divider(String divider) {
            this.mDivider = divider;
            return this;
        }

        public void build() {
            Logger logger = new Logger();
            logger.initLogger(mConverters, mPrinters);
            logger.setDivider((mDivider == null || "".equals(mDivider.trim())) ? "\r\n" : mDivider);
            logger.setVersionType(mVersionType);
        }
    }

    public enum VERSION_TYPE {
        DEBUG,
        RELEASE
    }

    private static IConverters sConverters;
    private static IPrinter[] sPrinters;
    /**
     * 是否为DEBUG版本
     */
    private static VERSION_TYPE versionType;

    private Logger() {
    }

    private void setConverters(IConverters converters){
        sConverters = converters;
    }

    private void initLogger(IConverters converters, IPrinter... printers){
        setConverters(converters);
        sPrinters = printers;
    }

    private void setDivider(String divider){
        sConverters.setDivider(divider);
    }

    private void setVersionType(VERSION_TYPE versionType) {
        Logger.versionType = versionType;
    }

    public static void d(Object object){
        if (!canPrint()) {
            return;
        }

        String message = sConverters.convert(object);
        for (IPrinter printer: sPrinters){
            printer.print(IPrinter.PRINTER_TYPE.DEBUG, message);
        }
    }

    public static void dWithoutCheck(Object object) {
        String message = sConverters.convert(object);
        for (IPrinter printer: sPrinters){
            printer.print(IPrinter.PRINTER_TYPE.DEBUG, message);
        }
    }

    public static void w(Object object){
        if (!canPrint()) {
            return;
        }

        String message = sConverters.convert(object);
        for (IPrinter printer: sPrinters){
            printer.print(IPrinter.PRINTER_TYPE.WARN, message);
        }
    }

    public static void wWithoutCheck(Object object) {
        String message = sConverters.convert(object);
        for (IPrinter printer: sPrinters){
            printer.print(IPrinter.PRINTER_TYPE.WARN, message);
        }
    }

    public static void e(Object object){
        if (!canPrint()) {
            return;
        }

        String message = sConverters.convert(object);
        for (IPrinter printer: sPrinters){
            printer.print(IPrinter.PRINTER_TYPE.ERROR, message);
        }
    }

    public static void eWithoutCheck(Object object) {
        String message = sConverters.convert(object);
        for (IPrinter printer: sPrinters){
            printer.print(IPrinter.PRINTER_TYPE.ERROR, message);
        }
    }

    private static boolean canPrint() {
        if (versionType == null) {
            throw new RuntimeException("必须设置当前版本类型");
        }

        return versionType == VERSION_TYPE.DEBUG;
    }
}
