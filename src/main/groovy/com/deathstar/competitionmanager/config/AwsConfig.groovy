package com.deathstar.competitionmanager.config

import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig {

    @Value('${cm.aws.region}')
    String awsRegionName

    @Bean
    AmazonS3 amazonS3Client() {
        Region region = Region.getRegion(Regions.fromName(awsRegionName))

        return AmazonS3ClientBuilder.standard()
                .withRegion(awsRegionName)
                .build()
    }
}
