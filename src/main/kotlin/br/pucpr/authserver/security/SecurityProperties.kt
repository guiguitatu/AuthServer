package br.pucpr.authserver.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties("security")
data class SecurityProperties @ConstructorBinding constructor(
        val issuer: String,
        val token: String,
        val expHours: Long,
        val user: String
)
