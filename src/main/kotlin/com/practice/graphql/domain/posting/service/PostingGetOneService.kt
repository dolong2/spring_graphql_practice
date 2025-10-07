package com.practice.graphql.domain.posting.service

import com.practice.graphql.domain.posting.facade.PostingFacade
import com.practice.graphql.domain.posting.presentation.dto.response.PostingRes
import com.practice.graphql.global.exception.BasicException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostingGetOneService(
    private val postingFacade: PostingFacade,
){
    @Transactional(readOnly = true, rollbackFor = [BasicException::class])
    fun execute(id: Long): PostingRes =
        PostingRes(postingFacade.getById(id))
}