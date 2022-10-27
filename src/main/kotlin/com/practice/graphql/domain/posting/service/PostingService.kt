package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.presentation.dto.request.PostingReq
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.domain.posting.repository.PostingRepository
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.exception.collections.PostingNotExistException
import com.practice.graphql.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class PostingService(
    val postingRepository: PostingRepository,
    val currentMemberUtil: CurrentMemberUtil,
){
    @Transactional(rollbackFor = [BasicException::class])
    fun save(inputPosting: PostingReq): PostingRes{
        val member = currentMemberUtil.getCurrentMember()
        val posting = postingRepository.save(inputPosting.toEntity(member))
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