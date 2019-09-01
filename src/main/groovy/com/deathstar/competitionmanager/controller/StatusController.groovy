package com.deathstar.competitionmanager.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StatusController {

    @GetMapping('/v1/status')
    StatusView getStatus() {
        return new StatusView(status: "ALIVE")
    }
}

class StatusView {
    String status
}