package br.pucpr.authserver.users

import br.pucpr.authserver.users.requests.LoginRequest
import org.springframework.stereotype.Service
@Service
class UsersService(val repository: UsersRepository) {

    fun save(user: User) = repository.save(user)

    fun getById(id: Long) = repository.findById(id)

    fun findAll(role: String?): List<User> =
        if (role == null) repository.findAll()
        else repository.findallByRole(role)

    @Deprecated(message = "Não implementado", replaceWith = ReplaceWith("repository.findUserByEmail(email)"))
    fun findByEmail(email: String) = repository.findUserByEmail(email)

    fun deleteById(id: Long) = repository.deleteById(id)

    fun login(credentials: LoginRequest): User? {
        val user = repository.findUserByEmail(credentials.email!!) ?: return null
        return if (user.password != credentials.password) null
        else user
    }

}

