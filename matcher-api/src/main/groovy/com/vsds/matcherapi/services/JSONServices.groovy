package com.vsds.matcherapi.services

import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.DbUser
import groovy.json.JsonSlurper
import org.json.JSONObject
import org.springframework.data.mongodb.core.convert.JsonSchemaMapper

class JSONServices {
    static JsonSlurper slurper = new JsonSlurper()

    static void parseCurrentUserJSON(String userJSON) {
        def inputString = slurper.parseText(userJSON)
        User.setFirstName(inputString["first_name"] as String)
        User.setLastName(inputString["last_name"] as String)
        User.setEmail(inputString)
    }

    static void updateUser(String update) {
        def inputString = slurper.parseText(update)
        println(inputString.getClass())
    }


}