package com.cloud.strategy;

public interface MessagePublisher {
    public static Logger log = Logger.getLogger(MessagePublisher.class);

    public boolean sendByQuene(String msg, String queneName);
}
