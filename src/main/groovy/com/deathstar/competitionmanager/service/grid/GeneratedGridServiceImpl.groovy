package com.deathstar.competitionmanager.service.grid

import com.deathstar.competitionmanager.domain.GeneratedGrid
import com.deathstar.competitionmanager.repository.GeneratedGridRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.Instant

@Service
class GeneratedGridServiceImpl implements GeneratedGridService {

    @Autowired
    GeneratedGridRepository generatedGridRepository

    @Override
    GeneratedGrid save(GeneratedGrid generatedGrid) {
        generatedGrid.createdDate = Instant.now()
        return generatedGridRepository.save(generatedGrid)
    }

    @Override
    GeneratedGrid findByKey(String key) {
        return generatedGridRepository.findGeneratedGridByKey(key)
    }

    @Override
    Set<GeneratedGrid> findByLessThenCreatedDate(Instant createdDate) {
        return generatedGridRepository.findGeneratedGridsByCreatedDateLessThan(createdDate)
    }

    @Override
    boolean deleteGeneratedGridsByIds(Set<GeneratedGrid> gridsForDelete) {
        return generatedGridRepository.delete(gridsForDelete)
    }
}
