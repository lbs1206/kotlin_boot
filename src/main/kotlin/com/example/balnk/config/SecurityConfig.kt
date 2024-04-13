package com.example.balnk.config

import com.example.balnk.config.jwt.JwtAuthenticationFilter
import com.example.balnk.config.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtTokenProvider: JwtTokenProvider) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf{
                it.disable()
            }
            .cors { }
            .headers {}
            .authorizeHttpRequests{
                it
                    .requestMatchers(*allAllowedUris).permitAll()
                    .requestMatchers(HttpMethod.GET,*getAllowedUris).permitAll()
                    .requestMatchers(HttpMethod.POST, *postAllowedUris).permitAll()
                    .requestMatchers(HttpMethod.PUT,*putAllowedUris).permitAll()
                    .requestMatchers(HttpMethod.DELETE, *deleteAllowedUris).permitAll()
                    .anyRequest().authenticated()
            }.addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)


        return http.build()
    }
}

val allAllowedUris = arrayOf("/all")
val getAllowedUris = arrayOf("/member/test")
val postAllowedUris = arrayOf("/member","/member/sign-in")
val putAllowedUris = arrayOf("/put")
val deleteAllowedUris = arrayOf("/delete")
