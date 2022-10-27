function validateEmail(email){
    email = email.toLowerCase();
    if (!email.endsWith("@villanova.edu")) {
        console.log(email + ": Invalid email")
    } else {
        console.log(email + ": Valid email")
    }
}
//doesn't currently check if email ends in multiple @villanova.edu's but wouldn't be valid email address anyways