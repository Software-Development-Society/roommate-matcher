
//Checks if every question is answered
//if all questions are answered then the user can submit
function checkIfAllQuestionsAnswered(questionLength){
    
    //console.log(questionLength)

    var formSubmitButton = document.getElementById('userinfo-submit-button')

    var questionsUnanswered = 0

    //temp var to hold the current question DOM
    var currentQuestion;

    for(let x = 0; x < questionLength; x++){
        currentQuestion = document.getElementById('question' + x + 'Row');
        console.log(currentQuestion.classList)
        if(currentQuestion.classList.contains('notAnswered')){
            questionsUnanswered++;
        }
    }

    console.log(questionsUnanswered)

    //Makes button clicakble if all questions are answered
    if(questionsUnanswered == 0){
        formSubmitButton.disabled = false
    }

}

//Passing the questions length as an argument because it wouldn't load when i required it in the file
//Passing the length as a string because it didn't like when I used EJS alone
function updateQuestionClassList(id, questionLengthStr){

    //turns the string into an int 
    let questionLength = parseInt(questionLengthStr)

    //console.log("UPDATED QUESTION CLASS LIST FOR " + id)
    let question = document.getElementById(id)

    //makes sure all the questions are answered
    if (question.classList.contains('notAnswered')){
        question.classList.remove('notAnswered')
        question.classList.add('answered')
    }

    checkIfAllQuestionsAnswered(questionLength)
}

