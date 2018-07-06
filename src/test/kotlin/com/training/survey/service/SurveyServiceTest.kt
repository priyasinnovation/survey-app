package com.training.survey.service

import com.training.survey.bo.SurveyAnswer
import com.training.survey.bo.SurveyQuestion
import com.training.survey.repositories.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.mockito.*
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class SurveyServiceTest {

    @InjectMocks
    lateinit var surveyService: SurveyService

    @Mock
    lateinit var surveyRepository: SurveyRepository

    @Mock
    lateinit var questionService: QuestionService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `survey service saves a survey response`() {
        val uuid = UUID.randomUUID().toString()
        val responseSurveyInfo = SurveyInfo(uuid, mutableListOf(),1)
        Mockito.`when`(surveyRepository.save(Matchers.any(SurveyInfo::class.java))).thenReturn(responseSurveyInfo)
        val question = SurveyQuestion("123","Red","Its Fun")
        val surveyAnswer = SurveyAnswer(1, mutableListOf(question))
        val response = surveyService.submitResponse(surveyAnswer)
        assertNotNull(response)
        assertEquals(uuid,response)
    }


    @Test
    fun `survey service returns results for a given sprint with the actual question`(){

        val answer: Answer = Answer("1","123", "Red", "Its boring 1")
        val answer2: Answer = Answer("1","125", "Red", "Its boring 2")
        val surveyInfo : SurveyInfo = SurveyInfo("1", mutableListOf(answer, answer2),1)
        val surveyInfo2 : SurveyInfo = SurveyInfo("2", mutableListOf(answer),1)
        val surveyInfo3 : SurveyInfo = SurveyInfo("2", mutableListOf(answer),2)

        val surveyInfoAggregate : List<SurveyInfo> = mutableListOf(surveyInfo,surveyInfo2,surveyInfo3)
        var question = com.training.survey.bo.Question("123","Is it fun for you")
        var question2 = com.training.survey.bo.Question("125","Is it fun for you")

        Mockito.`when`(questionService.getQuestion("123")).thenReturn(question)
        Mockito.`when`(questionService.getQuestion("125")).thenReturn(question2)

        Mockito.`when`(surveyRepository.findBySprint(1)).thenReturn(surveyInfoAggregate)
        val response = surveyService.getResults(1)

        assertNotNull(response)
        assertEquals(2,response.questions.size)
        assertEquals("Is it fun for you",response.questions[0].id)

    }

}