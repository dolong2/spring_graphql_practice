package com.practice.graphql.global.exception

import java.lang.RuntimeException

open class BasicException(
    val errorCode: ErrorCode,
): RuntimeException(
    errorCode.msg
)