package com.cloud.service;

import com.cloud.util.PropertiesReader;
import com.cloud.util.ThreadStateFlag;

public abstract class AbstractObserver implements Observer {
    protected void commonUpdate(Subject subject, String appType, String sendFlag, String
            sendPath, HashMap<String, CommonThread> threadMap) {
        String datatype = subject.getDataType();
        String sendWayList = PropertiesReader.getProp("sendWayList");
        String[] sendWayArray = sendWayList.split(",");
        if (sendWayList == null) {
            return;
        }
        for (String sendWay : sendWayArray) {
//            读取配置文件 判断该发送方式是否开启
            if (!"on".equals(PropertiesReader.getProp(sendWay + "toggle"))) {
                continue;
            }
//            定义线程的唯一性
            String threadKey = appType + "" + datatype + "" + sendWay;
            if (threadMap.containsKey(threadKey)) {
                CommonThread thread = threadMap.get(threadKey);
                if (thread.isAlive()) {
                    continue;
                }
            }
            CommonThread thread = new CommonThread(sendWay, ThreadStateFlag.getlnstance(threadKey), datatype, sendFlag, appType, sendPath);
            thread.setName(threadKey);
            thread.start();
            threadMap.put(threadKey, thread);

        }
    }
}
