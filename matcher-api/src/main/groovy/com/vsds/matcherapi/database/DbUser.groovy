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

    public DbUser(){}

    DbUser(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
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

    @Override
    public String toString(){
        return String.format("User[id='%s', firstName='%s', lastName='%s']", userId, firstName, lastName)
    }
}
