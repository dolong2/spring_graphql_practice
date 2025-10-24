package com.practice.graphql.domain.comment

import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.posting.Posting
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class Comment(
    @Id
    @GeneratedValue
    val id: Long = 0L,
    var content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime? = null,
    @ManyToOne
    @JoinColumn(name = "posting_id")
    val posting: Posting,
    @ManyToOne
    @JoinColumn(name = "writer_id")
    val writer: Member,
)