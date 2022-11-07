package com.practice.graphql.domain.member.service

import com.practice.graphql.domain.member.facade.MemberFacade
import com.practice.graphql.domain.member.presentation.dto.request.SigninReq
import com.practice.graphql.domain.member.presentation.dto.response.SigninRes
import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.exception.collections.MemberNotExistException
import com.practice.graphql.global.exception.collections.PasswordNotMatchException
import com.practice.graphql.global.jwt.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SigninService(
    private val memberRepository: MemberRepository,
    private val tokenProvider: TokenProvider,
    private val passwordEncoder: PasswordEncoder,
    private val memberFacade: MemberFacade,
){
    @Transactional(rollbackFor = [BasicException::class])
    fun execute(signinReq: SigninReq): SigninRes {
        if(!memberFacade.existMember(signinReq.email))
            throw MemberNotExistException()
        val member = memberRepository.findByEmail(signinReq.email) ?: throw MemberNotExistException()
        if(!passwordEncoder.matches(signinReq.password, member.password))
            throw PasswordNotMatchException()
        val accessToken = tokenProvider.createAccessToken(member.email, member.roles)
        val refreshToken = tokenProvider.createRefreshToken(member.email)
        member.updateRefreshToken(refreshToken)
        return SigninRes(accessToken, refreshToken)
    }
}