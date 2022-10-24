package com.vsds.matcherapi.services

import com.vsds.matcherapi.User.User
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


    static void updateUserFullJson(Map<String, String> updateInformation, User user){
        for(String key : updateInformation.keySet()){
            if (key == "first_name"){
                user.setFirstName(updateInformation.get(key))
            }
            else if (key == "last_name"){
                user.setLastName(updateInformation.get(key))
            }
            else if (key == "email"){
                user.setEmail(updateInformation.get(key))
            }
            else if (key == "bio"){
                user.setBio(updateInformation.get(key))
            }
            else if (key == "sex"){
                user.setSex(updateInformation.get(key))
            }
            else if (key == "class_year"){
                user.setClassYear(updateInformation.get(key) as int)
            }
            else if (key == "age"){
                user.setAge(updateInformation.get(key) as int)
            }
            else if (key == "answer_list"){
                user.setAnswerList(updateInformation.get(key) as ArrayList<Integer[]>)
            }
        }

    }



}
