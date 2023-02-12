const express = require('express');
const router = express.Router();
const { User } = require('../db/schema');
const multer = require('multer');


//This is used to import the router for upload-form.js
//Used to help differentiate the router and make the code more readable
const uploadFormRouter = require('./upload-form')
router.use(uploadFormRouter)

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
    //console.log("here ", req.body);
    if(req.isAuthenticated()){
        if(req.user.registrationComplete){
            res.json({"Message": "Ayooo??? You already completed registration, wyd homie."});
            return;
        }
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
                    registrationComplete: true,
                    snapchat: req.body.snapchat,
                    instagram: req.body.instagram
                }, (docs)=>{
                    //console.log("41", docs);
                    res.redirect("/submit-pic");
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

router.get("/submit-pic", (req, res) =>{
    if(req.isAuthenticated()){
        if(!req.user.registrationComplete){
            res.redirect('/signup-form');
            return;
        }
        if(!req.user.picture){
            res.render('pfp/pfp', {styleInput: "homepage", isLoggedIn: req.isAuthenticated(), fileError: ""});
        } else {
            res.redirect('/form');
        }
    } else {
        res.redirect('/login');
    }
});
const path = require('path');
const { imageToBase64, deleteFile } = require('./images');

const storage = multer.diskStorage({
    destination: (req, file, cb) =>{
        cb(null, path.join(__dirname,'../uploadedImages'))
    },
    filename: (req, file, cb) =>{
        req.headers.fileName = "fname-"+req.user.firstName+"-MongoID-"+req.user.id + ".jpg";
        cb(null, "fname-"+req.user.firstName+"-MongoID-"+req.user.id + ".jpg");
    }
})
// let regex = new RegExp(/\.(gif|jpe?g|tiff?|png|webp|bmp)$/i);
//             if (regex.test(path.extname(file.originalname).toString()) == true){
//                 cb(null, "fname-"+req.user.firstName+"-MongoID-"+req.user.id +  path.extname(file.originalname));
//             } else {
//                 req.body.fileError = "Wrong type of file, please upload new one";
//             }
const upload = multer({
    storage:storage, 
    limits: {
        fieldNameSize: 300,
        fileSize: 1048576, // 10 Mb
    },
    fileFilter: (req, file, cb) =>{
        let regex = new RegExp(/\.(jpe?g|tiff?|png|webp|bmp)$/i);
        if (regex.test(path.extname(file.originalname).toString()) != true){
            req.body.fileError = "Wrong Type";
            return cb(null, false, req.fileValidationError);
        }
        const fileSize = parseInt(req.headers['content-length']);
        if (fileSize > 1048576) {
            req.body.fileError = "Too Big";
            return cb(null, false, req.fileValidationError);
        }
        req.body.fileError = "";
        return cb(null, true, req.fileValidationError);
    }
})

router.post("/submit-pic", upload.single('image'),(req, res) =>{
    if(req.isAuthenticated()){
        if(!req.user.picture){
            //console.log("102x", err);
            //console.log("102", err);
            //The first condition runs if the file is too big or if the file is of the wrong type
            if(req.body.fileError !== ""){
                res.render('pfp/pfp', {styleInput: "homepage", isLoggedIn: req.isAuthenticated(), fileError: req.body.fileError});
            } else{//This condition means the user has successfully submitted a picture
                console.log("Good");
                const buffer = imageToBase64(path.join(__dirname,'../uploadedImages/')+req.headers.fileName);
                User.findByIdAndUpdate(req.user.id, {
                    picture: buffer,
                    pictureName: req.headers.fileName
                }, (docs, err)=>{
                    //console.log("37", docs, err);
                    //res.redirect("/submit-pic");
                })
                deleteFile(path.join(__dirname,'../uploadedImages/')+req.headers.fileName)
                console.log("buffer",buffer);
                res.redirect("/form");
                
            }
            
        } else {
            if(req.user.questionsFormComplete){
                res.redirect('/dashboard');
            } else {
                res.redirect('/form');//send them to the questions form
            }
        }
    } else {
        res.redirect('/login');
    }
});




module.exports = router;