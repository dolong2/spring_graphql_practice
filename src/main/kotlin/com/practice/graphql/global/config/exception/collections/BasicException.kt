package com.practice.graphql.global.config.exception.collections

import com.practice.graphql.global.config.exception.ErrorCode
import java.lang.RuntimeException

open class BasicException(
    val errorCode: ErrorCode,
): RuntimeException(
    errorCode.msg
)