const express = require('express');
const router = express.Router();
const axios = require('axios').default;

const questions = require('../Questions.json')

router.post("/unansweredQuestions-form", (req, res)=>{
    console.log(req.body)
})


router.post("/upload-form", (req, res) => {
    //console.log(questions.length)

    let keys = Object.keys(req.body)
    //All questions answered will make a 66 entry long json file
    //if there is less than 66 keys we know that not all questions were answered properly
    
    if((keys.length / 3) < questions.length){
        //Not all questions were answered
        
        let arrOfUnansweredQuestionIndex = getUnansweredQuestions(keys)

        //console.log(questions)
        res.render('../views/form/unansweredQuestionsForm', {styleInput: "homepage", isLoggedIn: req.isAuthenticated(), questions: questions, unansweredQuestions: arrOfUnansweredQuestionIndex, previousAnswers : makeFormJsonToSendToBackend(req)});

    }else{
        //formats the JSON
        let data = makeFormJsonToSendToBackend(req)

        //Makes its JSON
        let json = JSON.stringify(data)

        axios({
            method: 'post',
            headers: {
                "Content-Type": "application/json",
            },
            url: 'http://localhost:8080/save-responses',
            data: json
        }).then(response => {
        console.log(response);
        //res.send(response);
        }).catch(error => {
        console.log(error);
        //res.send(error)
    });

        res.redirect('/dashboard')
    }
    
})

//takes in the keys of the data received from the form and returns an array of the question number that was unanswered
function getUnansweredQuestions(keys){
    var questionsUnanswered = []
        

        var keyBefore = ""
        keys.forEach(key => {

            //use the first letter to distinguish what each key is
            //ex: r = roommateQuestion , n = numOfQuestionAnswersForQuestion. and q = question


            //gets rid of the roommatequestion keys
            if(key.charAt(0) == 'r'){
                keyBefore = key
                return
            }

            //first edge case: if question 1's answer is missing
            if((keyBefore == "" && key.charAt(0) == 'n') || (keyBefore.charAt(0) == 'r' && key.charAt(0) == 'n')){
                questionsUnanswered.push(parseInt(key.substring(31)))
                return
            }

            keyBefore = key
            
        });

    return questionsUnanswered
}


//method takes the request and creates JSON to send to the backend
function makeFormJsonToSendToBackend(req){
    var json = {
        user_id: req.user.id,
        sex: req.user.sex,
        responses: []
    }
        let keys = Object.keys(req.body)
        console.log(keys.length)

        //looping through the json received from form.ejs and assigning it to vars to make more readable
        for(var x = 0; x < keys.length; x+=3){
            let answer = req.body[keys[x]]
            let numOfAnswers = req.body[keys[x + 1]]
            let roommateAnswer = req.body[keys[x+2]]

            json["responses"].push([getQuestionValue(answer,numOfAnswers), getRoommateValue(roommateAnswer)])
        }

    return json
        //console.log(json)
}

//Below Methods here to convert the values from the form to the values we are using in the backend
//Ex: if there are only two possible answers, then the form will give the node js becakend either 1 or 2
//but we want it to be either 1 or 5
//I know these are hard coded and all but they are good enough
function getQuestionValue(answer, numOfAnswers){
    if(numOfAnswers == '2' && answer == '2'){
        answer = '5'
    }else if (numOfAnswers == '3'){
        if(answer == '2'){
            answer = '3'
        }else if(answer == '3'){
            answer = '5'
        }
    }
    return answer
}

//I know these are hard coded and all but they are good enough
function getRoommateValue(roommateAnswer){
    if(roommateAnswer == '3'){
        roommateAnswer = '4'
    }else if(roommateAnswer == '4'){
        roommateAnswer = '5'
    }

    return roommateAnswer
}



module.exports = router