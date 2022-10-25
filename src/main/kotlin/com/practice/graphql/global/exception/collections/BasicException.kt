package com.practice.graphql.global.exception.collections

import com.practice.graphql.global.exception.ErrorCode
import java.lang.RuntimeException

open class BasicException(
    val errorCode: ErrorCode,
): RuntimeException(
    errorCode.msg
)