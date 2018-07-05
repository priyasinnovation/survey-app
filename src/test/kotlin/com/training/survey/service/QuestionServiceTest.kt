package com.training.survey.service

import com.training.survey.repositories.Question
import com.training.survey.repositories.QuestionRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class QuestionServiceTest{

    @InjectMocks
    lateinit var questionService : QuestionService

    @Mock
    lateinit var questionRepository : QuestionRepository


    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `returns a list of questions`(){
        val questions = questionService.getQuestions()
        assertNotNull(questions)
    }

    @Test
    fun `each question has an id`(){
        val question : Question = Question("123", "It'fun")
        Mockito.`when`(questionRepository.findAll()).thenReturn(mutableListOf(question))
        val questions = questionService.getQuestions()
        assertNotNull(questions)
        assertNotNull(questions[0].id)
    }

    @Test
    fun `each question has a name`(){
        val question : Question = Question("123", "It'fun")
        Mockito.`when`(questionRepository.findAll()).thenReturn(mutableListOf(question))
        val questions = questionService.getQuestions()
        assertNotNull(questions)
        assertNotNull(questions[0].question)
    }

}