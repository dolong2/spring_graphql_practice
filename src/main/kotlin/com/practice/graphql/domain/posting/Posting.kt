package com.practice.graphql.domain.posting

import com.practice.graphql.domain.member.Member
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Posting(
    val title: String,
    val content: String,
    @ManyToOne
    @JoinColumn(name = "writer")
    val writer: Member,
){
    @Id @GeneratedValue
    val id: Long = 0
}