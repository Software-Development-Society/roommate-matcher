package com.vsds.matcherapi.User

import org.bson.types.ObjectId

class MatchesObject {
    ObjectId user_id
    String firstName
    String lastName
    String instagram
    String snapchat
    String bio
    String email

    MatchesObject(ObjectId user_id, String firstName, String lastName, String instagram, String snapchat, String bio, String email) {
        this.user_id = user_id
        this.firstName = firstName
        this.lastName = lastName
        this.instagram = instagram
        this.snapchat = snapchat
        this.bio = bio
        this.email = email
    }

    ObjectId getUser_id() {
        return user_id
    }

    String getFirstName() {
        return firstName
    }

    String getLastName() {
        return lastName
    }

    String getInstagram() {
        return instagram
    }

    String getSnapchat() {
        return snapchat
    }

    String getBio() {
        return bio
    }

    String getEmail() {
        return email
    }

    void setUser_id(ObjectId user_id) {
        this.user_id = user_id
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    void setInstagram(String instagram) {
        this.instagram = instagram
    }

    void setSnapchat(String snapchat) {
        this.snapchat = snapchat
    }

    void setBio(String bio) {
        this.bio = bio
    }

    void setEmail(String email) {
        this.email = email
    }


    @Override
    public String toString() {
        return "MatchesObject{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", instagram='" + instagram + '\'' +
                ", snapchat='" + snapchat + '\'' +
                ", bio='" + bio + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
