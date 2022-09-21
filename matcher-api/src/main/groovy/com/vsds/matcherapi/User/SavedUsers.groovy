package com.vsds.matcherapi.User

class SavedUsers {
    static User getJDoe(){
        UUID userId = UUID.fromString("10123123")
        String name = "John Doe"
        String email = "jdoe@gmail.com"
        String sex = "male"
        int classYear = 2024
        int age = 19

        User jdoe = new User(userId, name, email, sex, classYear, age)
        return jdoe
    }

}
