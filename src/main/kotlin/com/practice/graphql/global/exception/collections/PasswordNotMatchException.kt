package com.practice.graphql.global.exception.collections

import com.practice.graphql.global.exception.BasicException
import com.practice.graphql.global.exception.ErrorCode

class PasswordNotMatchException: BasicException(
    ErrorCode.PASSWORD_NOT_CORRECT
)