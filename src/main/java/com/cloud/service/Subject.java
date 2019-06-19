package com.cloud.service;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据主题抽象类 Subject ，定义观察者的集合和业务相关的参数
 */
public abstract class Subject {
    public List<Observer> observers = new ArrayList<>();
    //数据包类型
    protected String dataType;
    //数据包APP类型
    protected String appType;

    public void add(Observer observer) {
        this.observers.add(observer);
    }

    public void del(Observer observer) {
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
