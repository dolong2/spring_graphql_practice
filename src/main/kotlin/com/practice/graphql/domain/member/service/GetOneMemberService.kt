package com.practice.graphql.domain.member.service

import com.practice.graphql.domain.member.presentation.dto.response.MemberRes
import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.exception.BasicException
import com.practice.graphql.global.exception.collections.MemberNotExistException
import com.practice.graphql.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetOneMemberService(
    private val memberRepository: MemberRepository,
    private val currentMemberUtil: CurrentMemberUtil,
){
    @Transactional(rollbackFor = [BasicException::class], readOnly = true)
    fun execute(id: Long): MemberRes {
        val member = memberRepository.findById(id)
            .orElseThrow { throw MemberNotExistException() }
        return MemberRes(member)
    }

    @Transactional(rollbackFor = [BasicException::class], readOnly = true)
    fun execute(): MemberRes {
        val member = currentMemberUtil.getCurrentMember()
        return MemberRes(member)
    }
}