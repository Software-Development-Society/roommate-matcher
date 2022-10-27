package com.vsds.matcherapi.database

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
@Document
interface MatchListRepository extends MongoRepository<MatchList, String>{

}