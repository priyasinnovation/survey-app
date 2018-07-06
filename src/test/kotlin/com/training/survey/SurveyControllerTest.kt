package com.training.survey

import com.training.survey.bo.*
import com.training.survey.service.SurveyService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SurveyControllerTest{

    @InjectMocks
    lateinit var surveyController : SurveyController

    @Mock
    lateinit var surveyService : SurveyService


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `given a sprint number getSurveys returns surveys for that sprint`() {

        val questionAggregate1 : QuestionAggregate = QuestionAggregate("123",
                Status(red = 0, green = 0, yellow = 1),
                mutableListOf("Its boring"))
        val questionAggregate2 : QuestionAggregate = QuestionAggregate("125",
                Status(red = 0, green = 1, yellow = 1),
                mutableListOf("Its boring"))
        val surveyResults : SurveyResults = SurveyResults(mutableListOf(questionAggregate1, questionAggregate2))
        Mockito.`when`(surveyService.getResults(1)).thenReturn(surveyResults)
        val result = surveyController.getSurveys(1)
        assertNotNull(result)
        assertNotNull(result.questions)
        assertEquals(2,result.questions.size)
        assertEquals("123",result.questions[0].id)
        assertEquals("125",result.questions[1].id)
    }

    @Test
    fun `survey id is returned`(){
        val surveyQuestion : SurveyQuestion = SurveyQuestion("Fun", "Red", "it sucks")
        val surveyAnswer : SurveyAnswer = SurveyAnswer(1, mutableListOf(surveyQuestion))

        Mockito.`when`(surveyService.submitResponse(surveyAnswer)).thenReturn("123")
        val result = surveyController.submitResponse(surveyAnswer)
        assertNotNull(result)
        assertEquals("123",result)
    }



}