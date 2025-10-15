package com.practice.graphql.domain.topic

import com.practice.graphql.domain.topic.presentation.dto.response.TopicRes
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String
) {
    fun toResponse(): TopicRes =
        TopicRes(
            id = id,
            name = name
        )
}