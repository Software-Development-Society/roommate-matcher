package com.vsds.matcherapi.User

import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.services.UserServices
import org.bson.types.ObjectId

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
        Map<ObjectId, ArrayList<Integer>> matchedIds = new HashMap<>()
        for (Users userToMatch : MatcherApiApplication.visableRepo.findAll()){
            ArrayList<Integer> currentUserResp = currentUser.getAnswerList()
            ArrayList<Integer> userToMatchResp = userToMatch.getAnswerList()

            int personalityTotal = 0
            int habitsTotal = 0
            int miscTotal = 0
            if (userToMatch.getUserId() != currentUser.getUserId() && UserServices.validateCorrectUser(userToMatch)){
                for (int answerIndex = 0; answerIndex < currentUserResp.size()-1; answerIndex+=2){
                    int currentUserAns = currentUserResp.get(answerIndex) * currentUserResp.get(answerIndex + 1)
                    int matchUserAns = userToMatchResp.get(answerIndex) * userToMatchResp.get(answerIndex + 1)
                    int ansScore = Math.abs(currentUserAns-matchUserAns)
                    if (answerIndex < 10){
                        personalityTotal += ansScore
                    }
                    else if (answerIndex < 20){
                        habitsTotal+= ansScore
                    }
                    else{
                        miscTotal+= ansScore
                    }
                }
                ArrayList<Integer> respScore = new ArrayList<>()
                respScore.addAll(personalityTotal, habitsTotal, miscTotal)
                matchedIds.put(userToMatch.getUserId(), respScore)
            }
        }
        return matchedIds
    }



}
