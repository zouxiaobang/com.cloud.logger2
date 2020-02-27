package com.cloud.logger2.logger;

import com.cloud.logger2.Logger;
import com.cloud.logger2.converter.def.DefaultConverters;
import com.cloud.logger2.printer.LoggerPrinter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option Logger类测试
 */
public class DebugLoggerTest {

    @Before
    public void before() {
        new Logger.Builder()
                .converters(new DefaultConverters())
                .printers(new LoggerPrinter())
                .versionType(Logger.VERSION_TYPE.DEBUG)
                .build();
    }

    @Test
    public void testD() {
        Logger.d("test");
        String json = "{\"version\" : \"1.0\"}";
        Logger.d(json);
        List<String> sl = new ArrayList<String>(3);
        sl.add("John");
        sl.add("Marry");
        sl.add("Kyle");
        Logger.d(sl);
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("name", "Kyle");
        map.put("age", "18");
        Logger.d(map);
    }

    @Test
    public void testW() {
        Logger.w("test");
        String json = "{\"version\" : \"1.0\"}";
        Logger.w(json);
        List<String> sl = new ArrayList<String>(3);
        sl.add("John");
        sl.add("Marry");
        sl.add("Kyle");
        Logger.w(sl);
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("name", "Kyle");
        map.put("age", "18");
        Logger.w(map);
    }

    @Test
    public void testE() {
        Logger.e("test");
        String json = "{\"version\" : \"1.0\"}";
        Logger.e(json);
        List<String> sl = new ArrayList<String>(3);
        sl.add("John");
        sl.add("Marry");
        sl.add("Kyle");
        Logger.e(sl);
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("name", "Kyle");
        map.put("age", "18");
        Logger.e(map);
    }
}
