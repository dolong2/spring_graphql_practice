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
    fun writePosting(@Argument postingRequest: PostingReq) =
        postingSaveService.execute(postingRequest)

    @QueryMapping
    fun getPosting(@Argument id:Long) =
        postingGetOneService.execute(id)

    @QueryMapping
    fun getPostings() =
        postingGetAllService.execute()

    @MutationMapping
    fun updatePosting(@Argument id:Long, @Argument postingRequest: PostingUpdateReq) =
        postingUpdateService.execute(id, postingRequest)

    @SubscriptionMapping
    fun getPostingsRealTime(): Flux<PostingListRes> =
        Flux.interval(Duration.ofSeconds(5))
            .map { postingGetAllService.execute() }

    @SubscriptionMapping
    fun getPostingRealTime(@Argument id: Long): Flux<PostingRes> =
        Flux.interval(Duration.ofSeconds(5))
            .map { postingGetOneService.execute(id) }
}