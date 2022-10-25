package com.practice.graphql.domain.member.service

import com.practice.graphql.domain.member.presentation.dto.request.SignupReq
import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.exception.collections.MemberAlreadyExistException
import com.practice.graphql.global.jwt.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val tokenProvider: TokenProvider,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional(rollbackFor = [BasicException::class])
    fun signup(signupReq: SignupReq){
        if(memberRepository.existsByEmail(signupReq.email))
            throw MemberAlreadyExistException()
        val encodedPassword = passwordEncoder.encode(signupReq.password)
        val member = signupReq.toEntity(encodedPassword)
        memberRepository.save(member)
    }
}