package com.cloud.logger2.printer.annotation;

import java.lang.annotation.*;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 主要判断是否在Release版本中也需要打印日志。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logger2Print {
    /**
     * 在Release版本中是否也打印该日志
     */
    boolean printWithRelease() default false;
}
