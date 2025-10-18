package com.practice.graphql.global.exception.collections

import com.practice.graphql.global.exception.BasicException
import com.practice.graphql.global.exception.ErrorCode

class AccessTokenExpiredException : BasicException(
    ErrorCode.TOKEN_EXPIRED
)