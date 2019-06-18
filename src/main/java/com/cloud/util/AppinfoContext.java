package com.cloud.util;

import java.util.HashMap;
import java.util.List;

/**
 * ＊应用系统上下文信息，
 * ＊保存各个 app appType sendFlag sendPath appT oggle 等相关信息
 * * <br ＞系统启动时即从数据库读取到内存，以减少数据库访问次数，若信息有更新，则需要及时
 * 刷新此上下文
 */
public class AppinfoContext {

    public static HashMap<String, HashMap<String, String>> info = new HashMap<String, HashMap<String, String>>();

    static {
        initAppInfo();
    }

    public static void initAppInfo() {
        info.clear();
        String sql = " SELECT appName as appType , appSendFlag as sendFlag, appUrl as sendPath , appToggle,appQueueName FROM product";
        List<HashMap<String, String>> list = C3P0Util.getData(sql);
        if (list != null && list.size() > 0) {
            for (HashMap<String, String> map : list) {
                info.put(map.get("appType"), map);
            }
        }
    }

    public static String getPropertyByApp(String appType, String prop) {
        initAppInfo();
        HashMap<String, String> appinfo = info.get(appType);
        if (appinfo != null) {
            return appinfo.get(prop);
        } else {
            return null;
        }
    }
}
