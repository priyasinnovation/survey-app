package com.transunion.lfe.ui

import com.transunion.lfe.ui.bo.Question
import com.transunion.lfe.ui.bo.Status
import com.transunion.lfe.ui.bo.SurveyAnswer
import com.transunion.lfe.ui.bo.SurveyResults
import com.transunion.lfe.ui.service.SurveyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid

@RestController
class SurveyController{

   /* @Autowired
    lateinit var surveyService : SurveyService*/

    @GetMapping("/surveys")
    fun getSurveys() = SurveyResults(Date(),
                                    mutableListOf(Question("Code Quality",
                                    Status(1,2,3),
                                    mutableListOf("I'm Happy"))))


    @PostMapping("/survey")
    fun submitResponse(@Valid @RequestBody surveyAnswer: SurveyAnswer) = "response submitted"

}