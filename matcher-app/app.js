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
app.use(express.static("public"));

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
    res.render('homepage/homepage', {styleInput: "homepage"});
});

app.get("/register", function (req, res) {
    res.render('register/register', {styleInput: "homepage"});
});

app.get("/login", function (req, res) {
    res.render('login/login', {styleInput: "homepage"});
});

app.get("/dashboard", function (req, res) {
    res.render('dashboard/dashboard', {styleInput: "dashboard"});
});
app.get("/problem", function (req, res) {
    res.render('problem/problem', {styleInput: "problem"});
});
app.get("/form", function (req, res) {
    res.render('form/form', {styleInput: "homepage"});
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
    res.render('404/404', {styleInput: "404styles"})
  });

app.listen(3000, function () {
    console.log("Server started on port 3000");
});
