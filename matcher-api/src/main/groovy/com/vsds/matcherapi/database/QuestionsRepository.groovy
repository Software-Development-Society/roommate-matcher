package com.vsds.matcherapi.database

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
@Document
interface QuestionsRepository extends MongoRepository<Questions, String>{

}