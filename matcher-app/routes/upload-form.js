const express = require('express');
const router = express.Router();

router.post("/upload-form", (req, res) => {
    if(Object.keys(req.body).length < 66){
        //Not all questions were answered
        console.log("Questions unanswered")
    }else{
        //api method to send to backend
        res.redirect('/dashboard')
    }
    console.log(req.body);
})

module.exports = router