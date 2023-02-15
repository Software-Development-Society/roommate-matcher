const express = require("express");
const session = require("express-session");
const bodyParser = require('body-parser');
const axios = require('axios').default;
const router = require("./routes/ms-auth");
const signUpRouter = require('./routes/signup');
const { Problem, Question } = require("./db/schema");
const {imagesRouter} = require('./routes/images');
const { profileRouter } = require("./routes/updateProfile");

const app = express();

app.set('view engine', 'ejs');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static("public"));


//ABSOLUTELY MAKE SURE TO CHANGE AND HIDE SECRET KEY BEFORE PRODUCTION
app.use(session({
    secret: 'keyboard cat',
    resave: false,
    saveUninitialized: false,
    //cookie: {secure: true}
}));

app.use('/', router);
app.use("/", signUpRouter);
app.use("/", imagesRouter);
app.use("/", profileRouter);

app.get("/", function (req, res) {
    res.render('homepage/homepage', {styleInput: "homepage", isLoggedIn: req.isAuthenticated()});
});

app.get("/dashboard", async function (req, res) {
    if(req.isAuthenticated()){
        let matchesArray = [];
        try {
            const response = await axios({
                method: 'post',
                url: 'http://localhost:8080/get-matches',
                data: {
                    user_id: req.user.id,
                }
            });
            const data = response.data;
            matchesArray = [...data.match_array];
            //console.log(data)
        } catch (error) {
            console.log(error);
        }
        if(req.user.questionsFormComplete){
            res.render('dashboard/dashboard', {styleInput: "dashboard", isLoggedIn: req.isAuthenticated(), matchesArray: matchesArray , userProfileImage: "http://localhost:3000/images/" + req.user.pictureName}); 
        } else {
            res.redirect('/form')
        }
    } else {
        res.redirect('/login');
    }
});
app.get("/problem", function (req, res) {   
    if(!req.isAuthenticated()){
        req.headers.problem = true;
        console.log("problem 48", req.headers.problem)
        res.redirect('/login');
    } else {
        res.render('problem/problem', {styleInput: "problem", isLoggedIn: req.isAuthenticated(), submitted: false});
    } 
});

app.get("/form", async function (req, res) {
    if(req.isAuthenticated()){
        if(req.user.registrationComplete){
            if(req.user.pictureName){
                const questions = require('./Questions.json');
                //console.log(questions)
                if(req.user.questionsFormComplete){
                    let answers = [];
                    try {
                        const result = await Question.findById(req.user.id)
                        answers = [...result.responses]
                        console.log("result here line 83 app.js", result.responses)
                    } catch (error) {
                        console.log(error)                      
                    }
                    res.render('form/form2', {styleInput: "homepage", isLoggedIn: req.isAuthenticated(), questions: questions, error:false, answers: answers});
                } else {
                    res.render('form/form', {styleInput: "homepage", isLoggedIn: req.isAuthenticated(), questions: questions, error:false});
                }
            } else {
                res.redirect('/submit-pic');
                return;
            }
        } else {
            res.redirect('/signup-form');
            return;
        }
    } else {
        res.redirect('/login');
        return;
    }
});



app.get("/signout", function (req, res) {
    req.logout(function(err) {
        if (err){ 
            res.send("There was an error signing you out");
            return
        }
        res.redirect('/');
    });
});


//These last 2 routes are just for API testing purposes.
app.get("/secret", (req, res) => {
    if (req.isAuthenticated()) {
        res.send("You're good");
    } else {
        res.redirect("/login");
    }
})
app.post('/problem', (req, res) =>{
   if(!req.isAuthenticated()){
    req.headers.problem = true;
    res.redirect('/login');
   } else {
    console.log("problem description", req.body.problemDescription)
    var problem = new Problem({ 
        msId: req.user.msId,
        firstName: req.user.firstName,
        lastName: req.user.lastName,
        email: req.user.email, 
        problemDescription: req.body.problemDescription, 
    });
 
    // save model to database
    problem.save(function (err, book) {
      if (err){
        console.log(err)
        res.render('problem/problem', {styleInput: "problem", isLoggedIn: req.isAuthenticated(), submitted: true, error: true});
      } else {
        res.render('problem/problem', {styleInput: "problem", isLoggedIn: req.isAuthenticated(), submitted: true, error: false});
      }
    });
    
   } 
});
app.get("/test", async (req, res) => {
    const userID = req.query.userID;
    console.log(userID);
    //res.send(userID)
    axios({
        method: 'post',
        url: 'http://localhost:8080/login',
        data: {
            user_id: userID,
        }
    }).then(response => {
        console.log(response);
        res.send(response);
    }).catch(error => {
        console.log(error);
        res.send(error)
    });
});

//404 Route
app.get('*', function(req, res){
    res.render('404/404', {styleInput: "404styles", isLoggedIn: req.isAuthenticated()})
});

app.listen(3000, function () {
    console.log("Server started on port 3000");
});
