package com.vsds.matcherapi.services


import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.Users
import org.bson.types.ObjectId

class DatabaseServices {
        static Users getUserFromId(ObjectId userId){
                for (Users dbUser: MatcherApiApplication.visableRepo.findAll()){
                        println("User gotten from repo :"+ dbUser.toString())
                        if (dbUser.getUserId() != null){
                                if (dbUser.getUserId() == userId){
                                        println(dbUser.toString())
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



        static User getUser(ObjectId userId){
                Users currentUser = getUserFromId(userId)
                println("Current user" + currentUser)
                return new User(currentUser.getUserId() as String, currentUser.getFirstName() as String, currentUser.getLastName() as String,
                        currentUser.getEmail() as String, currentUser.getSex() as String, currentUser.classYear as int, currentUser.age as int, currentUser.getBio() as String)
        }


        static void saveUserFormResponse(Users currentUser, ArrayList<Integer> formResponse){
                currentUser.setAnswerList(formResponse)
                updateUser(currentUser)
        }
}
