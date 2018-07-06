package com.training.survey.service

import com.training.survey.bo.Question
import com.training.survey.repositories.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class QuestionService {

    @Autowired
    lateinit var questionService: QuestionRepository


    fun getQuestions() : List<Question> {

        val questions = questionService.findAll() as List<com.training.survey.repositories.Question>

        return questions.stream().map { question ->  Question(question.id,question.question)}.collect(Collectors.toList())
    }

    fun save(question: Question): String {
        val questionEntity = com.training.survey.repositories.Question(question.id,question.name)
        return questionService.save(questionEntity).id
    }

    fun  getQuestion(id : String): Question? {
        try {
            val questionEntity = questionService.findById(id).get()
            val question = Question(questionEntity.id,questionEntity.question)
            return question
        }catch (e : NoSuchElementException){
            println(e)
        }
        return null
    }
}