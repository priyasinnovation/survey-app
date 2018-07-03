package com.transunion.lfe.ui.service

import com.transunion.lfe.ui.bo.Question
import com.transunion.lfe.ui.bo.SurveyAnswer
import com.transunion.lfe.ui.bo.SurveyQuestion
import com.transunion.lfe.ui.repository.SurveyInfo
import com.transunion.lfe.ui.repository.SurveyRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@RunWith(MockitoJUnitRunner::class)
class SurveyServiceTest{

    @InjectMocks
    lateinit var surveyService : SurveyService

    @Mock
    lateinit var surveyRepository : SurveyRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `survey service saves a surveyresponse`(){

        val question :
                com.transunion.lfe.ui.repository.Question = com.transunion.lfe.ui.repository.Question(
                "Quality",
                "Red",
                "No unit tests!! it sucks")
        val surveyInfo : SurveyInfo = SurveyInfo(null,mutableListOf(question))
        Mockito.`when`(surveyRepository.save(surveyInfo)).thenReturn(surveyInfo)

        val surveyQuestion : SurveyQuestion = SurveyQuestion(
                "Quality",
                "Red",
                "No unit tests!! it sucks")
        var surveyAnswer : SurveyAnswer = SurveyAnswer(mutableListOf(surveyQuestion))
        val surveyInfoResponse : SurveyInfo = surveyService.submitResponse(surveyAnswer)!!

        assertNotNull(surveyInfoResponse)
        assertNotNull(surveyInfoResponse.id)
        assertEquals("123",surveyInfoResponse.id)
    }
}