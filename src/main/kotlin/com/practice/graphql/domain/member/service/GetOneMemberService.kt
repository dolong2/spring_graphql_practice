package com.practice.graphql.domain.member.service

import com.practice.graphql.domain.member.presentation.dto.response.MemberRes
import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.exception.collections.MemberNotExistException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetOneMemberService(
    private val memberRepository: MemberRepository,
){
    @Transactional(rollbackFor = [BasicException::class], readOnly = true)
    fun execute(id: Long): MemberRes {
        val member = memberRepository.findById(id)
            .orElseThrow { throw MemberNotExistException() }
        return MemberRes(member)
    }
}