package com.vsds.matcherapi.services

import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.Users
import org.bson.types.ObjectId

class UserServices {

    static void updateUserFullJson(Map<String, String> updateInformation, User user){
        println(user.toString())
        println()
        for(String key : updateInformation.keySet()){
            if (key == "first_name"){
                user.setFirstName(updateInformation.get(key))
            }
            else if (key == "last_name"){
                user.setLastName(updateInformation.get(key))
            }
            else if (key == "email"){
                user.setUserName(updateInformation.get(key))
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
        }
        Users updatedUser = usersFromUser(user)
        ObjectId user_id = new ObjectId(user.getUserId())
        updatedUser.setUserId(user_id)
        DatabaseServices.updateUser(updatedUser)
    }
    static Users usersFromUser(User user){
        return new Users(user.getFirstName(), user.getLastName(), user.getUserName(), user.getSex(), user.getClassYear(), user.getAge(), user.getBio())
    }



}
