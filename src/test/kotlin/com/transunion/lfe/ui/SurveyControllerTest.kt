package com.transunion.lfe.ui

import com.transunion.lfe.ui.bo.SurveyAnswer
import org.junit.Assert.*
import org.junit.Test
import kotlin.test.assertTrue

class SurveyControllerTest{

    val surveyController = SurveyController()

        @Test
        fun `get surveys returns date`() {
            val result = surveyController.getSurveys()
            assertNotNull(result)
            assertNotNull(result.date)
        }

        @Test
        fun `get surveys returns answers to survey questions`() {
            val result = surveyController.getSurveys()
            assertNotNull(result)
            assertNotNull(result.questions)
            assertTrue(result.questions.isNotEmpty())
        }

        @Test
        fun `each answer has the associated question`() {
            val result = surveyController.getSurveys()
            assertNotNull(result)
            assertNotNull(result.questions)
            assertTrue(result.questions.isNotEmpty())
            assertNotNull(result.questions[0].question)
        }

        @Test
        fun `each question has a status`() {
            val result = surveyController.getSurveys()
            assertNotNull(result)
            assertNotNull(result.questions)
            assertTrue(result.questions.isNotEmpty())
            assertNotNull(result.questions[0].question)
            assertNotNull(result.questions[0].status.green)
        }

        @Test
        fun `each question has responses`() {
            val result = surveyController.getSurveys()
            assertNotNull(result)
            assertNotNull(result.questions)
            assertTrue(result.questions.isNotEmpty())
            assertNotNull(result.questions[0].question)
            assertTrue(result.questions[0].response.isNotEmpty())
            assertNotNull(result.questions[0].response)
        }

        @Test
        fun `user is able to submit the response`(){
            val result = surveyController.submitResponse(SurveyAnswer(mutableListOf()))
            assertNotNull(result)
        }



}