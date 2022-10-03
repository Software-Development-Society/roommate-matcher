package com.vsds.matcherapi.database

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
class DbUser {
    @Id
    private String userId
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
    private Map<String, ArrayList<Integer>> matchedIds


    public DbUser(){}

    DbUser(String firstName, String lastName, String email, String sex, int classYear, int age, String bio, ArrayList<Integer> answerList, Map<String, ArrayList<Integer>> matchedIds) {
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

    void setUserId(String userId) {
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

    String getUserId() {
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

    Map<String, ArrayList<Integer>> getMatchedIds() {
        return matchedIds
    }

    @Override
    public String toString(){
        return String.format("User[id='%s', firstName='%s', lastName='%s']", userId, firstName, lastName)
    }
}
