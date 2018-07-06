package com.training.survey

import com.training.survey.bo.Question
import com.training.survey.service.QuestionService
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class QuestionControllerTest{

    @InjectMocks
    lateinit var questionController: QuestionController

    @Mock
    lateinit var questionService : QuestionService


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

   /* @Test
    fun `returns all questions with id and name`(){
        val question = Question()
        val questions = mutableListOf(question)
        Mockito.`when`(questionService.getQuestions()).thenReturn()
    }*/




}