package com.vsds.matcherapi.Controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MatcherLoginController {
    @GetMapping
    public String helloWorld(){
        return "hello world"
    }

    @PostMapping("login-handling")
    public String loginHandling(String userId){
        return 'login success'

    }

}
