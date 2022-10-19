package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.Posting
import com.practice.graphql.domain.posting.repository.PostingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class PostingService(
    val postingRepository: PostingRepository,
){
    @Transactional
    fun save(title: String, content: String): Posting{
        val posting = Posting(
            title = title,
            content = content,
        )
        return postingRepository.save(posting)
    }

    @Transactional(readOnly = true)
    fun getOne(id: Long) =
        postingRepository.findById(id)
            .orElseThrow { throw RuntimeException() }

    @Transactional(readOnly = true)
    fun getAll() =
        postingRepository.findAll()
}