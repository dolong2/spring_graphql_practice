package com.practice.graphql.global.exception.collections

import com.practice.graphql.global.exception.ErrorCode

class MemberNotSameException: BasicException(
    ErrorCode.NOT_SAME_WRITER
)