package com.vsds.matcherapi.database

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "users")
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
    private String username
    @Field
    private String sex
    @Field
    private int classYear
    @Field
    private int age
    @Field
    private String bio
    @Field
    private String instagram
    @Field
    private String snapChat



    public Users(){}

    Users(String firstName, String lastName, String username, String sex, int classYear, int age, String bio, String instagram, String snapChat) {
        this.firstName = firstName
        this.lastName = lastName
        this.username = username
        this.sex = sex
        this.classYear = classYear
        this.age = age
        this.bio = bio
        this.instagram = instagram
        this.snapChat = snapChat
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

    String getUsername() {
        return username
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

    void setUserId(ObjectId userId) {
        this.userId = userId
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    void setUsername(String username) {
        this.username = username
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


    @Override
    public String toString() {
        return "{userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", classYear=" + classYear +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                ", instagram='" + instagram + '\'' +
                ", snapChat='" + snapChat + '}\''
    }
}
