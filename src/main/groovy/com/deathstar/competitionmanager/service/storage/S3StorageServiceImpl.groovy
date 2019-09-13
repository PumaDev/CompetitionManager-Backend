package com.deathstar.competitionmanager.service.storage

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.DeleteObjectsRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.S3Object
import com.deathstar.competitionmanager.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class S3StorageServiceImpl implements StorageService {

    @Autowired
    AmazonS3 amazonS3Client

    @Value('${cm.storage.bucket:mke-cm-content}')
    String bucketName

    @Override
    String saveContent(String type, InputStream contentInputStream, String path) {
        return amazonS3Client.putObject(new PutObjectRequest(bucketName, "${type}/${path}", contentInputStream, new ObjectMetadata()))
    }

    @Override
    InputStream getContent(String type, String path) {
        S3Object foundObject = amazonS3Client.getObject(bucketName, "${type}/${path}")
        if (!foundObject) {
            throw new EntityNotFoundException("Content by path ${path} was not found")
        }
        foundObject.objectContent
    }

    @Override
    void batchDeleteContent(String type, List<String> paths) {
        amazonS3Client.deleteObjects(
                new DeleteObjectsRequest()
                        .withBucketName(bucketName)
                        .withKeys(paths.collect { new DeleteObjectsRequest.KeyVersion(bucketName, "${type}/${it}") })
        )
    }

    @Override
    void deleteContent(String type, String path) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, "${type}/${path}"))
    }
}
