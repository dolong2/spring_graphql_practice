package com.practice.graphql.domain.posting.presentation.controller

import com.practice.graphql.domain.posting.presentation.dto.request.InputPosting
import com.practice.graphql.domain.posting.service.PostingService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class PostingController(
    val postingService: PostingService,
){

    @MutationMapping
    fun writePosting(@Argument inputPosting: InputPosting) =
        postingService.save(inputPosting)

    @QueryMapping
    fun getPosting(@Argument id:Long) =
        postingService.getOne(id)

    @QueryMapping
    fun getPostings() =
        postingService.getAll()
}