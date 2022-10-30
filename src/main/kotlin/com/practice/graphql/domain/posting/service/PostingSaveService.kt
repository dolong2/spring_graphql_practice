package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.presentation.dto.request.PostingReq
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.domain.posting.repository.PostingRepository
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostingSaveService(
    private val postingRepository: PostingRepository,
    private val currentMemberUtil: CurrentMemberUtil,
){
    @Transactional(rollbackFor = [BasicException::class])
    fun execute(inputPosting: PostingReq): PostingRes {
        val member = currentMemberUtil.getCurrentMember()
        val posting = postingRepository.save(inputPosting.toEntity(member))
        return PostingRes(posting)
    }
}