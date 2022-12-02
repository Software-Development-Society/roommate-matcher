package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.services.DatabaseServices
import com.vsds.matcherapi.services.UserServices
import groovy.json.JsonSlurper
import org.bson.types.ObjectId
import org.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    JsonSlurper slurper = new JsonSlurper()
    /*
    input -> {"user_id":"@e3245y35, "value": "first_name"}
    takes the initial json and returns a json containing the information requested
    return json -> {"first_name":"John"}
     */
    @PostMapping("/get-info")
    String getUserInfo(@RequestBody String info){
        print(info)
        def inputString = slurper.parseText(info)
        ObjectId userId = new ObjectId(inputString["user_id"])
        Users currentUser = DatabaseServices.getUserFromId(userId)
        ArrayList<String> values = new ArrayList<String>(inputString["values"])
        JSONObject returnPayload = new JSONObject()
        returnPayload.put("user_id", userId)

        for(int i=0; i< values.size(); i++){
            String value = values.get(i)
            returnPayload.put(value, UserServices.returnUserInfo(currentUser, value))

        }
        return returnPayload as String
    }
    
    /*
    input -> json containing a user id and anything the user wants to update about their profile
    {"user_id" : "31rt2f13r", "first_name" : "John" .... }
    method parses the json and updates any information inside of it in the db.
    return -> {"true" : "user updated successfully"} or {"false" : "error message"}
    */

    @PostMapping("/update-user")
    String updateUser(@RequestBody String input){
        def updateInformation = slurper.parseText(input);
        ObjectId user_id = new ObjectId(updateInformation["user_id"])
        User currentUser = DatabaseServices.getUser(user_id)
        UserServices.updateUserFullJson(updateInformation as Map<String, String>, currentUser)
        JSONObject returnPayload = new JSONObject()
        returnPayload.put("true", "user updated")
        return returnPayload as String
    }
    @PostMapping("/get-matches")
    public String getMatches(@RequestBody String info) {
        print(info)
        def inputString = slurper.parseText(info)
        ObjectId user_id = new ObjectId(inputString["user_id"])
        Map<ObjectId, ArrayList<Integer>> matches = DatabaseServices.returnMatchList(user_id)


    }


}
