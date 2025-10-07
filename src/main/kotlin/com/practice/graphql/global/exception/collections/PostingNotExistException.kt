package com.practice.graphql.global.exception.collections

import com.practice.graphql.global.exception.BasicException
import com.practice.graphql.global.exception.ErrorCode

class PostingNotExistException: BasicException(
    ErrorCode.POSTING_NOT_EXIST
)