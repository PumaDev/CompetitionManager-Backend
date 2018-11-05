package com.deathstar.competitionmanager.service.user

import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.view.accesstoken.LoginView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BCryptPasswordEncrypter implements PasswordEncrypter {

    @Value('${com.ds.competition-manager.app-salt:gsyM48coLad4chhFdvsmcjf34}')
    String applicationSalt

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder

    @Override
    String hashPassword(User user) {
        String passwordWithSalt = buildPasswordWithSalt(user)
        return bCryptPasswordEncoder.encode(passwordWithSalt)
    }

    @Override
    Boolean isPasswordEquals(LoginView loginView, User user) {
        String passwordWithSalt = buildPasswordWithSalt(new User(login: loginView.login, password: loginView.password))
        return bCryptPasswordEncoder.matches(passwordWithSalt, user.password)
    }

    private buildPasswordWithSalt(User user) {
        return "${user.password}${user.login}${applicationSalt}"
    }
}
