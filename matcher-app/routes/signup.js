const express = require('express');
const router = express.Router();
const { User } = require('../db/schema');

router.get("/signup-form", (req, res) =>{
    console.log("here");
    console.log()
   if(req.isAuthenticated()){
        if(req.user.registrationComplete){
            res.redirect("/submit-pic");
        } else {
            res.render('signup/signup', {styleInput: "homepage", isLoggedIn: req.isAuthenticated()});
        }
    } else {
        res.redirect("/login");
   }
});

router.post("/signup", function (req, res) {
    console.log("here ", req.body);
    if(req.isAuthenticated()){
        User.findById(req.user.id, function(err, user) {
            if(!err){
                console.log("herex", user);
                User.findByIdAndUpdate(req.user.id, {
                    firstName: req.body.firstName,
                    lastName: req.body.lastName,
                    age: Number(req.body.age),
                    sex: req.body.sex,
                    classYear: Number(req.body.classYear),
                    bio: req.body.bio,
                    registrationComplete: true,
                    snapchat: req.body.snapchat,
                    instagram: req.body.instagram
                }, (docs, err)=>{
                    if(!err){
                        console.log("35",docs);
                        res.redirect("/submit-pic")
                    } else {
                        console.log("37", err);
                    }
                })
            } else {
                res.redirect('/404');
                console.log(err);
            }
        });
    } else {
        res.redirect('/login')
    }
    // if (!validateEmail(req.body.username)) {
    //     res.send("Bad Email");
    //     return;
    // }
    
});

module.exports = router;