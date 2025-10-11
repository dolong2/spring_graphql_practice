package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.presentation.dto.request.PostingReq
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.domain.posting.repository.PostingRepository
import com.practice.graphql.domain.topic.repository.TopicRepository
import com.practice.graphql.global.exception.BasicException
import com.practice.graphql.global.util.CurrentMemberUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostingSaveService(
    private val postingRepository: PostingRepository,
    private val currentMemberUtil: CurrentMemberUtil,
    private val topicRepository: TopicRepository,
){
    @Transactional(rollbackFor = [BasicException::class])
    fun execute(topicId: Long, postingReq: PostingReq): PostingRes {
        val topic = (topicRepository.findByIdOrNull(topicId)
            ?: throw RuntimeException())

        val member = currentMemberUtil.getCurrentMember()

        val posting = postingRepository.save(postingReq.toEntity(member, topic))
        return PostingRes(posting)
    }
}