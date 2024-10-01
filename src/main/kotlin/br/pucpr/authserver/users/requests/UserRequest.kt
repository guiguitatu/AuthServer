package br.pucpr.authserver.users.requests

data class UserRequest(
    val name: String,
    val email: String,
    val password: String
)
