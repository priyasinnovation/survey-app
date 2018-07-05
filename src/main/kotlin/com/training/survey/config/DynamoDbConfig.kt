package com.training.survey.config

import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableDynamoDBRepositories(basePackages = ["com.training.survey.repositories"])
open class DynamoDbConfig( @Value("\${amazon.dynamodb.endpoint}") val dynamoDbEndPoint: String,
                      @Value("\${amazon.aws.accesskey}") val amazonAccessKey: String,
                      @Value("\${amazon.aws.secretkey}")val amazonSecretKey: String) {


    @Bean
    fun amazonDynamoDB() : AmazonDynamoDBClient {
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
