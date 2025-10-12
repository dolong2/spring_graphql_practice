package com.practice.graphql.domain.topic.presentation.dto.request

import com.practice.graphql.domain.topic.Topic

data class TopicRequest(
    val name: String
) {
    fun toEntity(): Topic =
        Topic(
            name = name
        )
}