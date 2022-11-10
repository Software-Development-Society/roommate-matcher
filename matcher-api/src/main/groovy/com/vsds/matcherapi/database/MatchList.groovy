package com.vsds.matcherapi.database

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "matchlist")
class MatchList {
    @Id
    ObjectId userId
    @Field
    HashMap<ObjectId, Integer> matchList

    MatchList(HashMap<ObjectId, Integer> matchList) {
        this.matchList = matchList
    }

    MatchList(ObjectId userId, HashMap<ObjectId, Integer> matchList) {
        this.userId = userId
        this.matchList = matchList
    }

}
