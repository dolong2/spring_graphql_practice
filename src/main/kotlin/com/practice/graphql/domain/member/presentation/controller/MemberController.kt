package com.practice.graphql.domain.member.presentation.controller

import com.practice.graphql.domain.member.presentation.dto.request.SigninReq
import com.practice.graphql.domain.member.presentation.dto.request.SignupReq
import com.practice.graphql.domain.member.service.MemberService
import com.practice.graphql.global.response.SuccessResponse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class MemberController(
    val memberService: MemberService,
){

    @MutationMapping
    fun signupMember(@Argument inputMember: SignupReq): SuccessResponse{
        memberService.signup(inputMember)
        return SuccessResponse
    }

    @MutationMapping
    fun signin(@Argument inputLogin: SigninReq) =
        memberService.signin(inputLogin)

    @QueryMapping
    fun getMember(@Argument id: Long) =
        memberService.getOneMember(id)
}