package com.vsds.matcherapi.Controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MatchUsersController {


    @GetMapping("/match-users")
    String matchUsers(){

    }
}
