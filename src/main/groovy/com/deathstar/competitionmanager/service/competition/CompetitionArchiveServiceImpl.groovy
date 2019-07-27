package com.deathstar.competitionmanager.service.competition

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.GeneratedGrid
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import com.deathstar.competitionmanager.service.grid.GeneratedGridService
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

    @Autowired
    GeneratedGridService generatedGridService

    @Override
    Tuple2<InputStream, String> getCompetitionArchive(String archiveName) {
        String competitionId = archiveName.substring(0, archiveName.indexOf("-"))
        Competition competition = competitionService.findById(competitionId.toInteger())
        if (!competition) {
            throw new EntityNotFoundException("Competition with id ${competitionId} is not found".toString())
        }

        GeneratedGrid generatedGrid = generatedGridService.findByKey(archiveName)

        String encodedArchiveName = translitUtils.convertToTranslit(competition.name)
        String archiveResponseName = "${encodedArchiveName}.zip"

        return new Tuple2<InputStream, String>(generatedGrid.body.getBinaryStream(), archiveResponseName)
    }
}
