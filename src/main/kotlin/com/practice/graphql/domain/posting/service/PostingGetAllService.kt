package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.presentation.dto.response.PostingListRes
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.domain.posting.repository.PostingRepository
import com.practice.graphql.domain.topic.facade.TopicFacade
import com.practice.graphql.global.exception.BasicException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostingGetAllService(
    private val postingRepository: PostingRepository,
    private val topicFacade: TopicFacade,
){
    @Transactional(readOnly = true, rollbackFor = [BasicException::class])
    fun execute(topicId: Long): PostingListRes =
        topicFacade.getById(topicId)
            .let { postingRepository.findAllByTopic(it) }
            .map { PostingRes(it) }
            .let { PostingListRes(it) }
}