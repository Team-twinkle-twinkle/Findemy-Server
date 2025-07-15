package com.founderz.findemy.global.exception.domain.favorite;

import com.founderz.findemy.global.exception.FindemyException;
import com.founderz.findemy.global.exception.error.ErrorCode;

public class FavoriteNotFoundException extends FindemyException {

    public static final FindemyException EXCEPTION = new FavoriteNotFoundException();

    public FavoriteNotFoundException() {
        super(ErrorCode.FAVORITE_NOT_FOUND);
    }
}