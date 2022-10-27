package com.practice.graphql.domain.member

import com.practice.graphql.domain.posting.Posting
import javax.persistence.*

@Entity
class Member(
    val email: String,
    val name: String,
    val password: String,
    @Enumerated(EnumType.STRING) @Column(name = "Role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = [JoinColumn(name = "member_id")])
    val roles: MutableList<Role>,
){
    @Id @GeneratedValue
    val id: Long = 0L
    @OneToMany(cascade = [CascadeType.REMOVE], mappedBy = "writer")
    val postings: List<Posting> = mutableListOf()
    var refreshToken: String = ""
    fun updateRefreshToken(refreshToken: String){
        this.refreshToken = refreshToken
    }
}