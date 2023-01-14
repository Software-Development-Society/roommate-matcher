package com.vsds.matcherapi.User

import com.vsds.matcherapi.MatcherApiApplication
import com.vsds.matcherapi.database.MatchList
import com.vsds.matcherapi.database.Questions
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.services.DatabaseServices
import com.vsds.matcherapi.services.UserServices
import org.bson.types.ObjectId
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration
import org.springframework.data.mongodb.core.aggregation.ArrayOperators

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


    static void matchUsers(){
        ArrayList<Questions> users = DatabaseServices.getUsersResponses()
        for (Questions currentUser : users){
            ObjectId user_id = currentUser.getUser_id()
            HashMap<ObjectId, Integer> matches = createMatches(user_id)
            MatchList userMatches = new MatchList(user_id, matches)
            DatabaseServices.saveMatches(userMatches)
        }

    }




    static HashMap<ObjectId, Integer> createMatches(ObjectId user_id){
        Users userToMatch = DatabaseServices.getUserFromId(user_id)
        Questions userToMatchResponse = DatabaseServices.returnQuestions(user_id)
        HashMap<ObjectId, Integer> matches = new HashMap<>()

        for(Questions currentResponse : MatcherApiApplication.visableQuestionRepo.findAll()){
            // each user total score
            if(user_id == currentResponse.getUser_id() || userToMatch.getSex() != currentResponse.getSex()){
                continue
            }
            Integer scoresSum = 0
            for(int questionNum = 0; questionNum < 30; questionNum++){
                // each question score
                int userQuestion1 = userToMatchResponse.getResponses().get(questionNum).get(0)
                int userQuestion2 = currentResponse.getResponses().get(questionNum).get(1)

                int matchQuestion1 = userToMatchResponse.getResponses().get(questionNum).get(0)
                int matchQuestion2 = currentResponse.getResponses().get(questionNum).get(1)

                String xscore = Math.abs(userQuestion1-matchQuestion1) as String
                String yscore = (userQuestion2 + matchQuestion2) as String

               HashMap<String, Integer> scoresMap = populatingScores()

                String key = xscore + yscore
                int score = scoresMap.get(key)


                //weighting
                ArrayList<Integer> weights = getWeights()
                int matchScoreForThisQuestion = weights.get(questionNum) * score
                scoresSum = matchScoreForThisQuestion
            }
            matches.put(currentResponse.getUser_id(), scoresSum)
            println(scoresSum)
        }

        // could have issues here
       HashMap<ObjectId, Integer> result = matches.sort { a, b -> a.value <=> b.value} as HashMap<ObjectId, Integer>
        return result
    }

    static HashMap<String, Integer> populatingScores(){
         Map<String, Integer> scoresMap = new HashMap<>()

        // populating scores map
        scoresMap.put("010", 1)
        scoresMap.put("09", 2)
        scoresMap.put("08", 3)
        scoresMap.put("07", 4)
        scoresMap.put("06", 5)
        scoresMap.put("05", 6)
        scoresMap.put("04", 7)
        scoresMap.put("03", 8)
        scoresMap.put("02", 9)

        scoresMap.put("210", 14)
        scoresMap.put("29", 15)
        scoresMap.put("28", 10)
        scoresMap.put("27", 11)
        scoresMap.put("26", 12)
        scoresMap.put("25", 13)
        scoresMap.put("24", 16)
        scoresMap.put("23", 17)
        scoresMap.put("22", 18)

        scoresMap.put("310", 31)
        scoresMap.put("39", 30)
        scoresMap.put("38", 29)
        scoresMap.put("37", 28)
        scoresMap.put("36", 27)
        scoresMap.put("35", 25)
        scoresMap.put("34", 23)
        scoresMap.put("33", 21)
        scoresMap.put("32", 19)


        scoresMap.put("510", 36)
        scoresMap.put("59", 35)
        scoresMap.put("58", 34)
        scoresMap.put("57", 33)
        scoresMap.put("56", 32)
        scoresMap.put("55", 26)
        scoresMap.put("54", 24)
        scoresMap.put("53", 22)
        scoresMap.put("52", 20)

        scoresMap.put("610", 51)
        scoresMap.put("69", 50)
        scoresMap.put("68", 49)
        scoresMap.put("67",48)
        scoresMap.put("66", 47)
        scoresMap.put("65", 44)
        scoresMap.put("64", 40)
        scoresMap.put("63", 38)
        scoresMap.put("62", 37)

        scoresMap.put("810", 60)
        scoresMap.put("89", 59)
        scoresMap.put("88", 58)
        scoresMap.put("87", 55)
        scoresMap.put("86", 54)
        scoresMap.put("85", 52)
        scoresMap.put("84", 45)
        scoresMap.put("83", 42)
        scoresMap.put("82", 39)

        scoresMap.put("1010", 63)
        scoresMap.put("109", 62)
        scoresMap.put("108", 61)
        scoresMap.put("107", 57)
        scoresMap.put("106", 56)
        scoresMap.put("105", 53)
        scoresMap.put("104", 46)
        scoresMap.put("103", 43)
        scoresMap.put("102", 41)

        return scoresMap
    }

    static ArrayList<Integer> getWeights(){
        ArrayList<Integer> weights = new ArrayList<>()
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)

        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)

        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)
        weights.add(1)

        return weights


    }



}
