package com.vsds.matcherapi.User

import com.vsds.matcherapi.database.DbUser
import com.vsds.matcherapi.services.DatabaseServices

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
}
