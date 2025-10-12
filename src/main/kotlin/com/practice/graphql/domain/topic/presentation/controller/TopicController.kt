package com.practice.graphql.domain.topic.presentation.controller

import com.practice.graphql.domain.topic.presentation.dto.request.TopicRequest
import com.practice.graphql.domain.topic.presentation.dto.response.TopicResponse
import com.practice.graphql.domain.topic.service.CreateTopicService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class TopicController(
    val createTopicService: CreateTopicService
) {
    @MutationMapping
    fun createTopic(@Argument topicRequest: TopicRequest): TopicResponse =
        createTopicService.execute(topicRequest)
}