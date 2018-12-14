package com.deathstar.competitionmanager.service.sportsman

import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.repository.RegistratedSportsmanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegistratedSportsmanServiceImpl implements RegistratedSportsmanService {

    @Autowired
    RegistratedSportsmanRepository registratedSportsmanRepository

    @Override
    RegistratedSportsman create(RegistratedSportsman sportsman) {
            return registratedSportsmanRepository.save(sportsman)
    }

    @Override
    List<RegistratedSportsman> readAll() {
        return registratedSportsmanRepository.findAll()
    }

    @Override
    RegistratedSportsman getById(Integer id) {
        return registratedSportsmanRepository.findOne(id)
    }

    @Override
    List<RegistratedSportsman> findSportsmanByCompetitionId(Integer competitionId) {
        return registratedSportsmanRepository.findRegistratedSportsmansByCompetitionId(competitionId)
    }

    @Override
    List<RegistratedSportsman> findSportsmenByClubNameAndCompetitionId(String clubName, Integer competitionId) {
        return registratedSportsmanRepository.findRegistratedSportsmansByClubNameAndCompetitionId(clubName, competitionId)
    }

    @Override
    RegistratedSportsman delete(Integer sportsmanId) {
        return registratedSportsmanRepository.delete(sportsmanId)
    }
}
