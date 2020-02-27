package com.cloud.logger2.converter;

import com.cloud.logger2.converter.custom.CustomConverter;
import com.cloud.logger2.converter.def.DefaultConverters;
import com.cloud.logger2.converter.def.JsonConverter;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option
 */
public class DefConvertersTest {

    @Test
    public void testAddConverters() {
        DefaultConverters dc = new DefaultConverters();
        dc.addConverter(new JsonConverter());
        Assert.assertEquals(dc.getConverterList().size(), 5);
        Assert.assertEquals(dc.getConverterList().get(0).getClass().getSimpleName(), "JsonConverter");
    }

    @Test
    public void testAddCustomConverter() {
        DefaultConverters dc = new DefaultConverters();
        dc.addConverter(new CustomConverter());
        Assert.assertEquals(dc.getConverterList().size(), 6);
        Assert.assertEquals(dc.getConverterList().get(0).getClass().getSimpleName(), "CustomConverter");

        dc.addConverter(new CustomConverter(), 3);
        Assert.assertEquals(dc.getConverterList().size(), 6);
        Assert.assertEquals(dc.getConverterList().get(3).getClass().getSimpleName(), "CustomConverter");


        dc.addConverter(new CustomConverter(), 10);
        Assert.assertEquals(dc.getConverterList().size(), 6);
        Assert.assertEquals(dc.getConverterList().get(4).getClass().getSimpleName(), "CustomConverter");
    }
}
