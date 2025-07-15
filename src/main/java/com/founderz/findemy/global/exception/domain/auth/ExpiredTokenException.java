package com.founderz.findemy.global.exception.domain.auth;

import com.founderz.findemy.global.exception.FindemyException;
import com.founderz.findemy.global.exception.error.ErrorCode;

public class ExpiredTokenException extends FindemyException {

    public static final FindemyException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}