package com.deathstar.competitionmanager.service.storage

interface StorageService {

    String saveContent(String type, InputStream contentInputStream, String path)

    String getContent(String type, String path)

    void batchDelete(String type, List<String> paths)
}