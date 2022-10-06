package com.vsds.matcherapi.services


import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.Users

class DatabaseServices {
        static Users getUserFromId(String userId){
                for (Users dbUser: MatcherApiApplication.visableRepo.findAll()){
                        if (dbUser.getUserId() != null){
                                if (dbUser.getUserId() == userId){
                                        return dbUser
                                }
                        }
                }
                if (User.getName() == null) {
                        print "ISSUE WITH GETTING USER"
                }
        }

        static void updateUser(Users user){
                MatcherApiApplication.visableRepo.save(user)
        }



        static User getUser(String userId){
                Users currentUser = getUserFromId(userId)
                println(currentUser.getMatchedIds() as Map<String, Integer>)
                return new User(currentUser.getUserId() as String, currentUser.getFirstName() as String, currentUser.getLastName() as String,
                        currentUser.getEmail() as String, currentUser.getSex() as String, currentUser.classYear as int, currentUser.age as int, currentUser.getBio() as String,
                        currentUser.getAnswerList() as ArrayList<Integer>, currentUser.getMatchedIds() as Map<String, Integer>)
        }

        //TO DO
        //how can you get the most recent version of the db?
        static void loadDataBase(){

        }

        static void updateUser(Map<String, String> updateMap){
                String userUpdate = updateMap.keySet()[1]
                if(userUpdate.equals("first_name")){
                        print("firstname")
                } else if (userUpdate.equals("last_name")){
                        print("lastname")
                } else if (userUpdate.equals("email")){
                        print("email")
                }else if (userUpdate.equals("sex")){
                        print("sex")
                }else if (userUpdate.equals("age")){
                        print("age")
                }else if (userUpdate.equals("bio")){
                        print("bio")
                }else if (userUpdate.equals("class_year")){
                        print("class Year")
                }else if (userUpdate.equals("user_id")){
                        print("userId")
                }
        }
}
