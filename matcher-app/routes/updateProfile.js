const express = require('express');
const { User } = require('../db/schema');

const profileRouter = express.Router();


profileRouter.get('/profile', (req, res) =>{
    if(req.isAuthenticated()){
        const placeHolders = {
            firstName: req.user.firstName,
            lastName: req.user.lastName,
            age: req.user.age,
            sex: req.user.sex,
            instagram: req.user.instagram,
            snapchat: req.user.snapchat,
            pictureName: req.user.pictureName,
            classYear: req.user.classYear,
            bio: req.user.bio,   
        }
        //if(!req.user.registrationComplete){
            res.render('changeProfile/changeProfile', {styleInput: "changeProfile", isLoggedIn: req.isAuthenticated(), userInfo: placeHolders, userProfileImage: "http://localhost:3000/images/" + req.user.pictureName});
        //} else {
          //  res.redirect('/signup-form');
        //}
    } else {
        res.redirect('/login')
    }
});

profileRouter.post('/profile', (req, res) =>{
    //console.log("31", req.body)
    if(req.isAuthenticated()){
        if(req.user.registrationComplete){
            User.findById(req.user.id, function(err, user) {
                if(!err){
                    //console.log("herex", user);
                    User.findByIdAndUpdate(req.user.id, {
                        firstName: req.body.firstName,
                        lastName: req.body.lastName,
                        age: Number(req.body.age),
                        sex: req.body.sex,
                        classYear: Number(req.body.classYear),
                        bio: req.body.bio,
                        snapchat: req.body.snapchat,
                        instagram: req.body.instagram
                    }, (docs)=>{
                        //console.log("41", docs);
                        res.redirect("/profile");
                    })
                } else {
                    res.redirect('/profile');
                    console.log(err);
                }
            });
        } else {
            res.redirect('/signup-form')
        }
    } else {
        res.redirect('/login')
    }
})

module.exports = {profileRouter};