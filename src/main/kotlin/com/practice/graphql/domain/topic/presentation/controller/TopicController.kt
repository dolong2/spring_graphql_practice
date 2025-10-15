package com.practice.graphql.domain.topic.presentation.controller

import com.practice.graphql.domain.topic.presentation.dto.request.TopicRequest
import com.practice.graphql.domain.topic.presentation.dto.response.TopicListRes
import com.practice.graphql.domain.topic.presentation.dto.response.TopicRes
import com.practice.graphql.domain.topic.service.CreateTopicService
import com.practice.graphql.domain.topic.service.GetAllTopicService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import java.time.Duration

@Controller
class TopicController(
    private val createTopicService: CreateTopicService,
    private val getAllTopicService: GetAllTopicService
) {
    @MutationMapping
    fun createTopic(@Argument topicRequest: TopicRequest): TopicRes =
        createTopicService.execute(topicRequest)

    @SubscriptionMapping
    fun getTopicsRealTime(): Flux<TopicListRes> =
        Flux.interval(Duration.ofSeconds(5))
            .map { getAllTopicService.execute() }
}