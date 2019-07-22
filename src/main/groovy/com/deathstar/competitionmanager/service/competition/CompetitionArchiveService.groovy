package com.deathstar.competitionmanager.service.competition

interface CompetitionArchiveService {

    Tuple2<File, String> getCompetitionArchive(String archiveName)
}