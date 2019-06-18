package com.cloud.util;

import java.util.HashMap;

public class ThreadStateFlag {
    private static HashMap<String, ThreadStateFlag> iMap = new HashMap<String, ThreadStateFlag>();

    private ThreadStateFlag() {
    }

    public static synchronized ThreadStateFlag getlnstance(String key) {
        if (iMap.containsKey(key)) {
            return iMap.get(key);
        } else {
            iMap.put(key, new ThreadStateFlag());
            return iMap.get(key);
        }
    }
}
