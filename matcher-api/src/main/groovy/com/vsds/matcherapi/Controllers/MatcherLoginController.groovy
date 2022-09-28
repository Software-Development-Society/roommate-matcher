package com.vsds.matcherapi.Controllers


import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.DbUser
import com.vsds.matcherapi.services.DatabaseServices
import com.vsds.matcherapi.services.UserServices
import groovy.json.JsonSlurper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MatcherLoginController {
    @GetMapping
    public String helloWorld(){
        return "hello world"
    }

    @PostMapping("/login-handling")
    public String loginHandling(String userId){


        return 'login success'


    }
    @GetMapping("/login-test")
    public String test(){
        User.loadCurrentUser("1000")
        User.setFirstName("John")
        User.updateDatabase()
        String test = User.getLastName()
        return test

    }
    @PostMapping("/login")
    void login(@RequestBody String login){
        println "The user id is: " +login
        JsonSlurper slurper = new JsonSlurper()
        def inputString = slurper.parseText(login)
        String userId = inputString["user_id"]
        User.loadCurrentUser(userId)
        String test = User.getLastName()
//        return test
    }

    @GetMapping
    String returnUser(){
        return User.getFirstName() + User.getLastName()
    }

}
