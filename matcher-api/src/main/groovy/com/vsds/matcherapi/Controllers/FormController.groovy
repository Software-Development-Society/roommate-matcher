package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.services.DatabaseServices
import groovy.json.JsonSlurper
import org.bson.types.ObjectId
import org.json.JSONObject
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FormController {

    JsonSlurper slurper = new JsonSlurper()


    /*
    Input -> {"user_id" : "24t324t5", "responses" : [[4,1],[5,3]...]}
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
        DatabaseServices.saveFormResponses(user_id,sex, userResponses)
        JSONObject returnPayload = new JSONObject()
        returnPayload.put("true", "Form response saved!")
    }

}
