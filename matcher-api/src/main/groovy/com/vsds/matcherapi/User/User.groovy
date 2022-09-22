package com.vsds.matcherapi.User

class User {
    static UUID userId
    static String name
    static String email
    static String sex
    static int classYear
    static int age
    static Map<String, String> formResponse
    static ArrayList matchList


    static UUID getUserId() {
        return userId
    }

    static String getName() {
        return name
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

    static ArrayList getMatchList() {
        return matchList
    }

    static void setUserId(UUID userId) {
        User.userId = userId
    }

    static void setName(String name) {
        User.name = name
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
