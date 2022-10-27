const express = require("express");
const app = express();
const session = require("express-session");
const bodyParser = require('body-parser');
const passport = require("passport");
const passportLocalMongoose = require("passport-local-mongoose");
const mongoose = require("mongoose");
const {
    response
} = require("express");
const axios = require('axios').default;

const Schema = mongoose.Schema;

app.use(express.static("public"));
app.use(bodyParser.urlencoded({
    extended: true
}));


//ABSOLUTELY MAKE SURE TO CHANGE AND HIDE SECRET KEY BEFORE PRODUCTION
app.use(session({
    secret: 'keyboard cat',
    resave: false,
    saveUninitialized: false,
    //cookie: {secure: true}
}));


//MAKE SURE TO HIDE AND CHANGE THIS AS WELL
mongoose.connect("mongodb+srv://vsds:lnBKl03NLjuCiieO@vsds.nio2wr0.mongodb.net/roommateMatcher?retryWrites=true&w=majority" /*, {useNewUrlParser:true}*/ );


const userSchema = new Schema({
    firstName: String,
    lastName: String,
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
    res.sendFile(__dirname + "/public/homepage/homepage.html");
});

app.get("/secret", (req, res) => {
    if (req.isAuthenticated()) {
        res.send("You're good");
    } else {
        res.redirect("/login");
    }
})

app.get("/login", function (req, res) {
    res.sendFile(__dirname + "/public/login.html");
});

app.get("/register", function (req, res) {
    res.sendFile(__dirname + "/public/register.html");
});



app.post("/login", function (req, res) {
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
                loginRequest(userID)

                res.redirect("/");
                
            });
        }
        

        //res.send(userID)
    })
});
async function loginRequest(userID){
    axios({
        method: 'post',
        url: 'http://localhost:8080/login',
        data: {
            user_id: userID,
        }
    }).then(response => {
        console.log(response);
        //res.send(response);
    }).catch(error => {
        console.log(error);
        //res.send(error)
    });
}

/**
 * Register function takes a post request on the /register endpoint
 * It takes all the data within the form and enters it into the database along with the salt and hash
 */
app.post("/register", function (req, res) {
    console.log(req.body);
    User.register({
        username: req.body.username,
        firstName: req.body.firstName,
        lastName: req.body.lastName,
        age: Number(req.body.age),
        sex: req.body.sex,
        classYear: Number(req.body.classYear),
        bio: req.body.bio
    }, req.body.password, (err, user) => {
        if (err) {
            console.log(err);
            response.redirect("/register");
        } else {
            passport.authenticate("local")(req, res, () => {
                console.log(req.user.id);
                res.redirect("/");
            });

        }
    })
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

app.listen(3000, function () {
    console.log("Server started on port 3000");
});
