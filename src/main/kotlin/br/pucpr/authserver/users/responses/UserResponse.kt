package br.pucpr.authserver.users.responses

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val role: String?
)
