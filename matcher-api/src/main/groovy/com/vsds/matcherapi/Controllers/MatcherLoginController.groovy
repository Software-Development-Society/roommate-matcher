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

import javax.xml.crypto.Data

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
        def inputString = slurper.parseText(login)
        String userId = inputString["user_id"]
        User currentUser = DatabaseServices.getUser(userId)
        println("New user logged in: " + currentUser.getFirstName())
        return currentUser.returnUser() as String
    }


}
