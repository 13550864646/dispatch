package com.cloud.util;

import org.apache.log4j.Logger;

public class Log {
    static Logger logger = null;

    static {
        logger = Logger.getLogger(Log.class);
    }

    /**
     * @param log
     */
    public static void debug(String log) {
        logger.debug(log);
    }

    /**
     * @param log
     */
    public static void info(String log) {
        logger.info(log);
    }

    /**
     * @param log
     */
    public static void error(String log) {
        logger.error(log);
    }
}
