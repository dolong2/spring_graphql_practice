package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.presentation.dto.request.PostingReq
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.domain.posting.repository.PostingRepository
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.exception.collections.PostingNotExistException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class PostingService(
    val postingRepository: PostingRepository,
){
    @Transactional(rollbackFor = [BasicException::class])
    fun save(inputPosting: PostingReq): PostingRes{
        val posting = postingRepository.save(inputPosting.toEntity())
        return PostingRes(posting)
    }

    @Transactional(readOnly = true, rollbackFor = [BasicException::class])
    fun getOne(id: Long): PostingRes {
        val posting = postingRepository.findById(id)
            .orElseThrow { throw PostingNotExistException() }
        return PostingRes(posting)
    }

    @Transactional(readOnly = true, rollbackFor = [BasicException::class])
    fun getAll() =
        postingRepository.findAll()
            .map{PostingRes(it)}
}