package br.pucpr.authserver.users

import br.pucpr.authserver.users.requests.UserRequest
import br.pucpr.authserver.users.responses.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")

class UsersController(val service: UsersService) {

    @GetMapping()
    fun listUsers() = service.findAll().map { it.toResponse() }

    @PostMapping()
    fun createUser(@RequestBody @Validated req: UserRequest) : ResponseEntity<UserResponse> {
        val user = User(email = req.email, password = req.password, name = req.name?: req.email)
        val saved = service.save(user).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(saved)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long) : ResponseEntity<UserResponse> =
        service.getById(id)
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    private fun User.toResponse() = UserResponse(id!!, email, name)


}