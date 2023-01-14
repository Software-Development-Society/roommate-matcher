const express = require('express');
const bodyParser = require('body-parser');
const passport = require('passport');
var MicrosoftStrategy = require('passport-microsoft').Strategy;
require('dotenv').config()
const { User } = require('../db/schema');



const router = express.Router();

router.use(passport.initialize());
router.use(passport.session());

passport.use(User.createStrategy());

passport.serializeUser(function(user, done) {
  done(null, user.id);
});

passport.deserializeUser(function(id, done) {
  User.findById(id, function(err, user) {
    done(err, user);
  });
});

passport.use(new MicrosoftStrategy({
    clientID: "",
    clientSecret: "",
    callbackURL: "http://localhost:3000/auth/ms",
    scope: ['user.read'],
  },
  async function(accessToken, refreshToken, profile, cb) {
    console.log("here");
    const regex = new RegExp('^[A-Za-z0-9._%+-]+@villanova.edu$')
    //console.log("profile info:", profile);
    const email = profile.emails[0].value;
    
    console.log(profile.emails[0].value, email);
    if(regex.test(email)){
      let firstTime = await User.find({email: email}).then(results =>{
        console.log(results);
        if(results.length < 1){
            //It is users first time
            
            return true;
        } else {
            return false;
        }
      }).catch(error =>{
        console.log(error);
      });
      console.log("first time", firstTime);
      if(firstTime){
        await User.findOrCreate({ msId: profile.id, email: profile.emails[0].value, userCompletedSignup: false}, function (err, user) {
          console.log("44", user);
          if(!err){
          }
          return cb(err, user);
        });
      } else {
        await User.findOrCreate({ msId: profile.id, email: profile.emails[0].value}, function (err, user) {
          console.log("44", user);
          if(!err){
          }
          return cb(err, user);
        });
      }
    }
        
  }
));

router.get('/login', passport.authenticate('microsoft'));

router.get('/auth/ms', 
  passport.authenticate('microsoft', {failWithError:true }),
  function(req, res) {
    //Successful authentication, redirect home.
    //console.log("68", req.user);
    //console.log("\nAuth info", req.user)
    //console.log(req)
    res.redirect("http://localhost:3000/logged-in-good");
    
    //res.json({"Message": "Success", token: req.authInfo, userId: req.user.id, email: req.user.email, picture: req.user.picture});
}, function(err, req, res, next){
    console.log("ms-auth 77: ", err)
    res.redirect("http://localhost:3000/login-failed-error");
});


router.get("/check", (req, res) =>{
  console.log(req.isAuthenticated());
  if(req.isAuthenticated()){
    res.redirect("/good-check");
  } else {
    res.redirect("/bad-check");
  }
})

module.exports = router;