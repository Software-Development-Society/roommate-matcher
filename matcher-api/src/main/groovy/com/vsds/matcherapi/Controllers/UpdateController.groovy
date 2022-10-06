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
class UpdateController {
    JsonSlurper slurper = new JsonSlurper()
    @PostMapping("/updateUser")
    String updateUser(@RequestBody String userChange){
        Map<String, String> inputString = slurper.parseText(userChange) as Map<String, String>

        DatabaseServices.updateUser(inputString)
        return "works"
    }

}
