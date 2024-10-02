package br.pucpr.authserver.users

import jakarta.annotation.Nonnull
import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table (name = "tblUsers")
open class User (
    @Id @GeneratedValue
    var id: Long? = null,

    @Email
    @Column(unique = true, nullable = false)
    var email: String,

    @Column(length = 50)
    var password: String,

    @Column(nullable = false)
    var name : String,

    @Column(length = 10)
    var role : String? = "USER"
)