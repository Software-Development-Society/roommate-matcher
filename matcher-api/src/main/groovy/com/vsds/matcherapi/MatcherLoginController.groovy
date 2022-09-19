package com.vsds.matcherapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MatcherLoginController {
    @GetMapping
    public String helloWorld(){
        return "hello world"
    }

}
