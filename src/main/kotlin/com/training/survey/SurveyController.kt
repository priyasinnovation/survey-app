package com.training.survey

import com.training.survey.bo.SurveyAnswer
import com.training.survey.service.SurveyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class SurveyController{

    @Autowired
    lateinit var surveyService8: SurveyService

    @GetMapping("/results")
    fun getSurveys(@RequestParam(name = "sprint") sprint : Int) = surveyService8.getResults(sprint)


    @PostMapping("/survey")
    fun submitResponse(@Valid @RequestBody surveyAnswer: SurveyAnswer) = surveyService8.submitResponse(surveyAnswer)

}
