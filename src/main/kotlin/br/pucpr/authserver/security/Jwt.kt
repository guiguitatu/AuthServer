package br.pucpr.authserver.security

import br.pucpr.authserver.users.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

@Component
class Jwt {

    fun createToken(user: User) =
        UserToken(
            id = user.id ?: -1,
            name = user.name,
            roles = setOf(user.role ?: "USER")
        ).let {
            Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SECRET.toByteArray()))
                .serializeToJsonWith(JacksonSerializer())
                .setIssuedAt(utcNow().toDate())
                .setExpiration(utcNow().plusHours(EXPIRE_HOURS).toDate())
                .setIssuer(ISSUER)
                .setSubject(it.id.toString())
                .addClaims(mapOf(USER to it))
                .compact()
        }

    companion object{
        private const val PREFIX = "Bearer"
        private const val ISSUER = "Minecraft"
        private const val SECRET = "uEX#(}:@Hz!SrRG*8p[f>m!0uY[P@7Kc"
        private const val USER = "user"
        private const val EXPIRE_HOURS = 24L

        private fun ZonedDateTime.toDate(): Date = Date.from(this.toInstant())
        private fun utcNow(): ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC)
    }

}