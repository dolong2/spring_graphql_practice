package com.practice.graphql.global.util

import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.config.security.auth.AuthDetails
import com.practice.graphql.global.exception.collections.MemberNotExistException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CurrentMemberUtil(
    private val memberRepository: MemberRepository
){
    private fun getCurrentId(): Long =
        (SecurityContextHolder.getContext().authentication.principal as AuthDetails).getId()

    fun getCurrentMember(): Member =
        memberRepository.findByIdOrNull(getCurrentId())
            ?: throw MemberNotExistException()
}