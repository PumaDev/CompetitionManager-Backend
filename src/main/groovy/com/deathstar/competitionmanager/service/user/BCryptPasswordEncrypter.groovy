package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BCryptPasswordEncrypter implements PasswordEncrypter {

    @Value('${com.ds.competition-manager.app-salt:gsyM48coLad4chhFdvsmcjf34}')
    String applicationSalt

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder

    @Override
    String hashPassword(User user) {
        String passwordWithSalt = "${user.password}${user.login}${applicationSalt}"
        return bCryptPasswordEncoder.encode(passwordWithSalt)
    }
}
