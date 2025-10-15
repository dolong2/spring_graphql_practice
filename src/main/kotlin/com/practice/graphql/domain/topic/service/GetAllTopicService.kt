package com.practice.graphql.domain.topic.service

import com.practice.graphql.domain.topic.presentation.dto.response.TopicListRes
import com.practice.graphql.domain.topic.repository.TopicRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetAllTopicService(
    private val topicRepository: TopicRepository
) {
    @Transactional(readOnly = true)
    fun execute() =
        topicRepository.findAll()
            .map { it.toResponse() }
            .let { TopicListRes(it) }
}