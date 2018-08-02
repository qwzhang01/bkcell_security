package com.bkcell.security.common.cache;

import cn.hutool.core.collection.ConcurrentHashSet;

import java.util.Set;

public class LocalCache {
    public static Set ACCESS_URL = new ConcurrentHashSet();
}