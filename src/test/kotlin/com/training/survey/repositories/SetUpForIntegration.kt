package com.training.survey.repositories

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.training.survey.UiApplication
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(UiApplication::class))
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = [
"amazon.dynamodb.endpoint=http://localhost:8000/",
"amazon.aws.accesskey=key",
"amazon.aws.secretkey=key2" ])
@Ignore
class SetUpForIntegration {

    lateinit var dynamoDBMapper: DynamoDBMapper

    @Autowired
    lateinit var amazonDynamoDB: AmazonDynamoDB


    @Before
    fun setUp() {
        dynamoDBMapper = DynamoDBMapper(amazonDynamoDB)

        val tableRequest: CreateTableRequest = dynamoDBMapper.generateCreateTableRequest(Question::class.java)
        tableRequest.provisionedThroughput = ProvisionedThroughput(1L, 1L)
        amazonDynamoDB.createTable(tableRequest)

        val answerTableRequest : CreateTableRequest = dynamoDBMapper.generateCreateTableRequest(Answer::class.java)
        answerTableRequest.provisionedThroughput = ProvisionedThroughput(1L,1L)
        amazonDynamoDB.createTable(answerTableRequest)

        val surveyInfoTableRequest : CreateTableRequest = dynamoDBMapper.generateCreateTableRequest(SurveyInfo::class.java)
        surveyInfoTableRequest.provisionedThroughput = ProvisionedThroughput(1L,1L)
        amazonDynamoDB.createTable(surveyInfoTableRequest)
    }

    /*@After
    fun tearDown() {
        val answerDeleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(Answer::class.java)
        amazonDynamoDB.deleteTable(answerDeleteTableRequest)

        val surveyInfoDeleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(SurveyInfo::class.java)
        amazonDynamoDB.deleteTable(surveyInfoDeleteTableRequest)

        val deleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(Question::class.java)
        amazonDynamoDB.deleteTable(deleteTableRequest)
    }*/

    @Test
    fun `set up everything`(){
        println("All Set Up")
    }



}
