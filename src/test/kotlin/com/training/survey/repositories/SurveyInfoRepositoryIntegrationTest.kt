package com.training.survey.repositories

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.training.survey.UiApplication
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(UiApplication::class))
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = [
"amazon.dynamodb.endpoint=http://localhost:8000/",
"amazon.aws.accesskey=yourAccessKey",
"amazon.aws.secretkey=yourSecretKey" ])
class SurveyInfoRepositoryIntegrationTest {

    lateinit var dynamoDBMapper : DynamoDBMapper

    @Autowired
    lateinit var amazonDynamoDB : AmazonDynamoDB

    @Autowired
    lateinit var surveyRepository: SurveyRepository

    @Before
    fun setUp(){
        dynamoDBMapper = DynamoDBMapper(amazonDynamoDB)

        val answerTableRequest : CreateTableRequest = dynamoDBMapper.generateCreateTableRequest(Answer::class.java)
        answerTableRequest.provisionedThroughput = ProvisionedThroughput(1L,1L)
        amazonDynamoDB.createTable(answerTableRequest)

        val surveyInfoTableRequest : CreateTableRequest = dynamoDBMapper.generateCreateTableRequest(SurveyInfo::class.java)
        surveyInfoTableRequest.provisionedThroughput = ProvisionedThroughput(1L,1L)
        amazonDynamoDB.createTable(surveyInfoTableRequest)

    }

    @After
    fun tearDown(){
        val answerDeleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(Answer::class.java)
        amazonDynamoDB.deleteTable(answerDeleteTableRequest)

        val surveyInfoDeleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(SurveyInfo::class.java)
        amazonDynamoDB.deleteTable(surveyInfoDeleteTableRequest)
    }

    @Test
    fun `save then retrive all to the surveyInfo table`(){
        saveSurveyInfo()

        val surveyResults = surveyRepository.findAll().toList()
        assertNotNull(surveyResults)
        assertEquals("2",surveyResults[0].answers[0].question)
        assertEquals("Red",surveyResults[0].answers[0].status)
        assertEquals("it sucks",surveyResults[0].answers[0].response)
    }

    private fun saveSurveyInfo() {
        val answer = Answer("1", "2","Red", "it sucks")
        val surveyInfo = SurveyInfo("1", mutableListOf(answer))
        surveyRepository.save(surveyInfo)
    }

    @Test
    fun `save then retrive one from the surveyInfo table`(){
        saveSurveyInfo()

        val surveyResult = surveyRepository.findById("1")
        assertNotNull(surveyResult)
        assertTrue(surveyResult.isPresent)
        assertEquals(1,surveyResult.get().answers.size)
        assertEquals("1",surveyResult.get().id)
        assertEquals("it sucks",surveyResult.get().answers[0].response)
        assertEquals("Red",surveyResult.get().answers[0].status)
    }

    @Test
    fun `save then retrieve by sprint number`(){
        val answer = Answer("1", "2","Red", "it sucks")
        val surveyInfoFromPerson1 = SurveyInfo("1", mutableListOf(answer),1)
        val surveyInfoFromPerson2 = SurveyInfo("2", mutableListOf(answer),1)
        val surveyInfoFromPerson3 = SurveyInfo("3", mutableListOf(answer),2)

        surveyRepository.saveAll(mutableListOf(surveyInfoFromPerson1,surveyInfoFromPerson2,surveyInfoFromPerson3))

        val surveyResult = surveyRepository.findBySprint(1)
        assertNotNull(surveyResult)
        assertEquals(2,surveyResult.size)
    }

}