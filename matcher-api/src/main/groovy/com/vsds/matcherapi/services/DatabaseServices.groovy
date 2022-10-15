package com.vsds.matcherapi.services

import com.fasterxml.jackson.databind.deser.DataFormatReaders
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*
import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.DbUser
import com.vsds.matcherapi.database.UserRepository;
import org.bson.Document;
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


class DatabaseServices {
        static DbUser getUserFromId(String userId){
                for (DbUser dbUser: MatcherApiApplication.visableRepo.findAll()){
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

        static void updateUser(DbUser user){
                MatcherApiApplication.visableRepo.save(user)
        }



        static User getUser(String userId){
                DbUser currentUser = getUserFromId(userId)
                println(currentUser.getMatchedIds() as Map<String, Integer>)
                return new User(currentUser.getUserId() as String, currentUser.getFirstName() as String, currentUser.getLastName() as String,
                        currentUser.getEmail() as String, currentUser.getSex() as String, currentUser.classYear as int, currentUser.age as int, currentUser.getBio() as String,
                        currentUser.getAnswerList() as ArrayList<Integer>, currentUser.getMatchedIds() as Map<String, ArrayList<Integer>>)
        }


        static void saveUserFormResponse(DbUser currentUser, ArrayList<Integer> formResponse){
                currentUser.setAnswerList(formResponse)
                updateUser(currentUser)
        }
}
