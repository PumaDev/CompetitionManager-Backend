package com.deathstar.competitionmanager.service.grid.draw

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
final class FileWriterConfig {

    static final String TEMP_DIRECTORY = System.getProperty('java.io.tmpdir').endsWith(File.separator) ? System.getProperty('java.io.tmpdir') : System.getProperty('java.io.tmpdir') + File.separator

    @Value('${com.ds.competition-manager.grid-generation.temp-directory-name:competition-manager-grids}')
    String directoryName

    String tempDirectoryPath

    @PostConstruct
    void init(){
        tempDirectoryPath = TEMP_DIRECTORY + directoryName
        File file = new File(tempDirectoryPath)
        if (file.exists() && file.isFile()) {
            throw new RuntimeException("Temp directory is a file. Please select another temp directory")
        }
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("Cannot create temp directory.")
            }
        }
    }
}
