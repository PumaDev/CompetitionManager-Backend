package com.deathstar.competitionmanager.service.grid

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.service.grid.draw.FileWriterConfig
import com.deathstar.competitionmanager.service.grid.draw.GridDrawer
import com.deathstar.competitionmanager.service.grid.draw.GridDrawerConfig
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem
import com.deathstar.competitionmanager.service.sportsman.RegistratedSportsmanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompetitionCategoryGridServiceImpl implements CompetitionCategoryGridService {

    @Autowired
    RegistratedSportsmanService registratedSportsmanService

    @Autowired
    GridDrawer gridDrawer

    @Autowired
    FileWriterConfig fileWriterConfig

    @Override
    File generateCategoryGridsForCompetition(Competition competition) {
        List<File> gridsFiles = competition.categories.collect { CompetitionCategory competitionCategory ->
            generateGridForCategory(competition.id, competitionCategory)
        }

        return null
    }

    private File generateGridForCategory(Integer competitionId, CompetitionCategory competitionCategory) {
        List<RegistratedSportsman> sportsmen = registratedSportsmanService.findSportsmenByCompetitionIdAndCategoryId(competitionId, competitionCategory.id)
        CompetitionCategoryGridPairProcessor processor = new CompetitionCategoryGridPairProcessor(sportsmen: sportsmen)
        CompetitionCategoryGridItem root = processor.divide()
        return gridDrawer.drawGridToFile(competitionCategory, root, buildTempDirectoryPath(competitionId))
    }

    String buildTempDirectoryPath(Integer competitionId) {
        String tempDirectoryPath = String.format("%s/%d-%s", fileWriterConfig.tempDirectoryPath, competitionId, UUID.randomUUID().toString())
        File directory = new File(tempDirectoryPath)
        directory.mkdir()
        return tempDirectoryPath
    }
}


