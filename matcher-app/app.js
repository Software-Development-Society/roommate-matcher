const express = require("express");
const session = require("express-session");
const bodyParser = require('body-parser');
const axios = require('axios').default;
const router = require("./routes/ms-auth");
const signUpRouter = require('./routes/signup');
const { Problem } = require("./db/schema");

const app = express();

app.set('view engine', 'ejs');
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static("public"));




var isLoggedIn;

//ABSOLUTELY MAKE SURE TO CHANGE AND HIDE SECRET KEY BEFORE PRODUCTION
app.use(session({
    secret: 'keyboard cat',
    resave: false,
    saveUninitialized: false,
    //cookie: {secure: true}
}));




app.use('/', router);
app.use("/", signUpRouter);

app.get("/", function (req, res) {
    res.render('homepage/homepage', {styleInput: "homepage", isLoggedIn: req.isAuthenticated()});
});

app.get("/dashboard", function (req, res) {
    if(req.user.questionsFormComplete){
        res.render('dashboard/dashboard', {styleInput: "dashboard", isLoggedIn: req.isAuthenticated()}); 
    } else {
        res.redirect('/signup-form')
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

app.get("/form", function (req, res) {
    const questions = require('./Questions.json');
    //console.log(questions)
    res.render('form/form', {styleInput: "homepage", isLoggedIn: req.isAuthenticated(), questions: questions});
});

app.get("/signout", function (req, res) {
    req.logout(function(err) {
        if (err){ 
            res.send("There was an error signing you out");
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
