package br.pucpr.authserver.security

import jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class SecurityConfig(private val jwtTokenFilter: JwtTokenFilter) {

    @Bean
    fun filterChain(security: HttpSecurity): DefaultSecurityFilterChain =

        security
            .cors { it.disable() }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .exceptionHandling { it
                .authenticationEntryPoint { _, response, authException ->
                    response.sendError(SC_UNAUTHORIZED, authException.message)
                }
            }
            .headers { it
                .frameOptions { frameOptions -> frameOptions.disable() }
            }
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(HttpMethod.GET).permitAll()
                    .requestMatchers("/error/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users", "/users/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/pedidos/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/produto/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

    @Bean
    fun corsFilter() =
        CorsConfiguration().apply{
            addAllowedHeader("*")
            addAllowedOrigin("*")
            addAllowedMethod("*")
        } .let{
            UrlBasedCorsConfigurationSource().apply {
                registerCorsConfiguration("/**", it)
            }
        } .let { CorsFilter(it) }
}