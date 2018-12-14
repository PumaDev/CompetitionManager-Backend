package com.deathstar.competitionmanager.controller

import com.deathstar.competitionmanager.service.accesstoken.LoginService
import com.deathstar.competitionmanager.service.user.UserViewService
import com.deathstar.competitionmanager.view.accesstoken.AccessTokenView
import com.deathstar.competitionmanager.view.accesstoken.LoginView
import com.deathstar.competitionmanager.view.user.CreateUserView
import com.deathstar.competitionmanager.view.user.RegistrateResponse
import com.deathstar.competitionmanager.view.user.UserView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@RestController
class LoginController {

    @Autowired
    LoginService loginService

    @Autowired
    UserViewService userViewService

    @PostMapping('/login')
    AccessTokenView login(@RequestBody LoginView loginView) {
        return loginService.login(loginView)
    }

    @PostMapping('/register')
    RegistrateResponse register(@RequestBody CreateUserView createUserView) {
        return userViewService.register(createUserView)
    }
}
