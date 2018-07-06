package com.training.survey

import com.training.survey.bo.Question
import com.training.survey.service.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
class QuestionController {

    @Autowired
    lateinit var questionService : QuestionService

    @GetMapping("/questions")
    fun getAllQuestions(): List<Question> = questionService.getQuestions()

    @PostMapping("/question")
    fun save(@Valid @RequestBody question : Question): String = questionService.save(question)
}