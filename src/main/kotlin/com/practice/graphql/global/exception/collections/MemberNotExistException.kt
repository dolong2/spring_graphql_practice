package com.practice.graphql.global.exception.collections

import com.practice.graphql.global.exception.ErrorCode

class MemberNotExistException: BasicException(
    ErrorCode.NOT_EXIST_MEMBER
)