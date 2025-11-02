package com.practice.graphql.domain.member

import com.practice.graphql.domain.posting.Posting
import jakarta.persistence.CascadeType
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany

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
    @Id
    @GeneratedValue
    val id: Long = 0L
    @OneToMany(cascade = [CascadeType.REMOVE], mappedBy = "writer")
    val postings: List<Posting> = mutableListOf()
    var refreshToken: String = ""


    fun updateRefreshToken(refreshToken: String){
        this.refreshToken = refreshToken
    }
}