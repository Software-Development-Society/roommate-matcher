package com.vsds.matcherapi.User

import com.vsds.matcherapi.database.DbUser
import com.vsds.matcherapi.services.DatabaseServices
import org.json.JSONObject

class User {

    static DbUser user
    static void loadCurrentUser(String userId){
        user = DatabaseServices.getUserFromId(userId)
    }
    static String getUserId(){
        return user.getUserId()
    }

    static String getFirstName(){
        return user.getFirstName()
    }

    static String getLastName(){
        return user.getLastName()
    }

    static String getEmail(){
        return user.getEmail()
    }

    static String getSex(){
        return user.getSex()
    }

    static int getClassYear(){
        return user.getClassYear()
    }

    static int getAge(){
        return user.getAge()
    }

    static String getBio(){
        user.getBio()
    }
    static DbUser getUser(){
        return user
    }
    static void setFirstName(String name){
        user.setFirstName(name)
    }
    static void setLastName(String name){
        user.setLastName(name)
    }
    static void setEmail(String email){
        user.setEmail(email)
    }
    static void setSex(String sex){
        user.setSex(sex)
    }
    static void setAge(int age){
        user.setAge(age)
    }
    static void setClassYear(int classYear){
        user.setClassYear(classYear)
    }
    static void setBio(String bio){
        user.setBio(bio)
    }





    static void updateDatabase(){
        DatabaseServices.updateUser(user)

    }


    static JSONObject returnUser(){
        JSONObject returnPayload = new JSONObject()
        returnPayload.put("first_name", getFirstName())
        returnPayload.put("last_name", getLastName())
        returnPayload.put("email", getEmail())
        returnPayload.put("sex", getSex())
        returnPayload.put("age", getAge() as String)
        returnPayload.put("class_year", getClassYear() as String)
        returnPayload.put("bio", getBio())
        return returnPayload
    }
}
