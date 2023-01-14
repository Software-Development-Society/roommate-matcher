package com.vsds.matcherapi.User

import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.database.Questions
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.services.DatabaseServices
import com.vsds.matcherapi.services.UserServices
import org.bson.types.ObjectId

import javax.swing.text.MutableAttributeSet
import javax.xml.crypto.Data
import java.lang.reflect.Array
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

    static HashMap<ObjectId, Integer> createMatches(ObjectId user_id){
        Users userToMatch = DatabaseServices.getUserFromId(user_id)
        Questions userToMatchResponse = DatabaseServices.returnQuestions(user_id)
        HashMap<ObjectId, Integer> matches = new HashMap<>()

        for(Questions currentResponse : MatcherApiApplication.visableQuestionRepo){
            if(user_id == currentResponse.getUser_id() || userToMatch.getSex() != currentResponse.getSex()){
                continue
            }
            for(int questionNum = 0; questionNum < 30; questionNum++){
                int userQuestion1 = userToMatchResponse.getResponses().get(questionNum).get(0)
                int userQuestion2 = userToMatchResponse.getResponses().get(questionNum).get(1)

                int matchQuestion1 = userToMatchResponse.getResponses().get(questionNum).get(0)
                int matchQuestion2 = userToMatchResponse.getResponses().get(questionNum).get(1)

                int xscore = Math.abs(userQuestion1-matchQuestion1)
                int yscore = userQuestion2 + matchQuestion2



            }
        }


    }



}
