package com.practice.graphql.domain.member.facade

import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.util.Facade

@Facade
class MemberFacade(
    private val memberRepository: MemberRepository,
){
    fun existMember(email: String): Boolean =
        memberRepository.existsByEmail(email)
}