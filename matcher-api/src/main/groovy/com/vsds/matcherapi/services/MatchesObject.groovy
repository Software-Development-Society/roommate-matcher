package com.vsds.matcherapi.services

import com.vsds.matcherapi.User.User
import com.vsds.matcherapi.database.MatchList
import org.bson.types.ObjectId

class MatchesObject {
    String rank
    HashMap<String, String> userData
    public MatchesObject(String r, HashMap<String, String> u){
        rank = r
        userData = u
    }

    String getRank() {
        return rank
    }

    void setRank(String rank) {
        this.rank = rank
    }

    HashMap<String, String> getUserData() {
        return userData
    }

    void setUserData(HashMap<String, String> userData) {
        this.userData = userData
    }





    static ArrayList<MatchesObject> getUserInfo(User user, MatchList matchList ){
        ArrayList<MatchesObject> result = new ArrayList<>()
        int count = 1
        for(ObjectId current_id : matchList.getMatchList().keySet()){
            User matchUser = DatabaseServices.getUser(current_id)
            HashMap<String, String> users = new HashMap<>()

            users.put("user_id", matchUser.getUserId())
            users.put("first_name", matchUser.getFirstName())
            users.put("last_name", matchUser.getLastName())
            users.put("user_name", matchUser.getUserName())
            users.put("sex", matchUser.getSex())
            users.put("class_year", matchUser.getClassYear() as String)
            users.put("age", matchUser.getAge() as String)
            users.put("instagram", matchUser.getInstagram())
            users.put("snapchat", matchUser.getSnapChat())
            users.put("bio", matchUser.getBio())
            users.put("score", matchList.getMatchList().get(current_id) as String)
            MatchesObject currentMatch = new MatchesObject(count++ as String, users)
            result.add(currentMatch)
        }
        return result
    }
}
