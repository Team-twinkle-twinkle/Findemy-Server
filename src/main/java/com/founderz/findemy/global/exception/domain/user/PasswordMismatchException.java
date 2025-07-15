package com.founderz.findemy.global.exception.domain.user;

import com.founderz.findemy.global.exception.FindemyException;
import com.founderz.findemy.global.exception.error.ErrorCode;

public class PasswordMismatchException extends FindemyException {

    public static final FindemyException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}