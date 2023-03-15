const express = require('express');
const fs = require("fs");
const { User } = require('../db/schema');

const imagesRouter = express.Router();

function imageToBase64(path){
    // Create a base64 string from an image => ztso+Mfuej2mPmLQxgD ...
    const base64 = fs.readFileSync(path, "base64");
    // Convert base64 to buffer => <Buffer ff d8 ff db 00 43 00 ...
    const buffer = Buffer.from(base64, "base64");
    
    return buffer;
}
function deleteFile(path){
    fs.unlink(path, (err) => {
        if (err) {
          console.log(err)
          return
        }
    })
}

imagesRouter.get("/images/:pictureName", async function(req, res) {
    if(req.isAuthenticated()){
        try {
            const results = await User.findOne({pictureName:  req.params.pictureName})
            if(!results){
                //console.log("No results, returning")
                return
            }
            var data = results.picture;
            var img = Buffer.from(data, 'binary');
            res.writeHead(200, {
                'Content-Type': 'image/png',
                'Content-Length': img.length
            });
            res.end(img);
        } catch (error) {
            console.log(error)
            res.send('There was an error');
        }
        
    } else {
        res.redirect('/login')
    }
});

module.exports = {imagesRouter, imageToBase64, deleteFile};