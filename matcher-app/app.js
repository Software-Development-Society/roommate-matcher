const express = require("express");
const app = express();
const session = require("express-session");
const bodyParser = require('body-parser');
const passport = require("passport");
const passportLocalMongoose = require("passport-local-mongoose");
const mongoose = require("mongoose");
const { response } = require("express");

const Schema = mongoose.Schema;

app.use(express.static("public"));
app.use(bodyParser.urlencoded({
    extended: true
  }));
  
app.use(session({
    secret: 'keyboard cat',
    resave: false,
    saveUninitialized: false,
    //cookie: {secure: true}
}));

//mongoose.connect("mongodb://localhost:27017/userDB"/*, {useNewUrlParser:true}*/);

const userSchema = new Schema({
    fName: String,
    lName: String,
    age: Number,
    sex: String,
    classYear: Number,
    email: String,
    password: String,
    bio: String
});

userSchema.plugin(passportLocalMongoose);

const User = new mongoose.model("User", userSchema);

passport.use(User.createStrategy());

passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

app.use(passport.initialize());
app.use(passport.session());

app.get("/", function (req, res) {
    res.sendFile(__dirname + "/index.html");
});

app.get("/secret", (req, res)=>{
    if(req.isAuthenticated()){
        res.send("You're good");
    } else {
        res.redirect("/login");
    }
})

app.get("/login", function (req, res) {
    res.sendFile(__dirname + "/public/login.html");
});

app.get("/home", function (req, res) {
    res.sendFile(__dirname + "/public/homepage/homepage.html");
});

app.get("/register", function (req, res) {
    res.sendFile(__dirname + "/public/register.html");
});

app.get("/changeanswer", function (req, res) {
    res.sendFile(__dirname + "/public/changeans/change.html");
});

app.post("/login", function (req, res) {
    const user = new User({
        username: req.body.username,
        password: req.body.password,
    });
    req.logIn(user, (err)=>{
        if(err){
            console.log("error");
        } else {
            passport.authenticate("local", {failureRedirect:"Bozo"})(req, res, ()=>{
                console.log(req.user);
                console.log("You logged in good");
                console.log(req.user.id);
                res.redirect("/");
            });
        }
    })
});

/**
 * Register function takes a post request on the /register endpoint
 * It takes all the data within the form and enters it into the database along with the salt and hash
 */
app.post("/register", function (req, res) {
    console.log(req.body);
    User.register({username: req.body.username, fName: req.body.fName, lName: req.body.lName, age: Number(req.body.age), sex: req.body.sex, classYear: Number(req.body.classYear), bio: req.body.bio}, req.body.password, (err, user)=>{
        if(err){
            console.log(err);
            response.redirect("/register");
        } else {
            passport.authenticate("local")(req, res, ()=>{
                console.log(req.user.id);
                res.redirect("/");
            });
            
        }
    })
});

app.listen(3000, function () {
    console.log("Server started on port 3000");
});