package com.vsds.matcherapi.services

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;


class DatabaseServices {


    static void main(String[] args) {
//        MongoClient client = MongoClients.create("mongodb+srv://vsds:2TrmWIOuqVQcKvWU@vsds.nio2wr0.mongodb.net/?retryWrites=true&w=majority")
//        MongoDatabase database = client.getDatabase("roommateMatcher")
//        MongoCollection<Document> toys = database.getCollection("user")
//        DBObject test = new BasicDBObject("name", "Holden")
//        toys.insertOne(test)


        ConnectionString connectionString = new ConnectionString("mongodb+srv://vsds:2TrmWIOuqVQcKvWU@vsds.nio2wr0.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings)
        MongoDatabase database = mongoClient.getDatabase("roommateMatcher")
        MongoCollection<Document> toys = database.getCollection("user")
        Document testDoc = new Document("_id", new ObjectId())


    }
}
