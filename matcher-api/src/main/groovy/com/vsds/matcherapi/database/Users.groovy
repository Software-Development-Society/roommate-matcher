package com.vsds.matcherapi.database

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
class Users {
    /*
    this class connects our code to the database
    the database contains a BSON object with these characteristics and when you iterate through the repository these objects are returned and the methods
    that are declared here can be used on them
     */
    @Id
    private ObjectId userId
    @Field
    private String firstName
    @Field
    private String lastName
    @Field
    private String email
    @Field
    private String sex
    @Field
    private int classYear
    @Field
    private int age
    @Field
    private String bio
    @Field
    private ArrayList<Integer> answerList
    @Field
    private Map<ObjectId, ArrayList<Integer>> matchedIds



    public Users(){}

    Users(String firstName, String lastName, String email, String sex, int classYear, int age, String bio, ArrayList<Integer> answerList, Map<String, ArrayList<Integer>> matchedIds) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.sex = sex
        this.classYear = classYear
        this.age = age
        this.bio = bio
        this.answerList = answerList
        this.matchedIds = matchedIds
    }

    void setUserId(ObjectId userId) {
        this.userId = userId
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    void setEmail(String email) {
        this.email = email
    }

    void setSex(String sex) {
        this.sex = sex
    }

    void setClassYear(int classYear) {
        this.classYear = classYear
    }

    void setAge(int age) {
        this.age = age
    }

    void setBio(String bio) {
        this.bio = bio
    }

    void setAnswerList(ArrayList<Integer> answerList) {
        this.answerList = answerList
    }

    void setMatchedIds(Map<String, ArrayList<Integer>> matchedIds) {
        this.matchedIds = matchedIds
    }

    ObjectId getUserId() {
        return userId
    }

    String getFirstName() {
        return firstName
    }

    String getLastName() {
        return lastName
    }

    String getEmail() {
        return email
    }

    String getSex() {
        return sex
    }

    int getClassYear() {
        return classYear
    }

    int getAge() {
        return age
    }

    String getBio() {
        return bio
    }

    ArrayList<Integer> getAnswerList() {
        return answerList
    }

    Map<ObjectId, ArrayList<Integer>> getMatchedIds() {
        return matchedIds
    }

    @Override
    public String toString(){
        return String.format("User[id='%s', firstName='%s', lastName='%s', email='%s']", userId, firstName, lastName, email)
    }
}
