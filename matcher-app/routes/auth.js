const express = require('express');

const passport = require("passport");
const { User } = require("../db/schema");
const { loginRequest } = require('../requests/login');

passport.use(User.createStrategy());
passport.serializeUser(User.serializeUser());
passport.deserializeUser(User.deserializeUser());

const router = express.Router();

router.post("/login", function (req, res) {

    const user = new User({
        username: req.body.username,
        password: req.body.password,
    });
    //res.send("Worked");
    req.logIn(user, (err) => {
        if (err) {
            console.log(err);
        } else {
            passport.authenticate("local", {
                failureRedirect: "Bozo"
            })(req, res, () => {
                const userID = req.user.id;
                console.log(req.user);
                console.log("You logged in good");
                console.log(req.user.id);
                //loginRequest(userID);
                res.redirect("/");
                
            });
        }
        //res.send(userID)
    })
});

router.post("/upload-form", (req, res) =>{

    console.log("upload form was accessed!")
    //req body will include 3 values per question
    //1. the number of answers in the questions answers (usually 5)
    //2. the value of the question's answer 
    //3. the value of the roommate question's answer
        //Values for roommate question's answers = 1,2,3,4 and correspond with 1,2,4,5 rating 
    //Ask Jack Johnston (me) if you have any questions
    
    
    //console.log(req.body) --------DEBUGGING CODE--------------


    //THINGS THAT NEED TO BE DONE IN THIS METHOD
    //1. api call to the Java backend to send the answers
    //2. send data to the db

})

router.post("/signup", function (req, res) {
    console.log(req.body);
    // if (!validateEmail(req.body.username)) {
    //     res.send("Bad Email");
    //     return;
    // }
    User.register({
        username: req.body.username,
        firstName: req.body.firstName,
        lastName: req.body.lastName,
        age: Number(req.body.age),
        sex: req.body.sex,
        classYear: Number(req.body.classYear),
        bio: req.body.bio,
        instagram: req.body.instagram,
        snapchat: req.body.snapchat},  
        req.body.password, (err, user) => {
        if (err) {
            console.log(err);
            res.redirect("/signup");
        } else {
            passport.authenticate("local")(req, res, () => {
                console.log(req.user.id);
                res.redirect("/");
            });
        }
    })
});

module.exports = {usePassport : passport, authRouter : router};