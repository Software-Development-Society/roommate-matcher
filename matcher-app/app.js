const express = require("express");
const session = require("express-session");
const bodyParser = require('body-parser');
const axios = require('axios').default;

const validateEmail = require('./functions/validateEmail');
const { loginRequest } = require("./requests/login");
const { usePassport, authRouter } = require("./routes/auth");
const { User } = require("./db/schema");

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



app.use(usePassport.initialize());
app.use(usePassport.session());

app.use('/', authRouter);

app.get("/", function (req, res) {
    res.render('homepage/homepage', {styleInput: "homepage", isLoggedIn: req.isAuthenticated()});
});

app.get("/signup", function (req, res) {
    res.render('signup/signup', {styleInput: "homepage", isLoggedIn: req.isAuthenticated()});
});

app.get("/login", function (req, res) {
    res.render('login/login', {styleInput: "homepage", isLoggedIn: req.isAuthenticated()});
});

app.get("/dashboard", function (req, res) {
    res.render('dashboard/dashboard', {styleInput: "dashboard", isLoggedIn: req.isAuthenticated()});
});
app.get("/problem", function (req, res) {
    res.render('problem/problem', {styleInput: "problem", isLoggedIn: req.isAuthenticated()});
});

app.get("/form", function (req, res) {
    const questions = require('./Questions.json');
    console.log(questions)
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
