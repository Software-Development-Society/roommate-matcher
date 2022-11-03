package com.vsds.matcherapi.User

import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.database.Questions
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.services.DatabaseServices
import com.vsds.matcherapi.services.UserServices
import org.bson.types.ObjectId

import java.util.regex.Matcher

class MatchUsers {
    /*
    MATCHING ALGORITHM
    30 multiple choice questions - each with five possible answers
    the top is extreme in one direction and the bottom is extreme in the other direction
    the values will be saved as 5 for the top and 1 for the bottom
    the user selects how important this trait is for the other person to have from 1-5
    the persons answer to the question is multiplied by the importance to get a value for each question
    each question is compared to every other question and the total value is the sum of the difference of every question
    the lowest score is the persons best match
     */
    static Map<ObjectId, ArrayList<Integer>> matchAlgo(User currentUser){
        ObjectId user_id = new ObjectId(currentUser.getUserId())

        ArrayList<ArrayList<Integer>> currentUserResponses = DatabaseServices.returnQuestions(user_id)

        for(Questions matchResponses : MatcherApiApplication.visableQuestionRepo){


        }
    }



}
