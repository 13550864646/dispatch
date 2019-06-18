package com.cloud.strategy;

import com.cloud.util.Log;

public class StrategyContext {
    private static SendStrategy mqStrategy = null;
    private static SendStrategy postStrategy = null;

    static {
        mqStrategy = new MqStrategy();
        postStrategy = new PostStrategy();
    }

    public static boolean sendData(String data, String url, String appType, String sendWay) {
        if ("MqStrategy".equals(sendWay)) {
            Log.debug(" MqStrategy 方式发送数据");
            return mqStrategy.send(data, url, appType);
        } else if ("PostStrategy".endsWith(sendWay)) {
            Log.debug(" PostStrategy 方式发送数据");
            return postStrategy.send(data, url, appType);
        } else {
            Log.info("没有指定发送方式！ ! !sendWay :" + sendWay);
            return false;
        }
    }
}
