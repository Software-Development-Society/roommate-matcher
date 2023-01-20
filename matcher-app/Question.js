class Question{
    constructor(question, answers){
        this.question = question //String
        this.answers = answers //[String]
        //this.roomateQuestion = roomateQuestion //String
    }

    async getQuestions() {
        const fs = require('fs/promises');
        var questionsUnparced = ""
        
        try{
            questionsUnparced = await fs.readFile('./Questions.txt', {encoding: 'utf-8'});
        } catch (err) {
            console.log(err)
        }
        
        let questionArray = this.parseData(questionsUnparced)

        console.log(questionArray)
        
        return questionArray
    }


    parseData(questionsUnparced){
        let parsedQuestion = questionsUnparced.split("\n");
        var question = new Question("",[]);
        var questions = [];

        
        parsedQuestion.forEach( (line) =>{
            if (line.charAt(0) == '-'){
                question.question = line
            }else if (line == ""){
                if(question.answers.length != 0){
                    questions.push(question)
                    //console.log(question)
                }
                question = new Question("",[]);
            }else if(line != ""){
                question.answers.push(line)
            }
        })

        return questions
    }
}



module.exports = Question

