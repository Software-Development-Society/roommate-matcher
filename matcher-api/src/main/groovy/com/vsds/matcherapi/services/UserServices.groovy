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
            else if(key == "instagram") {
                user.setInstagram(updateInformation.get(key))
            }
            else if(key == "snap_chat"){
                user.setSnapChat(updateInformation.get(key))
            }
        }
        Users updatedUser = usersFromUser(user)
        ObjectId user_id = new ObjectId(user.getUserId())
        updatedUser.setUserId(user_id)
        DatabaseServices.updateUser(updatedUser)
    }
    static Users usersFromUser(User user){
        return new Users(user.getFirstName(), user.getLastName(), user.getUserName(), user.getSex(), user.getClassYear(), user.getAge(), user.getBio(), user.getInstagram(), user.getSnapChat())
    }


    static String returnUserInfo(Users user, String value){
        String result = "";
        if(value == "first_name"){
            result = user.getFirstName()
        }
        else if (value == "last_name"){
            result = user.getLastName()
        }
        else if (value == "username"){
            result = user.getUsername()

        }
        else if (value == "sex"){
            result = user.getSex()

        }
        else if (value == "class_year"){
            result = user.getClassYear()

        }
        else if (value == "age"){
            result = user.getAge()

        }
        else if (value == "bio"){
            result = user.getBio()

        }
        else if (value == "instagram"){
            result = user.getInstagram()

        }
        else if (value == "snapchat"){
            result = user.getSnapChat()

        }
        return result
    }



}
