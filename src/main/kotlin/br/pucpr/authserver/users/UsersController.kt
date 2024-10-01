package br.pucpr.authserver.users

import br.pucpr.authserver.users.responses.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun createUser(@RequestBody user: User) : ResponseEntity<UserResponse> {
        service.save(user).toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(user.toResponse())
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long) : ResponseEntity<UserResponse> {
        val user = service.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.status(HttpStatus.CREATED).body(user.toResponse())
    }

    private fun User.toResponse() = UserResponse(id!!, email, name)


}