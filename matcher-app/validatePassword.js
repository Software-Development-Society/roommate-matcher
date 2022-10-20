var password = prompt("Create a password");

//Checks if the password is long enough
function lengthTest(password) {
  if (password.length >= 10) {
    console.log(true);
  }
  else {
    console.log(false);
  }
}

//Checks if the password has numbers in it
/*function numberTest(password) {
  for (var i = 0; password.length; i++) {
    if (password[i] == '0' || password[i] == '0')
      console.log(true);
    else 
      console.log(false);
  }
}
*/
/*
//Checks if the password has symbols in it
function symbolsTest(password) {
  
}
*/
lengthTest(password);
numberTest(password);
//numberTest(password);