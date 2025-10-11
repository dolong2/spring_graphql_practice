package com.practice.graphql.domain.topic.repository

import com.practice.graphql.domain.topic.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {
}