package com.vsds.matcherapi.database

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.lang.reflect.Array

@Document(collection = "questions")
class Questions {
    @Id
    ObjectId user_id
    @Field
    ArrayList<ArrayList<Integer>> responses

    Questions(ArrayList<ArrayList<Integer>> responses) {
        this.responses = responses
    }
    Questions(){}

    Questions(ObjectId user_id, ArrayList<ArrayList<Integer>> responses) {
        this.user_id = user_id
        this.responses = responses
    }

    @Override
    public String toString() {
        return "Questions{" +
                "user_id=" + user_id +
                ", responses=" + responses +
                '}';
    }
}
