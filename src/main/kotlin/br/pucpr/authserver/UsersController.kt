package br.pucpr.authserver

import jakarta.websocket.server.PathParam
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
    fun listUsers() = service.findAll()

    @PostMapping()
    fun createUser(@RequestBody user: User) = service.save(user)

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") id: Long) = service.getById(id)


}