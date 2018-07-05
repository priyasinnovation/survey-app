package com.training.survey.service

import com.training.survey.repositories.Question
import com.training.survey.repositories.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired


class QuestionService {

    @Autowired
    lateinit var questionRepository: QuestionRepository


    fun getQuestions() : List<Question> {

        return questionRepository.findAll() as List<Question>
    }
}