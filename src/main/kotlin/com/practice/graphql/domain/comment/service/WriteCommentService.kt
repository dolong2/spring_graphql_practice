package com.practice.graphql.domain.comment.service

import com.practice.graphql.domain.comment.presentation.dto.request.WriteCommentReqDto
import com.practice.graphql.domain.comment.repository.CommentRepository
import com.practice.graphql.domain.posting.facade.PostingFacade
import com.practice.graphql.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteCommentService(
    private val commentRepository: CommentRepository,
    private val postingFacade: PostingFacade,
    private val currentMemberUtil: CurrentMemberUtil,
) {
    @Transactional(rollbackFor = [Exception::class])
    fun execute(postingId: Long, writeCommentReqDto: WriteCommentReqDto) {
        val posting = postingFacade.getById(postingId)

        val member = currentMemberUtil.getCurrentMember()

        val comment = writeCommentReqDto.toEntity(posting, member)
        commentRepository.save(comment)
    }
}