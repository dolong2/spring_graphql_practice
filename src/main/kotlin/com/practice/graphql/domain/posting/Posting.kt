package com.practice.graphql.domain.posting

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Posting(
    val title: String,
    val content: String,
){
    @Id @GeneratedValue
    val id: Long = 0
}