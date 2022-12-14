package com.practice.graphql.domain.member.presentation.controller

import com.practice.graphql.domain.member.presentation.dto.request.SigninReq
import com.practice.graphql.domain.member.presentation.dto.request.SignupReq
import com.practice.graphql.domain.member.service.GetOneMemberService
import com.practice.graphql.domain.member.service.SigninService
import com.practice.graphql.domain.member.service.SignupService
import com.practice.graphql.global.response.SuccessResponse
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class MemberController(
    val getOneMemberService: GetOneMemberService,
    val signinService: SigninService,
    val signupService: SignupService,
){

    @MutationMapping
    fun signupMember(@Argument inputMember: SignupReq): SuccessResponse{
        signupService.execute(inputMember)
        return SuccessResponse
    }

    @MutationMapping
    fun signin(@Argument inputLogin: SigninReq) =
        signinService.execute(inputLogin)

    @QueryMapping
    fun getMember(@Argument id: Long) =
        getOneMemberService.execute(id)
}