package com.vsds.matcherapi.database

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

import java.lang.reflect.Array
/*
This file contains the object for the questions responses.
Contains the user_id, the sex and the users responses.
*/
@Document(collection = "questions")
class Questions {
    @Id
    ObjectId user_id
    @Field
    String sex
    @Field
    ArrayList<ArrayList<Integer>> responses

    Questions(String sex, ArrayList<ArrayList<Integer>> responses) {
        this.sex = sex
        this.responses = responses
    }

    Questions(){}

    Questions(ObjectId user_id, String sex, ArrayList<ArrayList<Integer>> responses) {
        this.user_id = user_id
        this.sex = sex
        this.responses = responses
    }

    ObjectId getUser_id() {
        return user_id
    }

    String getSex() {
        return sex
    }

    ArrayList<ArrayList<Integer>> getResponses() {
        return responses
    }

    void setUser_id(ObjectId user_id) {
        this.user_id = user_id
    }

    void setSex(String sex) {
        this.sex = sex
    }

    void setResponses(ArrayList<ArrayList<Integer>> responses) {
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
