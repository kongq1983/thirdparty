package com.kq.javassist.exception;

/**
 * NoSuchPropertyException
 *
 * @author kq
 * @date 2019-05-10
 */
public class NoSuchPropertyException extends RuntimeException {

    private static final long serialVersionUID = -2725364246023268766L;

    public NoSuchPropertyException() {
        super();
    }

    public NoSuchPropertyException(String msg) {
        super(msg);
    }
}