package com.vsds.matcherapi.Controllers

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
    Input -> {"user_id" : "24t324t5", "responses" : [[4,1,3],[5,3,4]...]}
    This method saves the response into the responses collection
    Returns -> {"true" : "Response succesfully saved"} or {"false" : "error message"}

     */

    @PostMapping("/save-responses")
    String saveUserForm(@RequestBody String input){
        println("User to save response.")

        def response = slurper.parseText(input)

        ObjectId user_id = new ObjectId(response["user_id"])
        String sex = response["sex"]
        ArrayList<ArrayList<Integer>> userResponses = response["responses"] as ArrayList<ArrayList<Integer>>

        // saves weightings and responses
        ArrayList<ArrayList<Integer>> formResponse = new ArrayList<>()
        ArrayList<Integer> weightings = new ArrayList<>()
        int counter = 0
        for (ArrayList<Integer> currentQuestion : userResponses){
            formResponse.add(new ArrayList<Integer>())
            for(int index = 0; index < currentQuestion.size(); index++){
                if (index < 2){
                    formResponse.get(counter++).add(currentQuestion.get(index))
                }
                else{
                    weightings.add(currentQuestion.get(index))
                }
            }
        }

        DatabaseServices.saveFormResponses(user_id, sex, formResponse)
        DatabaseServices.saveWeights(weightings)

        JSONObject returnPayload = new JSONObject()
        returnPayload.put("true", "Form response saved!")
    }

}
