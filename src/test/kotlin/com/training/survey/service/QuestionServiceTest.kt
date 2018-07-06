package com.training.survey.service

import com.training.survey.repositories.Question
import com.training.survey.repositories.QuestionRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
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
        val question : Question = Question("123", "It's fun")
        Mockito.`when`(questionRepository.findAll()).thenReturn(mutableListOf(question))
        val questions = questionService.getQuestions()
        assertNotNull(questions)
        assertNotNull(questions[0].id)
        assertNotNull(questions[0].name)
    }

    @Test
    fun `each question has a name`(){
        val question : Question = Question("123", "It's fun")
        Mockito.`when`(questionRepository.findAll()).thenReturn(mutableListOf(question))
        val questions = questionService.getQuestions()
        assertNotNull(questions)
        assertNotNull(questions[0].id)
        assertNotNull("It's fun",questions[0].name)

    }

    @Test
    fun `question can be persisted`(){
        val questionEntity = Question("123", "It's fun")
        val question = com.training.survey.bo.Question("123","It's fun")
        Mockito.`when`(questionRepository.save(Matchers.any(Question::class.java))).thenReturn(questionEntity)
        val questionResponse = questionService.save(question)
        assertNotNull(questionResponse)
        assertEquals("123",questionResponse)
    }

    @Test
    fun `question can be retrieved by id`(){
        val question : Question = Question("123", "It's fun")
        Mockito.`when`(questionRepository.findById("123")).thenReturn(Optional.of(question))
        val questionResponse = questionService.getQuestion("123")
        assertNotNull(questionResponse)
        assertEquals("123",questionResponse!!.id)
        assertNotNull(questionResponse)
        assertEquals("It's fun",questionResponse!!.name)
    }

}