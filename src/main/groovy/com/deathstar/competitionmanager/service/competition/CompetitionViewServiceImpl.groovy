package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.GeneratedGrid
import com.deathstar.competitionmanager.domain.RegistrationStatus
import com.deathstar.competitionmanager.domain.user.User
import com.deathstar.competitionmanager.domain.user.UserRole
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.security.CurrentUserResolver
import com.deathstar.competitionmanager.service.grid.CompetitionCategoryGridService
import com.deathstar.competitionmanager.service.grid.GeneratedGridService
import com.deathstar.competitionmanager.service.mail.MailService
import com.deathstar.competitionmanager.service.sportsman.RegistratedSportsmanService
import com.deathstar.competitionmanager.view.CompetitionMetaView
import com.deathstar.competitionmanager.view.CompetitionView
import com.deathstar.competitionmanager.view.category.CompetitionCategoryView
import org.hibernate.engine.jdbc.BlobProxy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

import static com.deathstar.competitionmanager.domain.RegistrationStatus.CLOSED
import static com.deathstar.competitionmanager.domain.RegistrationStatus.REOPEN

@Service
class CompetitionViewServiceImpl implements CompetitionViewService {

    @Autowired
    CompetitionService competitionService

    @Autowired
    CompetitionConverter competitionConverter

    @Autowired
    RegistratedSportsmanService registratedSportsmanService

    @Autowired
    CompetitionCategoryGridService competitionCategoryGridService

    @Autowired
    CurrentUserResolver currentUserResolver

    @Autowired
    GeneratedGridService generatedGridService

    @Autowired
    MailService mailService

    @Override
    CompetitionView save(CompetitionView competitionView) {
        Competition competition = competitionConverter.convertToDomainEntity(competitionView)
        Competition createdCompetition = competitionService.save(competition)
        mailService.notifyAllCoachesAboutNewCompetition(createdCompetition)
        return competitionConverter.convertToView(createdCompetition)
    }

    @Override
    CompetitionView findById(Integer id) {
        Competition foundCompetition = competitionService.findById(id)
        CompetitionView competitionView = competitionConverter.convertToView(foundCompetition)
        fillCompetitionMetaInCompetition(currentUserResolver.getCurrentUser(), competitionView)
        return competitionView
    }

    @Override
    Page<CompetitionView> findFeatureCompetitions(Pageable pageable) {
        Page<Competition> featureCompetitions = competitionService.findFeatureCompetitions(pageable)
        Page<CompetitionView> featureCompetitionsPage = competitionConverter.convertToViews(featureCompetitions, pageable)
        fillCompetitionMetaInCompetitionsPage(currentUserResolver.getCurrentUser(), featureCompetitionsPage)
        return featureCompetitionsPage
    }

    @Override
    Page<CompetitionView> findLastCompetitions(Pageable pageable) {
        Page<Competition> lastCompetitions = competitionService.findLastCompetitions(pageable)
        Page<CompetitionView> lastCompetitionsPage = competitionConverter.convertToViews(lastCompetitions, pageable)
        fillCompetitionMetaInCompetitionsPage(currentUserResolver.getCurrentUser(), lastCompetitionsPage)
        return lastCompetitionsPage
    }

    private void fillCompetitionMetaInCompetitionsPage(User currentUser, Page<CompetitionView> competitionsPage) {
        competitionsPage.content.forEach { view ->
            fillCompetitionMetaInCompetition(currentUser, view)
        }
    }

    private void fillCompetitionMetaInCompetition(User currentUser, CompetitionView competitionView) {
        competitionView.competitionMeta = new CompetitionMetaView(
                totalCategoriesSize: competitionView.categories.size(),
                minAgeCategory: competitionView.categories.collect { it.lowerAge }.min { it },
                maxAgeCategory: getMaxAgeOfCompetition(competitionView),
                totalSportsmenCount: registratedSportsmanService.findSportsmanByCompetitionId(competitionView.id).size(),
                totalSportsmenOfCoachClubCount: currentUser.userRole == UserRole.COACH ?
                        registratedSportsmanService.findSportsmenByClubNameAndCompetitionId(currentUser.clubName, competitionView.id).size() : null)
    }

    private static Integer getMaxAgeOfCompetition(CompetitionView competition) {
        Set<Integer> upperAges = competition.categories.collect { it.upperAge }
        upperAges.contains(null) ? null : upperAges.max()
    }

    @Override
    CompetitionView update(CompetitionView competitionView) {
        Competition competition = competitionConverter.convertToDomainEntity(competitionView)
        Competition updatedCompetition = competitionService.update(competition)
        CompetitionView updatedCompetitionView = competitionConverter.convertToView(updatedCompetition)
        fillCompetitionMetaInCompetition(currentUserResolver.getCurrentUser(), updatedCompetitionView)
        return updatedCompetitionView
    }

    @Override
    boolean deleteCompetition(Integer competitionId) {
        return competitionService.deleteCompetitionById(competitionId)
    }

    @Override
    CompetitionView updateRegistrationStatus(Integer competitionId, RegistrationStatus newRegistrationStatus) {
        // support only closing and reopening
        if (![REOPEN, CLOSED].contains(newRegistrationStatus)) {
            throw new Exception('Can update status only to `REOPEN` and `CLOSED`')
        }

        Competition competition = competitionService.findById(competitionId)
        if (!competition) {
            throw new EntityNotFoundException()
        }

        competition.registrationStatus = newRegistrationStatus
        CompetitionView view = competitionConverter.convertToView(competitionService.update(competition))
        fillCompetitionMetaInCompetition(currentUserResolver.getCurrentUser(), view)

        return view
    }

    @Override
    List<CompetitionView> getAllCompetitions() {
        return competitionConverter.convertToViews(competitionService.getAllCompetitions())
    }

    @Override
    List<CompetitionCategoryView> getCategoriesByCompetitionId(Integer competitionId) {
        return findById(competitionId)?.categories
    }

    @Override
    Tuple2<File, CompetitionView> generateGrids(Integer competitionId) {
        Competition competition = competitionService.findById(competitionId)
        if (!competition) {
            System.out.println("Competition not found")
            throw new RuntimeException()
        } else {
            File zipFile = competitionCategoryGridService.generateCategoryGridsForCompetition(competition)
            GeneratedGrid generatedGrid = new GeneratedGrid(key: zipFile.getName(),
                    body: BlobProxy.generateProxy(new FileInputStream(zipFile), zipFile.size()))
            generatedGridService.save(generatedGrid)
            zipFile.delete()
            return new Tuple2<File, CompetitionView>(zipFile, competitionConverter.convertToView(competition))
        }
    }
}
