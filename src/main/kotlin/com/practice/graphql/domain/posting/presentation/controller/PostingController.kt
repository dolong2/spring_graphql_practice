package com.practice.graphql.domain.posting.presentation.controller

import com.practice.graphql.domain.posting.presentation.dto.request.PostingReq
import com.practice.graphql.domain.posting.presentation.dto.request.PostingUpdateReq
import com.practice.graphql.domain.posting.service.*
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import java.util.stream.Stream

@Controller
class PostingController(
    val postingGetAllService: PostingGetAllService,
    val postingGetOneService: PostingGetOneService,
    val postingSaveService: PostingSaveService,
    val postingUpdateService: PostingUpdateService,
){

    @MutationMapping
    fun writePosting(@Argument inputPosting: PostingReq) =
        postingSaveService.execute(inputPosting)

    @QueryMapping
    fun getPosting(@Argument id:Long) =
        postingGetOneService.execute(id)

    @QueryMapping
    fun getPostings() =
        postingGetAllService.execute()

    @MutationMapping
    fun updatePosting(@Argument id:Long, @Argument updatePostingReq: PostingUpdateReq) =
        postingUpdateService.execute(id, updatePostingReq)

    @SubscriptionMapping
    fun getPostingsRealTime() =
        Flux.fromStream(Stream.generate {
            postingGetAllService.execute()
        })

    @SubscriptionMapping
    fun getPostingRealTime(@Argument id:Long) =
        Flux.fromStream(Stream.generate {
            postingGetOneService.execute(id)
        })
}