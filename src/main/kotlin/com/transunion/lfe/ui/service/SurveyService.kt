package com.transunion.lfe.ui.service

import com.transunion.lfe.ui.bo.SurveyAnswer
import com.transunion.lfe.ui.repository.Question
import com.transunion.lfe.ui.repository.SurveyInfo
import com.transunion.lfe.ui.repository.SurveyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class SurveyService{

    @Autowired
    lateinit var surveyRepository : SurveyRepository

    fun submitResponse(surveyResponse: SurveyAnswer): SurveyInfo? {

        val surveyInfo : SurveyInfo = SurveyInfo()
        surveyInfo.questions = surveyResponse.questions
                .stream()
                .map { question -> Question(question.question,question.status,question.response) }
                .collect(Collectors.toList())!!

        return surveyRepository.save(surveyInfo)

    }
}