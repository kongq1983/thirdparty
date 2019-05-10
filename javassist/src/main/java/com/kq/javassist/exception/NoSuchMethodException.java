package com.kq.javassist.exception;

/**
 * NoSuchMethodException
 *
 * @author kq
 * @date 2019-05-10
 */
public class NoSuchMethodException extends RuntimeException {
    private static final long serialVersionUID = -2725364246023268766L;

    public NoSuchMethodException() {
        super();
    }

    public NoSuchMethodException(String msg) {
        super(msg);
    }
}