$(document).ready(function(){

    fillYear();
    fillDay();

    $("#regLink").on("click", function(){

        $("#login").slideToggle(500);

        setTimeout(function(){
            $("#register").slideToggle(500);
        }, 500);
    });

    $("#backToLogin").on("click", function(){

        $("#register").slideToggle(500);

        setTimeout(function(){
            $("#login").slideToggle(500);
        }, 500);

    });

    $("#signIn").on("click", function(){
        /* try login */
    });

    $("#regMonth").on("change", function(){
        fillDay();
    })

    $("#regYear").on("change", function(){
        fillDay();
    })

    $("#doRegister").on("click", function(){

        if (!validateInput()){
            /* display error */
        }

        /* try register */

    });
});

function validateInput(){
    let username = $("#regUsername");
    let password = $("#regPassword");
    let firstName = $("#regFirstName");
    let lastName = $("#regSurname");
    let dateOfBirth = $("#regDate");

    let dispText = $("#errorDisplay").empty();

    if (!username.match("^([a-zA-Z0-9]+)$")){
        dispText.append("Username is not in the correct format! <br>");
    }

    if (password.length <= 8){
        dispText.append("Password needs to be atleast 8 characters... <br>");
    }

    if (!firstName.match("^([a-zA-Z]+)$")){
        dispText.append("First name has some wrong characters... <br>");
    }

    if (!lastName.match("^([a-zA-Z]+)$")){
        dispText.append("Last name has some wrong characters... <br>");
    }
};

function fillDay(){
    let element = $("#regDay");
    element.empty();

    let month = $("#regMonth").val();
    let year = $("#regYear").val();
    let yearNum = parseInt(year,10);

    element.append("<option value='1' selected>1</option>");

    if (month === "januray" || month === "march" || month === "may" || 
        month === "july" || month === "august" || month === "october" || month === "december"){
        for(let k = 2; k <= 31; k++){
            element.append("<option value ='" + k + "'>" + k + "</option>");
        }
    }
    else if (month === "february"){

        if (yearNum % 4 != 0){
            for(let k = 2; k <= 28; k++){
                element.append("<option value ='" + k + "'>" + k + "</option>");
            }
        }
        else{
            for(let k = 2; k <= 29; k++){
                element.append("<option value ='" + k + "'>" + k + "</option>");
            }
        }
    }
    else{
        for(let k = 2; k <= 30; k++){
            element.append("<option value ='" + k + "'>" + k + "</option>");
        }
    }
};

function fillYear(){

    let year = $("#regYear");

    let end = new Date().getFullYear();
    let begin = end - 100;


    for(let k = begin ; k <= end - 1; k++){
        year.append("<option value='" + k + "'>" + k + "</option>");
    }

    year.append("<option value='" + end + "' selected>" + end + "</option>");
};
