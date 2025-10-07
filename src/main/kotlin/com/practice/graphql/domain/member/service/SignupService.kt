package com.practice.graphql.domain.member.service

import com.practice.graphql.domain.member.facade.MemberFacade
import com.practice.graphql.domain.member.presentation.dto.request.SignupReq
import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.exception.BasicException
import com.practice.graphql.global.exception.collections.MemberAlreadyExistException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignupService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val memberFacade: MemberFacade,
){
    @Transactional(rollbackFor = [BasicException::class])
    fun execute(signupReq: SignupReq){
        if(memberFacade.existMember(signupReq.email))
            throw MemberAlreadyExistException()
        val encodedPassword = passwordEncoder.encode(signupReq.password)
        val member = signupReq.toEntity(encodedPassword)
        memberRepository.save(member)
    }
}