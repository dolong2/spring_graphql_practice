package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.facade.PostingFacade
import com.practice.graphql.domain.posting.presentation.dto.request.PostingUpdateReq
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.exception.collections.MemberNotSameException
import com.practice.graphql.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostingUpdateService(
    private val currentMemberUtil: CurrentMemberUtil,
    private val postingFacade: PostingFacade,
){
    @Transactional(rollbackFor = [BasicException::class])
    fun execute(id: Long, postingUpdateReq: PostingUpdateReq): PostingRes {
        val posting = postingFacade.getById(id)
        if(posting.writer != currentMemberUtil.getCurrentMember())
            throw MemberNotSameException()
        posting.update(postingUpdateReq)
        return PostingRes(posting)
    }
}