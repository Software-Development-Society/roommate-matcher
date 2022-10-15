package com.vsds.matcherapi.Controllers


import com.vsds.matcherapi.User.User
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
        Users test = new Users("John", "Doe", "jdoe@villanova.edu", "male", 2024, 20, "This is a new user", answerList, matchedIds)
        DatabaseServices.updateUser(test)
        return test.getFirstName()
    }


    @PostMapping("/login")
    String login(@RequestBody String login){
        println(login)
        def inputString = slurper.parseText(login)
        ObjectId userId = new ObjectId(inputString["user_id"])
        println("User Id: " + userId)
        User currentUser = DatabaseServices.getUser(userId)
        println("New user logged in: " + currentUser.getFirstName())
        return currentUser.returnUser() as String
    }


}
