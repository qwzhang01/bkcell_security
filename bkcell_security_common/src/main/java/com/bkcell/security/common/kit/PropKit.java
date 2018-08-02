package com.bkcell.security.common.kit;

import cn.hutool.setting.dialect.Props;

public class PropKit {
    private static Props props = new Props("application.properties");

    public static String getStr(String key) {
        return props.getStr(key).trim();
    }

    public static Integer getInt(String key) {
        return Integer.parseInt(getStr(key));
    }

    public static Boolean getBoolean(String key) {
        return props.getBool(key);
    }

    public static Boolean getBoolean(String key, boolean defaultValue) {
        return props.getBool(key, defaultValue);
    }

    public static void use(String path) {
        props = new Props(path);
    }
}