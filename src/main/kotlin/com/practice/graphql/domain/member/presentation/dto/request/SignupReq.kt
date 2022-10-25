package com.practice.graphql.domain.member.presentation.dto.request

import com.practice.graphql.domain.member.Member
import com.practice.graphql.domain.member.Role
import java.util.Collections

class SignupReq(
    val email: String,
    val name: String,
    val password: String,
){
    fun toEntity(password: String): Member{
        return Member(
            email = email,
            name = name,
            password = password,
            roles = Collections.singletonList(Role.ROLE_MEMBER)
        )
    }
}