function validateEmail(email){
    let valid = false;
    let pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    email = email.toLowerCase();
    if (email.endsWith("@villanova.edu") && email.match(pattern)) {
        valid = true;
        console.log(email + " is a valid email");
    } else {
        console.log(email + " is INVALID");
    }
    return valid;
}

module.exports=validateEmail;