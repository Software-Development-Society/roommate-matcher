package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.services.DatabaseServices
import groovy.json.JsonSlurper
import org.bson.types.ObjectId
import org.json.JSONObject
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

class UserController {
    JsonSlurper slurper = new JsonSlurper()
    @PostMapping("/getInfo")
    String getUserInfo(@RequestBody String info){
        println("The request for getting user info is: " +info)
        Map<String, String> inputString = slurper.parseText(info) as Map<String, String>
        ObjectId userId = new ObjectId(inputString["user_id"])
        User currentUser = DatabaseServices.getUser(userId)
        String valueToReturn = inputString["value"]
        String returnValue = valueToReturn
        if(valueToReturn == "first_name"){
            valueToReturn = currentUser.getFirstName()
        }
        else if(valueToReturn == "last_name") {
            valueToReturn = currentUser.getLastName()
        }
        else if(valueToReturn == "email") {
            valueToReturn = currentUser.getEmail()
        }
        else if(valueToReturn == "sex") {
            valueToReturn = currentUser.getSex()
        }
        else if(valueToReturn == "class_year") {
            valueToReturn = currentUser.getClassYear()
        }
        else if(valueToReturn == "age") {
            valueToReturn = currentUser.getAge()
        }
        else if(valueToReturn == "bio") {
            valueToReturn = currentUser.getBio()
        }
        else if(valueToReturn == "answer_list") {
            valueToReturn = currentUser.getAnswerList()
        }
        else if(valueToReturn == "matched_id") {
            valueToReturn = currentUser.getMatchedId()
        }
        JSONObject returnPayload = new JSONObject()
        returnPayload.put(returnValue, valueToReturn)
        return returnPayload as String
    }
}
