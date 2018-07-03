package com.transunion.lfe.ui.config

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableDynamoDBRepositories
class DynamoDbConfig(val dynamoDbEndPoint: String, val amazonAccessKey: String, val amazonSecretKey: String) {


    @Bean
    fun amazonDynamoDb() : AmazonDynamoDBClient {
        val amazonDynamoDBClient = AmazonDynamoDBClient(amazonAWSCredentials())
        amazonDynamoDBClient.setEndpoint(dynamoDbEndPoint)
        return amazonDynamoDBClient
    }


    @Bean
    fun amazonAWSCredentials() : BasicAWSCredentials {
        val basicAWSCredentials = BasicAWSCredentials(amazonAccessKey, amazonSecretKey)
        return basicAWSCredentials
    }

}
