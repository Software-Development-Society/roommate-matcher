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
            console.log("error");
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
        bio: req.body.bio},  
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