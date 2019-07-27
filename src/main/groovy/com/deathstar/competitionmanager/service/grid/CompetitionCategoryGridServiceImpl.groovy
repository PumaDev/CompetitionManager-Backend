package com.deathstar.competitionmanager.service.grid

import com.deathstar.competitionmanager.domain.Competition
import com.deathstar.competitionmanager.domain.CompetitionCategory
import com.deathstar.competitionmanager.domain.RegistratedSportsman
import com.deathstar.competitionmanager.service.grid.draw.FileWriterConfig
import com.deathstar.competitionmanager.service.grid.draw.GridDrawer
import com.deathstar.competitionmanager.service.grid.model.CompetitionCategoryGridItem
import com.deathstar.competitionmanager.service.sportsman.RegistratedSportsmanService
import groovy.util.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@Log
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
        }.findAll { it != null }

        File zipFile = packFilesToZip(gridsFiles, competition)
        gridsFiles.each { it.delete() }
        return zipFile
    }

    File packFilesToZip(List<File> gridFiles, Competition competition) {
        String zipFileName = String.format("%s/%d-%s.zip", fileWriterConfig.tempDirectoryPath, competition.id, UUID.randomUUID().toString())
        FileOutputStream zipFos = new FileOutputStream(zipFileName)
        ZipOutputStream zipOutputStream = new ZipOutputStream(zipFos)

        final int bufferSize = 1024
        byte[] buffer = new byte[bufferSize]

        gridFiles.forEach { gridFile ->
            ZipEntry zipEntry = new ZipEntry(gridFile.name)
            zipOutputStream.putNextEntry(zipEntry)

            FileInputStream gridFileInputStream = new FileInputStream(gridFile)
            int len
            while ((len = gridFileInputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, len)
            }

            gridFileInputStream.close()
            zipOutputStream.closeEntry()
        }

        zipOutputStream.close()

        return new File(zipFileName)
    }

    private File generateGridForCategory(Integer competitionId, CompetitionCategory competitionCategory) {
        List<RegistratedSportsman> sportsmen = registratedSportsmanService.findSportsmenByCompetitionIdAndCategoryId(competitionId, competitionCategory.id)
        if (sportsmen.isEmpty()) {
            return null
        }
        CompetitionCategoryGridPairProcessor processor = new CompetitionCategoryGridPairProcessor(sportsmen: sportsmen)
        CompetitionCategoryGridItem root = processor.divide()
        return gridDrawer.drawGridToFile(competitionCategory, root, buildTempDirectoryPath(competitionId))
    }

    String buildTempDirectoryPath(Integer competitionId) {
        String tempDirectoryPath = String.format("%s/%d-%s", fileWriterConfig.tempDirectoryPath, competitionId, UUID.randomUUID().toString())
        File directory = new File(tempDirectoryPath)
        directory.mkdir()
        log.info(String.format("Created directory for grids files: %s. Full path: %s", tempDirectoryPath, directory.getAbsolutePath()))
        return tempDirectoryPath
    }
}
