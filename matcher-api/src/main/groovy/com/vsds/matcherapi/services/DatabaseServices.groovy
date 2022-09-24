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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


class DatabaseServices {

        static void getUserFromId(String userId){
                for (DbUser dbUser: MatcherApiApplication.visableRepo.findAll()){
                        if (dbUser.getUserId() != null){
                                if (dbUser.getUserId() == userId){
                                        // issues when trying to save user
                                        User.setUserId(dbUser.getUserId())
                                        User.setFirstName(dbUser.getFirstName())
                                        User.setLastName(dbUser.getLastName())
                                        User.setSex(dbUser.getSex())
                                        User.setClassYear(dbUser.getClassYear())
                                        User.setAge(dbUser.getAge())
                                        User.setBio(dbUser.getBio())
                                }
                        }
                }
                if (User.getName() == null) {
                        print "ISSUE WITH GETTING USER"
                }
                else{
                        print "user successfully created" + User.toString()
                }
        }

}
