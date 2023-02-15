package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.MatchList
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.services.DatabaseServices
import groovy.json.JsonSlurper
import org.bson.types.ObjectId
import org.json.JSONObject
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class DashBoardController {
    JsonSlurper slurper = new JsonSlurper()

    /*
    method takes an input {"user_id" : "23rg35g35y"} and returns the matches for this user
    result is structured as {"matches_array" : ["user_id" : "13rg234tg34g", "first_name" : "John", "last_name" : "Doe", ......]}
     */
    @PostMapping("/get-matches")
    JSONObject getMatches(@RequestBody String input){
        println("Matches requested for:" +input)
        def inputString = slurper.parseText(input)
        ObjectId user_id = new ObjectId(inputString["user_id"])

        MatchList currentUserMatches = DatabaseServices.getMatches(user_id)
        Map<ObjectId, Integer> matches = currentUserMatches.getMatchList()
        ArrayList<JSONObject> resultList = new ArrayList<>()

        for (ObjectId match_id : matches.keySet()){
            Users currentUser = DatabaseServices.getUserFromId(match_id)
            if (currentUser.getSex() == DatabaseServices.getUserFromId(user_id).getSex() && currentUser.getUserId() != DatabaseServices.getUserFromId(user_id).getUserId()){
                JSONObject currentUserObject = new JSONObject()
                currentUserObject.put("user_id", currentUser.getUserId())
                currentUserObject.put("first_name", currentUser.getFirstName())
                currentUserObject.put("last_name", currentUser.getLastName())
                currentUserObject.put("username",currentUser.getUsername())
                currentUserObject.put("sex", currentUser.getSex())
                currentUserObject.put("class_year", currentUser.getClassYear())
                currentUserObject.put("bio", currentUser.getBio())
                currentUserObject.put("picture_name", currentUser.getPictureName())
                currentUserObject.put("instagram", currentUser.getInstagram())
                currentUserObject.put("snap", currentUser.getSnapChat())


                resultList.add(currentUserObject)

            }

        }

        JSONObject result = new JSONObject()
        result.put("match_array", resultList)

        return result
    }




}
