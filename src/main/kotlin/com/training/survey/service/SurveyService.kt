package com.training.survey.service

import com.training.survey.bo.QuestionAggregate
import com.training.survey.bo.Status
import com.training.survey.bo.SurveyAnswer
import com.training.survey.bo.SurveyResults
import com.training.survey.repositories.Answer
import com.training.survey.repositories.QuestionRepository
import com.training.survey.repositories.SurveyInfo
import com.training.survey.repositories.SurveyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors


@Service
class SurveyService {

    @Autowired
    lateinit var surveyRepository: SurveyRepository

    @Autowired
    lateinit var questionService: QuestionService

    fun submitResponse(surveyResponse: SurveyAnswer): String? {

        var surveyInfo: SurveyInfo = SurveyInfo()
        val uuid = UUID.randomUUID().toString()
        surveyInfo.id = uuid
        surveyInfo.sprint = surveyResponse.sprintNumber
        surveyInfo.answers = surveyResponse.questions
                .stream()
                .map { question -> Answer(uuid,question.question, question.status, question.response) }
                .collect(Collectors.toList())!!

        val surveyInfoResponse = surveyRepository.save(surveyInfo)

        return surveyInfoResponse.id
    }

    fun getResults(sprint: Int): SurveyResults {
        val surveyResults = surveyRepository.findBySprint(sprint)
        return SurveyResults(surveyResults
                .stream()
                .flatMap { surveyInfoResponse -> surveyInfoResponse.answers.stream() }
                .collect(Collectors.toList())
                .groupBy { it.question }
                .entries.stream()
                .map { entryResponse ->
                    QuestionAggregate(questionService.getQuestion(entryResponse.key).name,
                            generateStatus(entryResponse.value),
                            generateResponses(entryResponse.value))
                }
                .collect(Collectors.toList()))
    }

    private fun generateResponses(answers: List<Answer>): List<String> {
        return answers.parallelStream().map { answer -> answer.response }.collect(Collectors.toList())
    }

    private fun generateStatus(answers: List<Answer>): Status {

        val status: Status = Status()

        answers.forEach { answer ->
            when (answer.status) {
                "Red" -> status.red = status.red + 1
                "Green" -> status.green = status.green + 1
                "Yellow" -> status.yellow = status.yellow + 1
                else -> status
            }
        }
        return status
    }


}