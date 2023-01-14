package com.vsds.matcherapi.services


import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.Questions
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.database.Weights
import org.bson.types.ObjectId

import java.lang.reflect.Array
import java.nio.file.WatchEvent

class DatabaseServices {
        /*
        input -> user_id
        method gets a user from the database by matching the current user_id to one present within the database
        return -> the user associated with that user_id
        */
        static Users getUserFromId(ObjectId userId){
                for (Users dbUser: MatcherApiApplication.visableUserRepo.findAll()){
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
        
        /*
        input -> current user you want to save in the user db
        */
        static void updateUser(Users user){
                MatcherApiApplication.visableUserRepo.save(user)
        }


        /*
        input -> user_id
        method creates a user object from a valid user_id
        returns -> current User object
        */
        static User getUser(ObjectId userId){
                Users currentUser = getUserFromId(userId)
                println("Current user" + currentUser)
                return new User(currentUser.getUserId() as String, currentUser.getFirstName() as String, currentUser.getLastName() as String,
                        currentUser.getUsername() as String, currentUser.getSex() as String, currentUser.classYear as int, currentUser.age as int, currentUser.getBio() as String, currentUser.getInstagram() as String, currentUser.getSnapChat() as String)
        }
        
        
        

        
        static void saveFormResponses(ObjectId user_id, String sex, ArrayList<ArrayList<Integer>> formResponses) {
                Questions questionList = new Questions(user_id,sex, formResponses)
                MatcherApiApplication.visableQuestionRepo.save(questionList)

        }


        static Questions returnQuestions(ObjectId user_id){
                for(Questions question : MatcherApiApplication.visableQuestionRepo){
                        if(question.getUser_id() == user_id){
                                return question
                        }
                }
        }


        static ArrayList<Integer> getWeights(){
                return MatcherApiApplication.visableWeightsRepo.findAll()
        }

        static void saveWeights(ArrayList<Integer> weights){
                ArrayList<Integer> updateWeights = getWeights()
                for(int index = 0; index < updateWeights.size(); index++){
                        updateWeights.set(index, updateWeights.get(index) + weights.get(index))
                }
                Weights weight = new Weights(updateWeights)
                MatcherApiApplication.visableWeightsRepo.save(weight)
        }


}
