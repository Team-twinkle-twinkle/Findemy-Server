package com.founderz.findemy.global.exception.domain.student;

import com.founderz.findemy.global.exception.FindemyException;
import com.founderz.findemy.global.exception.error.ErrorCode;

public class UserNotFoundException extends FindemyException {

    public static final FindemyException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}