package com.vsds.matcherapi.User


import org.json.JSONObject

class User {
    private String userId

    private String firstName

    private String lastName

    private String email

    private String sex

    private int classYear

    private int age

    private String bio

    private ArrayList<Integer> answerList

    private Map<String, Integer> matchedId

    User(String userId, String firstName, String lastName, String email, String sex, int classYear, int age, String bio, ArrayList<Integer> answerList, Map<String, Integer> matchedId) {
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.sex = sex
        this.classYear = classYear
        this.age = age
        this.bio = bio
        this.answerList = answerList
        this.matchedId = matchedId
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

    Map<String, Integer> getMatchedId() {
        return matchedId
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

    void setMatchedId(Map<String, Integer> matchedId) {
        this.matchedId = matchedId
    }


    JSONObject returnUser(){
        JSONObject returnPayload = new JSONObject()
        returnPayload.put("first_name", getFirstName())
        returnPayload.put("last_name", getLastName())
        returnPayload.put("email", getEmail())
        returnPayload.put("sex", getSex())
        returnPayload.put("age", getAge() as String)
        returnPayload.put("class_year", getClassYear() as String)
        returnPayload.put("bio", getBio())
        returnPayload.put("answer_list", getAnswerList())
        returnPayload.put("matched_ids", getMatchedId())
        return returnPayload
    }
}
