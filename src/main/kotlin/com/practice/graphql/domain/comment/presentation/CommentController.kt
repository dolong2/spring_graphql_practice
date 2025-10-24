package com.practice.graphql.domain.comment.presentation

import com.practice.graphql.domain.comment.presentation.dto.request.WriteCommentReqDto
import com.practice.graphql.domain.comment.service.WriteCommentService
import com.practice.graphql.global.response.SuccessResponse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class CommentController(
    private val writeCommentService: WriteCommentService
) {
    @MutationMapping
    fun createComment(
        @Argument postingId: Long,
        @Argument writeCommentReqDto: WriteCommentReqDto
    ) =
        writeCommentService.execute(postingId, writeCommentReqDto)
            .let { SuccessResponse }
}