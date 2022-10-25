package com.practice.graphql.global.exception

enum class ErrorCode(
    val msg: String
){
    /**
     * 400
     * bad request
     */
    BAD_REQUEST("올바르지 않은 요청입니다."),
    USER_ALREADY_EXIST("유저가 이미 존재합니다."),
    PASSWORD_NOT_CORRECT("패스워드가 일치하지 않습니다."),


    /**
     * 401
     * un authorized
     */
    UNAUTHORIZED("권한이 없습니다."),
    NOT_SAME_WRITER("자신의 게시물이 아닙니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다."),
    TOKEN_NOT_VALID("토큰이 유효하지 않습니다."),

    /**
     * 403
     * forbidden
     */
    FORBIDDEN("금지된 요청입니다."),

    /**
     * 404
     * not found
     */
    NOT_FOUND("리소스를 찾을 수 없습니다."),
    NOT_EXIST_MEMBER("해당 유저가 존재하지 않습니다."),
    POSTING_NOT_EXIST("해당 포스팅이 존재하지 않습니다."),

    /**
     * 500
     * internal error
     */
    INTERNAL_SERVER_ERROR("서버 내부 에러"),
}