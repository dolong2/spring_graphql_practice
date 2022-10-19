package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.presentation.dto.request.InputPosting
import com.practice.graphql.domain.posting.presentation.dto.response.ResponsePosting
import com.practice.graphql.domain.posting.repository.PostingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class PostingService(
    val postingRepository: PostingRepository,
){
    @Transactional
    fun save(inputPosting: InputPosting): ResponsePosting{
        val posting = postingRepository.save(inputPosting.toEntity())
        return ResponsePosting(posting)
    }

    @Transactional(readOnly = true)
    fun getOne(id: Long): ResponsePosting {
        val posting = postingRepository.findById(id)
            .orElseThrow { throw RuntimeException() }
        return ResponsePosting(posting)
    }

    @Transactional(readOnly = true)
    fun getAll() =
        postingRepository.findAll()
            .map{ResponsePosting(it)}
}