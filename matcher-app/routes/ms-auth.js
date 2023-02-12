const express = require('express');
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
    clientID: "25edda00-5ff3-4300-8ad5-4674943afe76",
    clientSecret: "5X78Q~otkD4H_6DvpXONFx1VCk9zuZ1Im311Tcxa",
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
        //console.log(results);
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
        await User.findOrCreate({ 
          username: profile.emails[0].value,
          msId: profile.id, 
          email: profile.emails[0].value, 
          registrationComplete: false, 
          picture: null, 
          questionsFormComplete:false, 
          firstName:null, 
          lastName:null, 
          age:null, 
          classYear:null, 
          snapchat:null, 
          instagram:null, 
          sex:null,
          bio:null,
          pictureName: null,
        }, 
          function (err, user) {
            //console.log("44", user);
            return cb(err, user);
        });
      } else {
        await User.findOrCreate({ msId: profile.id, email: profile.emails[0].value}, function (err, user) {
          //console.log("45", user);
          if(!err){
            console.log("66", err);
            return cb(err, user);
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
    if(!req.user.registrationComplete){
      res.redirect('/signup-form');
    } else if(req.user.picture === null){
      console.log("here");
      res.redirect('/submit-pic')
    } else if(!req.user.questionsFormComplete){
        res.redirect('/form');
    } else {
        res.redirect('/dashboard');
    }
    
}, function(err, req, res, next){
    console.log("ms-auth 77: ", err)
    res.redirect("http://localhost:3000/login-failed-error");
});


router.get("/check", (req, res) =>{
  console.log(req.isAuthenticated());
  if(req.isAuthenticated()){
    console.log(req.user);
    res.redirect("/good-check");
  } else {
    res.redirect("/bad-check");
  }
})

module.exports = router;