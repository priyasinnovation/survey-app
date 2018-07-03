package com.transunion.lfe.ui.repository

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface SurveyRepository : CrudRepository<SurveyInfo,String>