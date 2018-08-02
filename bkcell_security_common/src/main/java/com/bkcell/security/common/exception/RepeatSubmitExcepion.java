package com.bkcell.security.common.exception;

public class RepeatSubmitExcepion extends RuntimeException {
    public RepeatSubmitExcepion() {
        super("重复提交");
    }
}
