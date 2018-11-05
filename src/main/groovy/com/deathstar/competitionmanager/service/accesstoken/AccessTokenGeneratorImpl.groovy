package com.deathstar.competitionmanager.service.accesstoken

import com.deathstar.competitionmanager.domain.AccessToken
import com.deathstar.competitionmanager.domain.user.User
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.time.Instant

import static java.lang.Math.abs

@Component
class AccessTokenGeneratorImpl implements AccessTokenGenerator {

    private List<Character> symbols = []

    @PostConstruct
    void init() {
        symbols += ('A'.charAt(0) .. 'Z'.charAt(0))
        symbols += ('a'.charAt(0) .. 'z'.charAt(0))
        symbols += ('0'.charAt(0) .. '9'.charAt(0))
    }

    @Override
    AccessToken generate(User user) {
        Random random = new Random()
        StringBuilder stringBuilder = new StringBuilder()
        (0 .. 255)
                .collect { random.nextInt(symbols.size()) }
                .each { stringBuilder.append(symbols[it]) }
        String token = stringBuilder.toString()

        return new AccessToken(user: user, token: token)
    }
}
