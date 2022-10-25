package com.practice.graphql.global.exception.collections

import com.practice.graphql.global.exception.ErrorCode

class MemberAlreadyExistException: BasicException(
    ErrorCode.USER_ALREADY_EXIST
)