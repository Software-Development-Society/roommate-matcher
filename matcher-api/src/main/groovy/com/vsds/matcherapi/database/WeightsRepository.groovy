package com.vsds.matcherapi.database

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
@Document(collection = "weights")
interface WeightsRepository extends MongoRepository<Weights, String> {

}