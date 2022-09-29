package com.vsds.matcherapi.Controllers


import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.DbUser
import com.vsds.matcherapi.services.DatabaseServices
import com.vsds.matcherapi.services.UserServices
import groovy.json.JsonSlurper
import org.bson.json.JsonObject
import org.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MatcherLoginController {
    JsonSlurper slurper = new JsonSlurper()
    @GetMapping("/login-test")
    public String test(){
        User.loadCurrentUser("1000")
        User.setFirstName("John")
        User.updateDatabase()
        String test = User.getLastName()
        return test
    }
    @PostMapping("/login")
    String login(@RequestBody String login){
        def inputString = slurper.parseText(login)
        String userId = inputString["user_id"]
        User.loadCurrentUser(userId)
        println "User Successfully logged in!"
        return User.returnUser() as String
    }

    @GetMapping("/data")
    String returnUser(){
        JSONObject returnPayload = User.returnUser()
        return returnPayload as String
    }

}
