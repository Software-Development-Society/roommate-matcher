const express = require('express');
const router = express.Router();
const axios = require('axios').default;



router.post("/upload-form", (req, res) => {
    //All questions answered will make a 66 entry long json file
    //if there is less than 66 keys we know that not all questions were answered properly
    if(Object.keys(req.body).length < 66){
        //Not all questions were answered
        console.log("Questions unanswered")
    //console.log(req.body)

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