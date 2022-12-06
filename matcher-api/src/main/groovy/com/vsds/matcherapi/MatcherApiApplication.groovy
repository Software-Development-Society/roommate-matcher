package com.vsds.matcherapi

import com.vsds.matcherapi.database.MatchList
import com.vsds.matcherapi.database.MatchListRepository
import com.vsds.matcherapi.database.Questions
import com.vsds.matcherapi.database.QuestionsRepository
import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.database.UserRepository
import com.vsds.matcherapi.database.WeightsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
/*
this is how the application runs
DO NOT MODIFY THIS CODE!!!!
 */

@SpringBootApplication
class MatcherApiApplication implements CommandLineRunner {
    public UserRepository userRepository
    public static UserRepository visableUserRepo
    public QuestionsRepository questionsRepository
    public static visableQuestionRepo
    public MatchListRepository matchListRepository
    public static MatchListRepository visableMatchListRepo
    public static WeightsRepository visableWeightsRepo
    public WeightsRepository weightsRepository

    @Autowired
    AppApplication(UserRepository userRepository, QuestionsRepository questionsRepository, MatchListRepository matchListRepository,WeightsRepository weightsRepository){
        this.userRepository = userRepository
        this.questionsRepository = questionsRepository
        this.matchListRepository = matchListRepository
        this.weightsRepository = weightsRepository
    }

    static void main(String[] args) {
        SpringApplication.run(MatcherApiApplication, args)
    }

    @Override
    void run(String... args) throws Exception {
        visableUserRepo = userRepository
        visableQuestionRepo = questionsRepository
        visableMatchListRepo = matchListRepository
        visableWeightsRepo = weightsRepository

        for(Questions question : questionsRepository.findAll()){
            print question
        }
        println()

        for (Users dbUser: userRepository.findAll()){
            print dbUser
        }
        println()

        for(MatchList matchList : matchListRepository.findAll()){
            print matchList
        }
        println()
    }
}
