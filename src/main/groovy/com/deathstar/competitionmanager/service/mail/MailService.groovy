package com.deathstar.competitionmanager.service.mail

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.user.User

interface MailService {

    void sentMailAboutAproveRegistration(User approvedUser)

    void notifyAllCoachesAboutNewCompetition(Competition competition)
}