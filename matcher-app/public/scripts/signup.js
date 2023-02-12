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

function validSubmit2(){
    var formId = "change-prof";
    console.log(document.getElementById("cy-form2").value);
    if(document.getElementById("cy-form2").value != "0"){
        if(document.getElementById("sex2").value != "0"){
            console.log("good");
            console.log()
            console.log(document.getElementById('lname2').value)
            const complete = document.getElementById('fname2').value != "" && document.getElementById('lname2').value != "" && document.getElementById('age2').value > 0 &&  document.getElementById('bio2').value != ""
            console.log(complete);
            if(complete){
                document.getElementById(formId).submit();
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