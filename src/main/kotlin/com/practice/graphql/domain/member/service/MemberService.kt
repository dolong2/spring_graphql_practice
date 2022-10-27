package com.practice.graphql.domain.member.service

import com.practice.graphql.domain.member.presentation.dto.request.SigninReq
import com.practice.graphql.domain.member.presentation.dto.request.SignupReq
import com.practice.graphql.domain.member.presentation.dto.response.MemberRes
import com.practice.graphql.domain.member.presentation.dto.response.SigninRes
import com.practice.graphql.domain.member.repository.MemberRepository
import com.practice.graphql.global.exception.collections.BasicException
import com.practice.graphql.global.exception.collections.MemberAlreadyExistException
import com.practice.graphql.global.exception.collections.MemberNotExistException
import com.practice.graphql.global.exception.collections.PasswordNotMatchException
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

    @Transactional(rollbackFor = [BasicException::class])
    fun signin(signinReq: SigninReq): SigninRes{
        if(!memberRepository.existsByEmail(signinReq.email))
            throw MemberNotExistException()
        val member = memberRepository.findByEmail(signinReq.email) ?: throw MemberNotExistException()
        if(!passwordEncoder.matches(signinReq.password, member.password))
            throw PasswordNotMatchException()
        val accessToken = tokenProvider.createAccessToken(member.email, member.roles)
        val refreshToken = tokenProvider.createRefreshToken(member.email)
        member.updateRefreshToken(refreshToken)
        return SigninRes(accessToken, refreshToken)
    }

    @Transactional(rollbackFor = [BasicException::class], readOnly = true)
    fun getOneMember(id: Long): MemberRes{
        val member = memberRepository.findById(id)
            .orElseThrow { throw MemberNotExistException() }
        return MemberRes(member)
    }
}