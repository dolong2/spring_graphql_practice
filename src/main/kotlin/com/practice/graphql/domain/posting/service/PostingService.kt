package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.facade.PostingFacade
import com.practice.graphql.domain.posting.presentation.dto.request.PostingReq
import com.practice.graphql.domain.posting.presentation.dto.request.PostingUpdateReq
import com.practice.graphql.domain.posting.presentation.dto.response.PostingListRes
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.domain.posting.repository.PostingRepository
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.exception.collections.MemberNotSameException
import com.practice.graphql.global.exception.collections.PostingNotExistException
import com.practice.graphql.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class PostingService(
    val postingFacade: PostingFacade,
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
    fun getOne(id: Long): PostingRes =
        PostingRes(postingFacade.getById(id))

    @Transactional(readOnly = true, rollbackFor = [BasicException::class])
    fun getAll() =
        PostingListRes(
            list = postingRepository.findAll()
                .map{PostingRes(it)}
        )

    @Transactional(rollbackFor = [BasicException::class])
    fun updatePosting(id: Long, postingUpdateReq: PostingUpdateReq): PostingRes{
        val posting = postingFacade.getById(id)
        if(posting.writer != currentMemberUtil.getCurrentMember())
            throw MemberNotSameException()
        posting.update(postingUpdateReq)
        return PostingRes(posting)
    }
}