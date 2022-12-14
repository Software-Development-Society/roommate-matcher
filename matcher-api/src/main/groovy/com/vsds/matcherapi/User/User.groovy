package com.vsds.matcherapi.User

import org.bson.types.ObjectId
import org.json.JSONObject

class User {
    private String userId

    private String firstName

    private String lastName

    private String userName

    private String sex

    private int classYear

    private int age

    private String instagram

    private String snapChat

    private String bio

    User(String userId, String firstName, String lastName, String userName, String sex, int classYear, int age, String instagram, String snapChat, String bio) {
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
        this.userName = userName
        this.sex = sex
        this.classYear = classYear
        this.age = age
        this.instagram = instagram
        this.snapChat = snapChat
        this.bio = bio
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

    String getUserName() {
        return userName
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


    void setUserId(String userId) {
        this.userId = userId
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    void setUserName(String userName) {
        this.userName = email
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

    String getInstagram() {
        return instagram
    }

    String getSnapChat() {
        return snapChat
    }

    void setInstagram(String instagram) {
        this.instagram = instagram
    }

    void setSnapChat(String snapChat) {
        this.snapChat = snapChat
    }

    JSONObject returnUser(){
        JSONObject returnPayload = new JSONObject()
        returnPayload.put("first_name", getFirstName())
        returnPayload.put("last_name", getLastName())
        returnPayload.put("email", getUserName())
        returnPayload.put("sex", getSex())
        returnPayload.put("age", getAge() as String)
        returnPayload.put("class_year", getClassYear() as String)
        returnPayload.put("bio", getBio())
        returnPayload.put("instagram", getInstagram())
        returnPayload.put("snap_chat", getSnapChat())
        return returnPayload
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", classYear=" + classYear +
                ", age=" + age +
                ", instagram='" + instagram + '\'' +
                ", snapChat='" + snapChat + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
