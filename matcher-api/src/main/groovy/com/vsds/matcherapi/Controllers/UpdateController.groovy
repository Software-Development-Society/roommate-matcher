package com.vsds.matcherapi.Controllers


import com.vsds.matcherapi.services.DatabaseServices
import groovy.json.JsonSlurper
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

class UpdateController {
    JsonSlurper slurper = new JsonSlurper()
    @PostMapping("/updateUser")
    String updateUser(@RequestBody String userChange){
        Map<String, String> inputString = slurper.parseText(userChange) as Map<String, String>

        DatabaseServices.updateUser(inputString)
        return "works"
    }

}
