package com.cloud.service;

public class SubjectFactory {
    public static Subject getSubject(String appTypes, String dataType) {
        DataSaveSubject subject = new DataSaveSubject();
        subject.setAppType(appTypes);
        subject.setDataType(dataType);
        if (appTypes != null && !"".equals(appTypes)) {
            String[] app = appTypes.split(";");
            for (String appType : app) {
//                从库中查询 app 的发送标识以及发送路径
                Observer obs = (Observer) new CommonObserver(
                        AppinfoContext.getPropertyByApp(appType, "sendFlag"),
                        appType, AppinfoContext.getPropertyByApp(appType, "sendPath")
                );
                subject.add(obs);
            }
        }
        return subject;
    }
}
