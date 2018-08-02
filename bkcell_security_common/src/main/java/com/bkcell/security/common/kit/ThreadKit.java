package com.bkcell.security.common.kit;

/**
 * 线程工具
 */
public class ThreadKit {
    private static final ThreadLocal<Integer> TL = new ThreadLocal<>();

    public static void setThreadLocalAppId(int appId) {
        TL.set(appId);
    }

    public static void removeThreadLocalAppId() {
        TL.remove();
    }

    public static Integer getAppId() {
        Integer appId = TL.get();
        if (appId == null) {
            appId = 0;
        }
        return appId;
    }
}
