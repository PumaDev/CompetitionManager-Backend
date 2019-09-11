package com.deathstar.competitionmanager.service.storage

interface StorageService {

    String saveContent(String type, InputStream contentInputStream, String path)

    InputStream getContent(String type, String path)

    void batchDeleteContent(String type, List<String> paths)

    void deleteContent(String type, String path)
}