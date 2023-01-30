package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.User.MatchUsers
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
    return -> {"user_id" : "ewfw23r23", "first_name" : "John", "last_name" : "doe" ... }
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
        MatchUsers.matchUsers()
        return "works"
    }


}
