package com.practice.graphql.domain.topic.facade

import com.practice.graphql.domain.topic.Topic
import com.practice.graphql.domain.topic.repository.TopicRepository
import com.practice.graphql.global.exception.collections.TopicNotExistException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class TopicFacade(
    private val topicRepository: TopicRepository
) {
    fun getById(id: Long): Topic =
        topicRepository.findByIdOrNull(id)
            ?: throw TopicNotExistException()
}