package com.training.survey.repositories

import com.amazonaws.services.dynamodbv2.datamodeling.*


@DynamoDBTable(tableName = "surveyInfo")
data class SurveyInfo @JvmOverloads constructor(@DynamoDBHashKey
                                                @DynamoDBAutoGeneratedKey var id: String? = "",
                                                @DynamoDBAttribute(attributeName = "answers") var answers:
                                                List<Answer> = mutableListOf(),
                                                @DynamoDBAttribute(attributeName = "sprint") var sprint: Int = 1)


@DynamoDBTable(tableName = "answer")
data class Answer @JvmOverloads constructor(@DynamoDBHashKey
                                            @DynamoDBAutoGeneratedKey var id : String = "",
                                            @DynamoDBAttribute var question: String = "",
                                            @DynamoDBAttribute var status : String = "",
                                            @DynamoDBAttribute var response : String = "")