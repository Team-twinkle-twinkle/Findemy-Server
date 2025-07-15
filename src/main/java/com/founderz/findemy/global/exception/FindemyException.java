package com.founderz.findemy.global.exception;

import com.founderz.findemy.global.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindemyException extends RuntimeException {
    private final ErrorCode errorCode;
}
