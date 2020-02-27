package com.cloud.logger2.converter.def;

import com.cloud.logger2.converter.AbsConverter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xb.zou
 * @date 2018/05/06
 * @option 默认解析器，可解析String格式
 */
public class DefaultConverter extends AbsConverter {
    protected static final String TOP_LINE = "╔═══════════════════════════════════════════════════════════════════════════════════════";
    protected static final String VER_LINE = "║";
    protected static final String BOTTOM_LINE = "╚═══════════════════════════════════════════════════════════════════════════════════════";
    protected static final String MIDDLE_LINE = VER_LINE + "---------------------------------------------------------------------------------------";
    private String mGlobalTag;

    public String convert(Object object) {
        if (object instanceof String) {
            String tag = generateTag();
            StringBuilder builder = new StringBuilder();
            String message = (String) object;
            for (String msg: message.split(getDivider())){
                builder.append(tag).append(" ").append(msg).append(getDivider());
            }

            return builder.toString();
        }
        return null;
    }

    public void setDivider(String divider) {
        sDivider = divider;
    }

    protected String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[4];
        String tag = "%s:【(%s.java:%d)#%s】 ";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        if (callerClazzName.contains("$")) {
            callerClazzName = callerClazzName.split("\\$")[0];
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        String date = simpleDateFormat.format(new Date());
        tag = String.format(tag, date, callerClazzName, caller.getLineNumber(), caller.getMethodName());
        if(null == mGlobalTag || "" .equals(mGlobalTag)){
            return tag;
        }else{
            return mGlobalTag + "-" + tag;
        }
    }
}
