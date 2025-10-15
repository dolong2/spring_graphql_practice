package com.practice.graphql.domain.topic.service

import com.practice.graphql.domain.topic.presentation.dto.request.TopicRequest
import com.practice.graphql.domain.topic.presentation.dto.response.TopicRes
import com.practice.graphql.domain.topic.repository.TopicRepository
import com.practice.graphql.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service

@Service
class CreateTopicService(
    private val topicRepository: TopicRepository,
    private val getCurrentMemberUtil: CurrentMemberUtil
) {
    fun execute(topicRequest: TopicRequest): TopicRes {
        getCurrentMemberUtil.getCurrentMember()

        val topic = topicRequest.toEntity()
            .run { topicRepository.save(this) }

        return topic.toResponse()
    }
}