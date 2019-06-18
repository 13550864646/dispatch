package com.cloud.service;

/**
 * 观察者接口
 */
public interface Observer {
    /**
     * 数据跟新方法
     * @param subject
     */
    public void update(Subject subject);

}
