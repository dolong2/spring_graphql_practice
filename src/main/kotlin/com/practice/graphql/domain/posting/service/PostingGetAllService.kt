package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.presentation.dto.response.PostingListRes
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.domain.posting.repository.PostingRepository
import com.practice.graphql.global.exception.collections.BasicException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostingGetAllService(
    private val postingRepository: PostingRepository,
){
    @Transactional(readOnly = true, rollbackFor = [BasicException::class])
    fun execute() =
        PostingListRes(
            list = postingRepository.findAll()
                .map{ PostingRes(it) }
        )
}