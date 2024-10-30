package br.pucpr.authserver.users

import br.pucpr.authserver.exceptions.BadRequestException
import br.pucpr.authserver.security.Jwt
import br.pucpr.authserver.security.SecurityProperties
import br.pucpr.authserver.users.requests.LoginRequest
import br.pucpr.authserver.users.responses.LoginResponse
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UsersService(val repository: UsersRepository, val jwt: Jwt) {

    fun save(user: User){
        val email = repository.findUserByEmail(user.email)
        if (email != null) {
            throw BadRequestException("Email já cadastrado")
        } else repository.save(user)

    }

    fun getById(id: Long) = repository.findById(id)

    fun findAll(role: String?): List<User> =
        if (role == null) repository.findAll()
        else repository.findallByRole(role)

    @Deprecated(message = "Não implementado", replaceWith = ReplaceWith("repository.findUserByEmail(email)"))
    fun findByEmail(email: String) = repository.findUserByEmail(email)

    fun login(credentials: LoginRequest): LoginResponse? {
        val user = repository.findUserByEmail(credentials.email!!) ?: return null
        if (user.password != credentials.password) return null
        log.info("User Logged in. id={} name={}", user.id, user.name)
        return LoginResponse(
            token = jwt.createToken(user),
            user.toResponse()
        )
    }

    fun delete(id: Long): Boolean {
        val user = repository.findByIdOrNull(id) ?: return false
        if (user.role == "ADM") {
            val count = repository.findallByRole("ADM").size
            if (count == 1) throw BadRequestException("Não é possível deletar o único usuário ADM")
        }
        log.warn("User Deleted. id={} name={}", user.id, user.name)
        repository.delete(user)
        return false
    }

    companion object {
        val log = LoggerFactory.getLogger(UsersService::class.java)
    }

}

