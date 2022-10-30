package com.practice.graphql.domain.posting.facade

import com.practice.graphql.domain.posting.Posting
import com.practice.graphql.domain.posting.repository.PostingRepository
import com.practice.graphql.global.exception.collections.PostingNotExistException
import org.springframework.stereotype.Component

@Component
class PostingFacade(
    private val postingRepository: PostingRepository
){
    fun getById(id: Long): Posting{
        return postingRepository.findById(id)
            .orElseThrow{throw PostingNotExistException()}
    }
}