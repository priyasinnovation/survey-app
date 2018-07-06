package com.training.survey.repositories

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.training.survey.UiApplication
import org.junit.*
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
class QuestionRepositoryIT {

    lateinit var dynamoDBMapper : DynamoDBMapper

    @Autowired
    lateinit var amazonDynamoDB : AmazonDynamoDB

    @Autowired
    lateinit var questionService: QuestionRepository

    @Before
    fun setUp(){
        dynamoDBMapper = DynamoDBMapper(amazonDynamoDB)

        val tableRequest : CreateTableRequest = dynamoDBMapper.generateCreateTableRequest(Question::class.java)
        tableRequest.provisionedThroughput = ProvisionedThroughput(1L,1L)
        amazonDynamoDB.createTable(tableRequest)
    }

    @After
    fun tearDown(){
        val deleteTableRequest = dynamoDBMapper.generateDeleteTableRequest(Question::class.java)
        amazonDynamoDB.deleteTable(deleteTableRequest)
    }

    @Test
    fun `save then retrieve all to the question table`(){
        val question = Question("1","Fun times")
        questionService.save(question)

        val questions = questionService.findAll().toList()
        assertNotNull(questions)
        assertEquals(1,questions.size)
        assertEquals("Fun times",questions[0].question)
        assertEquals("1",questions[0].id)
    }

    @Test
    fun `save then retrieve one from the question table`(){
        val question = Question("1","Fun times")
        questionService.save(question)

        val questions = questionService.findById("1")
        assertNotNull(questions)
        assertTrue(questions.isPresent)
        assertEquals("Fun times",questions.get().question)
        assertEquals("1",questions.get().id)
    }

}