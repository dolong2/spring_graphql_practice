package com.practice.graphql.global.config.security.auth

import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.exception.collections.MemberNotExistException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailService(
    private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails =
        AuthDetails(memberRepository.findByEmail(username)
            ?: throw MemberNotExistException()
        )
}