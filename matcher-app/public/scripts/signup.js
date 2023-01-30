function validSubmit(){
    var formId = "signup-form";
    console.log(document.getElementById("cy-form").value);
    if(document.getElementById("cy-form").value != "0"){
        if(document.getElementById("sex").value != "0"){
            console.log("good");
            console.log()
            console.log(document.getElementById('lname').value)
            const complete = document.getElementById('fname').value != "" && document.getElementById('lname').value != "" && document.getElementById('age').value > 0 &&  document.getElementById('bio').value != ""
            console.log(complete);
            if(complete){
                document.getElementById("signup-form").submit();
            } else {
                window.alert("You have not completed the form, cannot submit");
            }
        } else {
            window.alert("You have not completed the form, cannot submit");
        }
    } else {
        window.alert("You have not completed the form, cannot submit");
    }
}   