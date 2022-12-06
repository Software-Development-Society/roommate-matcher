package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.Questions
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

import java.lang.reflect.Array
import java.util.concurrent.atomic.AtomicReferenceArray

@RestController
class UserController {
    JsonSlurper slurper = new JsonSlurper()
    /*
    input -> {"user_id":"@e3245y35, "value","first_name"}
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





    @GetMapping("/sample")
    String createSampleData(){
        ArrayList<ArrayList<Integer>> user1Form = new ArrayList<>()
        ArrayList<ArrayList<Integer>> user2Form = new ArrayList<>()
        ArrayList<ArrayList<Integer>> user3Form = new ArrayList<>()
        ArrayList<Integer> type1Val = new ArrayList<>()
        type1Val.add(1)
        type1Val.add(3)
        type1Val.add(6)
        type1Val.add(9)
        type1Val.add(11)

        ArrayList<Integer> type2Val = new ArrayList<>()
        type2Val.add(1)
        type2Val.add(2)
        type2Val.add(4)
        type2Val.add(5)

        for(int index = 0; index < 30; index++){
            Random rand = new Random()
            int val1 = type1Val.get(rand.nextInt(5))
            int val2 = type2Val.get(rand.nextInt(4))
            ArrayList<Integer> user1Add = new ArrayList<>()
            user1Add.add(val1)
            user1Add.add(val2)
            user1Form.add(user1Add)

            val1 = type1Val.get(rand.nextInt(5))
            val2 = type2Val.get(rand.nextInt(4))
            ArrayList<Integer> user2Add = new ArrayList<>()
            user2Add.add(val1)
            user2Add.add(val2)
            user2Form.add(user2Add)

            val1 = type1Val.get(rand.nextInt(5))
            val2 = type2Val.get(rand.nextInt(4))
            ArrayList<Integer> user3Add = new ArrayList<>()
            user3Add.add(val1)
            user3Add.add(val2)
            user3Form.add(user3Add)
        }



        ObjectId user1_id = new ObjectId("6351c8862c74567ed4f3d700")
        ObjectId user2_id = new ObjectId("6351ce2d697724116164bb0b")
        ObjectId user3_id = new ObjectId("6351dc9911d187bdda4bd064")


        DatabaseServices.saveFormResponses(user1_id, "male", user1Form)
        DatabaseServices.saveFormResponses(user2_id, "male", user2Form)
        DatabaseServices.saveFormResponses(user3_id, "male", user3Form)
        return "works"
    }

}
