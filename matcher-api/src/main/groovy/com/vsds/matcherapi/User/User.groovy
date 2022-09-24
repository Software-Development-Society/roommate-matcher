package com.vsds.matcherapi.User

class User {
    static String userId
    static String firstName
    static String lastName
    static String email
    static String sex
    static int classYear
    static int age
    static String bio;
    static Map<String, String> formResponse
    static ArrayList matchList
    public User(){}


    static String getUserId() {
        return userId
    }

    static String getFirstName() {
        return firstName
    }

    static String getLastName() {
        return lastName
    }

    static String getEmail() {
        return email
    }

    static String getSex() {
        return sex
    }

    static int getClassYear() {
        return classYear
    }

    static int getAge() {
        return age
    }

    static Map<String, String> getFormResponse() {
        return formResponse
    }

    static void setFormResponse(Map<String, String> formResponse) {
        this.formResponse = formResponse
    }

    static void setBio(String bio) {
        User.bio = bio
    }

    static String getBio() {
        return bio
    }

    static ArrayList getMatchList() {
        return matchList
    }

    static void setUserId(String userId) {
        User.userId = userId
    }

    static void setFirstName(String name) {
        User.firstName = name
    }

    static void setLastName(String lastName) {
        User.lastName = lastName
    }

    static void setEmail(String email) {
        User.email = email
    }

    static void setSex(String sex) {
        User.sex = sex
    }

    static void setClassYear(int classYear) {
        User.classYear = classYear
    }

    static void setAge(int age) {
        User.age = age
    }

    static void setMatchList(ArrayList matchList) {
        User.matchList = matchList
    }

}
