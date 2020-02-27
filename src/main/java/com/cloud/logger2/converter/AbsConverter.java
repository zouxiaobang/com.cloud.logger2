package com.cloud.logger2.converter;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option
 */
public abstract class AbsConverter implements IConverter {
    protected static String sDivider;

    public static String getDivider() {
        return sDivider;
    }
}
