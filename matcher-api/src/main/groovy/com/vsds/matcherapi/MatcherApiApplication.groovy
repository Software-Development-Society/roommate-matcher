package com.vsds.matcherapi


import com.vsds.matcherapi.database.Users
import com.vsds.matcherapi.database.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MatcherApiApplication implements CommandLineRunner {
    public UserRepository userRepository
    public static UserRepository visableRepo

    @Autowired
    AppApplication(UserRepository userRepository){
        this.userRepository = userRepository
    }

    static void main(String[] args) {
        SpringApplication.run(MatcherApiApplication, args)
    }

    @Override
    void run(String... args) throws Exception {
        visableRepo = userRepository

        for (Users dbUser: userRepository.findAll()){
            print dbUser
        }

    }
}
