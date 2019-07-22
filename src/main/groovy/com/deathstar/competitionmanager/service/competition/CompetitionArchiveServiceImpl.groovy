package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.service.grid.draw.FileWriterConfig
import com.deathstar.competitionmanager.util.TranslitUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompetitionArchiveServiceImpl implements CompetitionArchiveService {

    @Autowired
    CompetitionService competitionService

    @Autowired
    FileWriterConfig fileWriterConfig

    @Autowired
    TranslitUtils translitUtils

    @Override
    Tuple2<File, String> getCompetitionArchive(String archiveName) {
        String competitionId = archiveName.substring(0, archiveName.indexOf("-"))
        Competition competition = competitionService.findById(competitionId.toInteger())
        if (!competition) {
            throw new EntityNotFoundException("Competition with id ${competitionId} is not found".toString())
        }

        File archive = new File(fileWriterConfig.tempDirectoryPath + '/' + archiveName)

        String encodedArchiveName = translitUtils.convertToTranslit(competition.name)
        String archiveResponseName = "${encodedArchiveName}.zip"

        return new Tuple2<File, String>(archive, archiveResponseName)
    }
}
