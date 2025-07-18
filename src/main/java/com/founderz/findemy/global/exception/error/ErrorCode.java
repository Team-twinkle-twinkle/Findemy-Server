package com.founderz.findemy.global.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // user
    USER_NOT_FOUND(404, "일치하는 유저을 찾을 수 없습니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 일치하지 않습니다."),

    // academy
    ACADEMY_NOT_FOUND(404, "일치하는 학원을 찾을 수 없습니다."),

    // favorite
    FAVORITE_NOT_FOUND(404, "찜한 학원을 찾을 수 없습니다."),

    // jwt
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "검증되지 않은 토큰입니다."),

    //s3
    IMAGE_NOT_FOUND(404, "이미지를 찾을 수 없음"),
    FAILED_UPLOAD(1001, "업로드 실패"),
    FAILED_DELETE(1002, "삭제 실패"),

    // general
    BAD_REQUEST(400, "프론트 탓..."),
    INTERNAL_SERVER_ERROR(500, "서버 탓...");

    private final int statusCode;
    private final String message;
}

