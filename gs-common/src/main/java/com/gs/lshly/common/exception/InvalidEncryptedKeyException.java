package com.gs.lshly.common.exception;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2019-10-17
 * Time: 08:56
 * Description: No Description
 */
public class InvalidEncryptedKeyException extends Exception {

    public InvalidEncryptedKeyException() {
    }

    public InvalidEncryptedKeyException(String msg) {
        super(msg);
    }
}
