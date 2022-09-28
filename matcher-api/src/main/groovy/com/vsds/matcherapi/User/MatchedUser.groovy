package com.vsds.matcherapi.User

class MatchedUser {
    UUID userId
    String name
    String email
    String sex
    int classYear
    int age
    Map<String, String> formResponse

    MatchedUser(UUID userId, String name, String email, String sex, int classYear, int age, Map<String, String> formResponse) {
        this.userId = userId
        this.name = name
        this.email = email
        this.sex = sex
        this.classYear = classYear
        this.age = age
        this.formResponse = formResponse
    }

    UUID getUserId() {
        return userId
    }

    String getName() {
        return name
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

    Map<String, String> getFormResponse() {
        return formResponse
    }
}
