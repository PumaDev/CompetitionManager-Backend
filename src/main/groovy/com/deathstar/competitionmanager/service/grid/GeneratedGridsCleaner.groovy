package com.deathstar.competitionmanager.service.grid

import com.deathstar.competitionmanager.domain.GeneratedGrid
import groovy.util.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
@Log
class GeneratedGridsCleaner {

    @Autowired
    GeneratedGridService generatedGridService

    @Scheduled(fixedDelay = 3600000L) // 1 Hour = 60 * 60 * 1000
    @Transactional(isolation = Isolation.READ_COMMITTED)
    void removeOldGeneratedGrids() {
        Instant oldestLine = Instant.now().minus(1, ChronoUnit.HOURS )
        Set<GeneratedGrid> oldGrids = generatedGridService.findByLessThenCreatedDate(oldestLine)
        if (!oldGrids.isEmpty()){
            generatedGridService.deleteGeneratedGrids(oldGrids)
            log.info("Remove ${oldGrids.size()} old generated grids")
        } else {
            log.info("Doesn't have old generated grids")
        }
    }
}
