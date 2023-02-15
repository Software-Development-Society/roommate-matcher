const mongoose = require("mongoose");
const passportLocalMongoose = require("passport-local-mongoose");
const findOrCreate = require('mongoose-findorcreate');

const Schema = mongoose.Schema;

//MAKE SURE TO HIDE AND CHANGE THIS AS WELL
mongoose.connect("mongodb+srv://vsds:lnBKl03NLjuCiieO@vsds.nio2wr0.mongodb.net/roommateMatcher?retryWrites=true&w=majority" /*, {useNewUrlParser:true}*/ );
//mongoose.connect('mongodb://localhost/rm', {useNewUrlParser: true});

const reportProblemSchema = new Schema({
   msId: String,
   firstName: String,
   lastName: String,
   email: String, 
   problemDescription: String, 
});

const questionAnswersSchema = new Schema({
    sex: String,
    responses: Schema.Types.Mixed,
    _class: String
})

const userSchema = new Schema({
    username: String,
    msId: String,
    firstName: String,
    lastName: String,
    age: Number,
    sex: String,
    classYear: Number,
    email: String,
    bio: String,
    picture: Buffer,
    pictureName: String,
    registrationComplete:Boolean,
    questionsFormComplete: Boolean,
    snapchat: String,
    instagram: String,
    
});
userSchema.plugin(passportLocalMongoose);
userSchema.plugin(findOrCreate);

const User = mongoose.model("User", userSchema);
const Problem = mongoose.model("Problem", reportProblemSchema)
const Question = mongoose.model("Question", questionAnswersSchema)

module.exports = {User, Problem, Question};