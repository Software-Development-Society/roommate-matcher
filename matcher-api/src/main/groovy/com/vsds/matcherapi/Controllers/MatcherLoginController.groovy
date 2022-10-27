package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.Questions
import com.vsds.matcherapi.database.QuestionsRepository
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.services.DatabaseServices
import groovy.json.JsonSlurper
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MatcherLoginController {
    JsonSlurper slurper = new JsonSlurper()



    /*
    input - takes a JSON formatted as {user_id:@hfgsih2w413}
    method parses the json and converts it into a map with the key value pairs.
    it then goes to the db and returns a json containing the user information
     */
    @PostMapping("/login")
    String login(@RequestBody String login){
        println("Request body is: " +login)
        def inputString = slurper.parseText(login)
        ObjectId userId = new ObjectId(inputString["user_id"])
        println("User Id: " + userId)
        User currentUser = DatabaseServices.getUser(userId)
        println("New user logged in: " + currentUser.getFirstName())
        return currentUser.returnUser() as String
    }


    @GetMapping("/test")
    String addUser(){
        ArrayList<ArrayList<Integer>> questions = new ArrayList<>()
        ArrayList<Integer> question1 = new ArrayList<>()
        question1.add(5)
        question1.add(3)

        ArrayList<Integer> question2 = new ArrayList<>()
        question2.add(2)
        question2.add(3)

        questions.addAll(question1, question2)


        Questions addQuestions = new Questions(questions)
        MatcherApiApplication.visableQuestionRepo.save(addQuestions)
    }


}
