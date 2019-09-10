package com.deathstar.competitionmanager.service.storage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.DeleteObjectsRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class S3StorageServiceImpl implements StorageService {

    @Autowired
    AmazonS3 amazonS3Client

    @Override
    String saveContent(String type, InputStream contentInputStream, String path) {
        return amazonS3Client.putObject(new PutObjectRequest(type, path, contentInputStream, new ObjectMetadata()))
    }

    @Override
    String getContent(String type, String path) {
        return amazonS3Client.getObject(type, path)
    }

    @Override
    void batchDelete(String type, List<String> paths) {
        amazonS3Client.deleteObjects(
                new DeleteObjectsRequest()
                        .withBucketName(type)
                        .withKeys(paths.collect { new DeleteObjectsRequest.KeyVersion(it) })
        )
    }
}
