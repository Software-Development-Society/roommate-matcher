package com.vsds.matcherapi.User

class User {
    UUID userId
    String name
    String email
    String sex
    String classYear
    String age
    Map<String, String> formResponse

    User(UUID userId, String name, String email, String sex, String classYear, String age, Map<String, String> formResponse) {
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

    String getClassYear() {
        return classYear
    }

    String getAge() {
        return age
    }

    Map<String, String> getFormResponse() {
        return formResponse
    }
}
