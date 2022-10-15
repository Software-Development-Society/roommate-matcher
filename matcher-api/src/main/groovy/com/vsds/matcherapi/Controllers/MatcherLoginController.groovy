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

    // used for testing only!!!
    @GetMapping("/adduser")
    String user(){
        ArrayList<Integer> answerList = new ArrayList<>()
        Random rand = new Random()
        for (int i = 0; i < 30; i++){
            int firstNum = rand.nextInt(5)+1
            int secondNum = rand.nextInt(5)+1
            answerList.add(firstNum * secondNum)

        }
        Map<String, ArrayList<Integer>> matchedIds = new HashMap<>()
        ArrayList<Integer> values = new ArrayList<>()
        values.add(100)
        values.add(102)
        values.add(103)
        matchedIds.put("1000", values)
        DbUser test = new DbUser("Test", "User", "tuser@villanova.edu", "male", 2024, 20, "This is a test user", answerList, matchedIds)
        test.setUserId("1001")
        DatabaseServices.updateUser(test)
        return test.getFirstName()
    }


    @PostMapping("/login")
    String login(@RequestBody String login){
        def inputString = slurper.parseText(login)
        String userId = inputString["user_id"]
        User currentUser = DatabaseServices.getUser(userId)
        println("New user logged in: " + currentUser.getFirstName())
        return currentUser.returnUser() as String
    }


}
