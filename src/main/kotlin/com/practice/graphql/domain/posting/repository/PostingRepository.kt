package com.practice.graphql.domain.posting.repository

import com.practice.graphql.domain.posting.Posting
import com.practice.graphql.domain.topic.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface PostingRepository : JpaRepository<Posting, Long> {
    fun findAllByTopic(topic: Topic): List<Posting>
}