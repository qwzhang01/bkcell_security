package com.bkcell.security.common.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(-1, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(-1, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R validateOk() {
        return validateOk("");
    }

    public static R validateOk(String msg) {
        R r = new R();
        r.put("info", msg);
        r.put("status", "y");
        return r;
    }

    public static R validateNo() {
        return validateNo("验证失败");
    }

    public static R validateNo(String msg) {
        R r = new R();
        r.put("info", msg);
        r.put("status", "n");
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}