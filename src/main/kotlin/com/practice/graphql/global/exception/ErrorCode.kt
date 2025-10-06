package com.practice.graphql.global.exception

enum class ErrorCode(
    val code: String,
    val msg: String
){
    // http error
    BAD_REQUEST("A-01", "올바르지 않은 요청입니다."),
    UNAUTHORIZED("A-02", "권한이 없습니다."),
    FORBIDDEN("A-03", "금지된 요청입니다."),
    NOT_FOUND("A-04", "리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR("A-99", "서버 내부 에러"),

    // auth
    TOKEN_EXPIRED("B-01", "토큰이 만료되었습니다."),
    TOKEN_NOT_VALID("B-02", "토큰이 유효하지 않습니다."),
    PASSWORD_NOT_CORRECT("B-03", "패스워드가 일치하지 않습니다."),


    // user
    USER_ALREADY_EXIST("C-01", "유저가 이미 존재합니다."),
    NOT_EXIST_MEMBER("C-02", "해당 유저가 존재하지 않습니다."),

    // posting
    NOT_SAME_WRITER("D-01", "자신의 게시물이 아닙니다."),
    POSTING_NOT_EXIST("D-02", "해당 포스팅이 존재하지 않습니다."),
}