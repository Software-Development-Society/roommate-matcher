package com.vsds.matcherapi.database

import com.vsds.matcherapi.User.MatchUsers
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
/*
This object is used for the users matches in the database
user_id -> the current user
matchList -> user_id of matched user and an array of their respective match scores
*/
@Document(collection = "matchlist")
class MatchList {
    @Id
    ObjectId userId
    @Field
    Map<ObjectId, Integer> matchList

    MatchList() {
    }

    MatchList(Map<ObjectId, Integer> matchList) {
        this.matchList = matchList
    }

    MatchList(ObjectId userId, Map<ObjectId, Integer> matchList) {
        this.userId = userId
        this.matchList = matchList
    }



    ObjectId getUserId() {
        return userId
    }

    void setUserId(ObjectId userId) {
        this.userId = userId
    }

    Map<ObjectId, Integer> getMatchList() {
        return matchList
    }

    void setMatchList(Map<ObjectId, Integer> matchList) {
        this.matchList = matchList
    }


    @Override
    public String toString() {
        return "MatchList{" +
                "userId=" + userId +
                ", matchList=" + matchList +
                '}';
    }
}
