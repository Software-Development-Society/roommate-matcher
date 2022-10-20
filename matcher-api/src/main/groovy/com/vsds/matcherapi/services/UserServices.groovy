package com.vsds.matcherapi.services

import com.vsds.matcherapi.database.Users

class UserServices {
    static boolean validateCorrectUser(Users user){
        try{
            String userId = user.getUserId()
            String firstName = user.getFirstName()
            String lastName = user.getLastName()
            String email = user.getEmail()
            String sex = user.getSex()
            int age = user.getAge()
            int classYear = user.getClassYear()
            String bio = user.getBio()
            ArrayList<Integer> responses = user.getAnswerList()
            Map<String, ArrayList<Integer>> matchedIds = user.getMatchedIds()
        }
        catch (Exception e){
            println("ERROR IN FINDING USER: " +e)
            return false
        }
        return true
    }

}
