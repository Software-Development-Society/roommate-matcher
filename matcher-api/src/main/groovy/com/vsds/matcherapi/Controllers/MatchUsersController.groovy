package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.User.MatchUsers
import com.vsds.matcherapi.services.DatabaseServices
import groovy.json.JsonSlurper
import org.bson.types.ObjectId
import org.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import javax.xml.crypto.Data


@RestController
class MatchUsersController {
    JsonSlurper slurper = new JsonSlurper()


    @GetMapping("/match-users")
    String matchUsers(){
        MatchUsers.matchAllUsers()
        JSONObject returnPayload = new JSONObject()
        returnPayload.put("status", "true")
        return returnPayload as String
    }



    @GetMapping("/get-matches")
    String getMatches(@RequestBody String input) {
        println("Request body is: " +input)
        def parsedInput = slurper.parseText(input)
        ObjectId user_id = new ObjectId(parsedInput["user_id"])
        HashMap<ObjectId, Integer> matchList = DatabaseServices.getMatchListFromUserId(user_id)
        JSONObject returnPayload = new JSONObject()
        for(ObjectId current_id : matchList.keySet()){
            returnPayload.put(current_id as String, matchList.get(current_id))
        }

        return returnPayload as String
    }
}
