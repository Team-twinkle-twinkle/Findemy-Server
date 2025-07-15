package com.founderz.findemy.global.exception.domain.auth;

import com.founderz.findemy.global.exception.FindemyException;
import com.founderz.findemy.global.exception.error.ErrorCode;

public class InvalidTokenException extends FindemyException {

    public static final FindemyException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}