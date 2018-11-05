package com.deathstar.competitionmanager

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class ServerRunner {

    static def main(args) {
        SpringApplication.run(ServerRunner)
    }
}
