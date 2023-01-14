const mongoose = require("mongoose");
const passportLocalMongoose = require("passport-local-mongoose");
const findOrCreate = require('mongoose-findorcreate');

const Schema = mongoose.Schema;

//MAKE SURE TO HIDE AND CHANGE THIS AS WELL
//mongoose.connect("mongodb+srv://vsds:lnBKl03NLjuCiieO@vsds.nio2wr0.mongodb.net/roommateMatcher?retryWrites=true&w=majority" /*, {useNewUrlParser:true}*/ );
mongoose.connect('mongodb://localhost/rm', {useNewUrlParser: true});

const userSchema = new Schema({
    msId: String,
    firstName: String,
    lastName: String,
    age: Number,
    sex: String,
    classYear: Number,
    email: String,
    password: String,
    bio: String,
    userCompletedSignup: Boolean
});

userSchema.plugin(passportLocalMongoose);
userSchema.plugin(findOrCreate);

const User = new mongoose.model("User", userSchema);

module.exports = {User};