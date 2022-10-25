package com.practice.graphql.global.config.exception.collections

import com.practice.graphql.global.config.exception.ErrorCode

class PostingNotExistException: BasicException(
    ErrorCode.POSTING_NOT_EXIST
)