package br.pucpr.authserver.users

import br.pucpr.authserver.security.Jwt
import br.pucpr.authserver.users.requests.LoginRequest
import br.pucpr.authserver.users.responses.LoginResponse
import org.springframework.stereotype.Service
@Service
class UsersService(val repository: UsersRepository) {

    val jwt = Jwt()

    fun save(user: User) = repository.save(user)

    fun getById(id: Long) = repository.findById(id)

    fun findAll(role: String?): List<User> =
        if (role == null) repository.findAll()
        else repository.findallByRole(role)

    @Deprecated(message = "Não implementado", replaceWith = ReplaceWith("repository.findUserByEmail(email)"))
    fun findByEmail(email: String) = repository.findUserByEmail(email)

    fun deleteById(id: Long) = repository.deleteById(id)

    fun login(credentials: LoginRequest): LoginResponse? {
        val user = repository.findUserByEmail(credentials.email!!) ?: return null
        return if (user.password != credentials.password) null
        else LoginResponse(
                jwt.createToken(user), user.toResponse()
        )
    }

}

