package com.founderz.findemy.infra.s3.exception;

import com.founderz.findemy.global.exception.FindemyException;
import com.founderz.findemy.global.exception.error.ErrorCode;

public class ImageUploadFailedException extends FindemyException {

    public static final FindemyException EXCEPTION =
            new ImageUploadFailedException();

    private ImageUploadFailedException() { super(ErrorCode.IMAGE_UPLOAD_FAIL);}
}
