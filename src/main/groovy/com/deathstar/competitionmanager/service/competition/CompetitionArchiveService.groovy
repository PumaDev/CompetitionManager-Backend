package com.deathstar.competitionmanager.service.competition

interface CompetitionArchiveService {

    Tuple2<InputStream, String> getCompetitionArchive(String archiveName)
}