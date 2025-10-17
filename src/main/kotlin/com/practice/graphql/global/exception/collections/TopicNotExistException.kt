package com.practice.graphql.global.exception.collections

import com.practice.graphql.global.exception.BasicException
import com.practice.graphql.global.exception.ErrorCode

class TopicNotExistException : BasicException(ErrorCode.TOPIC_NOT_EXIST)
