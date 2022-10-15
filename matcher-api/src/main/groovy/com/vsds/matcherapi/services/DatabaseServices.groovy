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
                        currentUser.getAnswerList() as ArrayList<Integer>, currentUser.getMatchedIds() as Map<String, ArrayList<Integer>>)
        }


        static void saveUserFormResponse(Users currentUser, ArrayList<Integer> formResponse){
                currentUser.setAnswerList(formResponse)
                updateUser(currentUser)
        }
}
