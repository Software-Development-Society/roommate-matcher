package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.User.MatchUsers
import com.vsds.matcherapi.services.DatabaseServices
import groovy.json.JsonSlurper
import org.bson.types.ObjectId
import org.json.JSONObject
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

import java.lang.reflect.Array

@RestController
class FormController {

    JsonSlurper slurper = new JsonSlurper()


    /*
    Input -> {"user_id" : "24t324t5","sex" : "male", "responses" : [[4,1,3],[5,3,4]...]}
    This method saves the response into the responses collection
    Returns -> {"true" : "Response succesfully saved"} or {"false" : "error message"}

     */

    @PostMapping("/save-responses")
    String saveUserForm(@RequestBody String input){
        println("Saving user responses: "+ input)

        def response = slurper.parseText(input)

        ObjectId user_id = new ObjectId(response["user_id"])
        
        String sex = response["sex"]
        ArrayList<ArrayList<Integer>> userResponse = response["response"] as ArrayList<ArrayList<Integer>>
        println(userResponse)

        DatabaseServices.saveFormResponses(user_id, sex, userResponse)

        MatchUsers.matchUsers()

        JSONObject returnPayload = new JSONObject()
        returnPayload.put("true", "Form response saved!")
    }





//    static void main(String[] args) {
//        ArrayList<Integer> possibleQuestion1 = new ArrayList<>()
//        possibleQuestion1.add(1)
//        possibleQuestion1.add(3)
//        possibleQuestion1.add(5)
//        possibleQuestion1.add(7)
//        possibleQuestion1.add(9)
//        possibleQuestion1.add(11)
//
//        ArrayList<Integer> possibleQuestion2 = new ArrayList<>()
//        possibleQuestion2.add(1)
//        possibleQuestion2.add(2)
//        possibleQuestion2.add(4)
//        possibleQuestion2.add(5)
//
//        ArrayList<Integer> possibleQuestion3 = new ArrayList<>()
//        possibleQuestion3.add(2)
//        possibleQuestion3.add(4)
//        possibleQuestion3.add(6)
//        possibleQuestion3.add(8)
//        possibleQuestion3.add(10)
//
//        ArrayList<ArrayList<Integer>> result = new ArrayList<>()
//        for(int index = 0; index < 30; index++){
//            result.add(new ArrayList<Integer>())
//            Random rand = new Random()
//
//            int val1 = possibleQuestion1.get(rand.nextInt(5))
//            int val2 = possibleQuestion2.get(rand.nextInt(3))
//            int val3 = possibleQuestion3.get(rand.nextInt(4))
//
//            result.get(index).add(val1)
//            result.get(index).add(val2)
//
//
//
//        }
//        println result
//
//
//    }

}

