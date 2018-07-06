package com.training.survey

import com.training.survey.bo.Question
import com.training.survey.service.QuestionService
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class QuestionControllerTest{

    @InjectMocks
    lateinit var questionController: QuestionController

    @Mock
    lateinit var questionService : QuestionService


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `returns all questions with id and name`(){
        val question = Question("123","Is it fun for you")
        val question2 = Question("125","Collaboration")

        val questions = mutableListOf(question,question2)
        Mockito.`when`(questionService.getQuestions()).thenReturn(questions)
        val questionsResponse = questionController.getAllQuestions()
        assertEquals("123",questionsResponse[0].id)
        assertEquals("Is it fun for you",questionsResponse[0].name)
        assertEquals("125",questionsResponse[1].id)
        assertEquals("Collaboration",questionsResponse[1].name)
    }

    @Test
    fun `saving a new question returns an id`(){
        val question = Question("123","Is it fun for you")

        Mockito.`when`(questionService.save(question)).thenReturn("123")
        val questionsResponse = questionController.save(question)
        assertNotNull(questionsResponse)
        assertEquals("123",questionsResponse)
    }


}