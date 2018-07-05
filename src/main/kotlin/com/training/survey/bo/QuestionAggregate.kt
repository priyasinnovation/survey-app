package com.training.survey.bo

data class QuestionAggregate(val id: String, val status: Status, val responses: List<String>)