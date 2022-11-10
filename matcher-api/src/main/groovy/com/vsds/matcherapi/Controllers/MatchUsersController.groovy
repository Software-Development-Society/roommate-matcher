package com.vsds.matcherapi.Controllers

import com.vsds.matcherapi.User.MatchUsers
import org.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MatchUsersController {


    @GetMapping("/match-users")
    String matchUsers(){
        MatchUsers.matchAllUsers()
        JSONObject returnPayload = new JSONObject()
        returnPayload.put("status", "true")
        return returnPayload as String
    }
}
