package com.deathstar.competitionmanager.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

@Configuration
class SecurityConfig {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder()
    }

    @Bean
    SCryptPasswordEncoder sCryptPasswordEncoder() {
        return new SCryptPasswordEncoder()
    }
}
