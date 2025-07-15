package com.founderz.findemy.global.exception.domain.academy;

import com.founderz.findemy.global.exception.FindemyException;
import com.founderz.findemy.global.exception.error.ErrorCode;

public class AcademyNotFoundException extends FindemyException {

    public static final FindemyException EXCEPTION = new AcademyNotFoundException();

    public AcademyNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}