package com.training.survey.repositories

import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository
import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@EnableScan
@Repository
@Transactional
interface QuestionRepository : DynamoDBCrudRepository<Question, String>