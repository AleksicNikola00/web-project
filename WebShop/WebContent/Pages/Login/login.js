$(document).ready(function(){

    fillYear();
    fillDay();

    if (window.location.href.indexOf("?#") == -1)
        window.location.href = window.location.href + "?#";

    $("#regLink").on("click", function(){
        clearRegForm();
        $("#login").slideToggle(500);

        setTimeout(function(){
            $("#register").slideToggle(500);
            clearSignInForm();
        }, 500);
    });

    $("#backToLogin").on("click", function(){

        clearSignInForm();
        $("#register").slideToggle(500);

        setTimeout(function(){
            $("#login").slideToggle(500);
            clearRegForm();
        }, 500);
    });

    $("#signIn").on("click", function(){
        
        if (!validateInputSignIn()){
        	$("#staticBackdrop").modal('show');
        	return;
        }
        
        let username = $("#floatingInput").val();
		let password = $("#floatingPassword").val();
        
        $.post({
			url: 'rest/login',
			data: JSON.stringify({username: username, password: password, role: "0"}),
			contentType: 'application/json',
			success: function(data) {
                if (!data.includes('{')){
                    let dispText = $("#errorDisplay").empty();
                    dispText.append(data);
                    $("#staticBackdrop").modal('show');
                } else{
                    window.localStorage.setItem('User',data);
					window.location.replace("http://localhost:8080/WebShop/Pages/ShopperDashboard/dashboard.html");
                    clearRegForm();
                    clearSignInForm();
                }
			}
		});
    });

    $("#regMonth").on("change", function(){
        fillDay();
    })

    $("#regYear").on("change", function(){
        fillDay();
    })

    $("#doRegister").on("click", function(){

        if (!validateInput()){
            $("#staticBackdrop").modal('show');
        }
        else{
            let username = $("#regUsername").val();
            let password = $("#regPassword").val();
            let firstName = $("#regFirstName").val();
            let lastName = $("#regSurname").val();

            let year = $("#regYear").val();
            let month = $("#regMonth").val();
            let day = $("#regDay").val();

            let gender = $("#regGender").val();

            let potentialUser = {username: username,
                                password: password,
                                name: firstName,
                                surname: lastName,
                                dateOfBirth: new Date(year,month,day),
                                role: "0",
                                gender: gender};
            
            $.post({
                url: 'rest/register',
                data: JSON.stringify(potentialUser),
                contentType: 'application/json',
                success: function(data) {
                    if (data.length != 0){
                        let dispText = $("#errorDisplay").empty();
                        dispText.append(data);
                        $("#staticBackdrop").modal('show');
                    }
                    else{
                        let dispText = $("#errorDisplay").empty();
                        dispText.append("Successful!");
                        $("#staticBackdrop").modal('show');
                        clearRegForm();
                        clearSignInForm();
                    }
			}
            });

        }

    });

    $('.modal').on('shown.bs.modal', function() {
        $(this).find('[autofocus]').focus();
    });
});

function validateInputSignIn(){
	let username = $("#floatingInput").val();
	let password = $("#floatingPassword").val();
	
	let dispText = $("#errorDisplay").empty();
    let hadError = false;
	
	if (username.length == 0){
		dispText.append("Username is required...<br>");
		hadError = true;
	}
	if (password.length < 8){
		dispText.append("Last time I remember your password was atleast 8 characters long...<br>");
		hadError = true;
	}
	
	return !hadError;
	
}

function validateInput(){
    let username = $("#regUsername").val();
    let password = $("#regPassword").val();
    let firstName = $("#regFirstName").val();
    let lastName = $("#regSurname").val();

    let year = $("#regYear").val();
    let month = $("#regMonth").val();
    let day = $("#regDay").val();

    let dispText = $("#errorDisplay").empty();
    let hadError = false;

    if (!username.match("^([a-zA-Z0-9]+)$")){
        dispText.append("Username is not in the correct format! <br>");
        hadError = true;
    }

    if (password.length < 8){
        dispText.append("Password needs to be atleast 8 characters... <br>");
        hadError = true;
    }

    if (!firstName.match("^([a-zA-Z]+)$")){
        dispText.append("First name has some wrong characters... <br>");
        hadError = true;
    }

    if (!lastName.match("^([a-zA-Z]+)$")){
        dispText.append("Last name has some wrong characters... <br>");
        hadError = true;
    }

    let date = new Date(parseInt(year), parseInt(month), parseInt(day));
    let today = new Date();

    console.log(date.toDateString() + ' > ' + today.toDateString())

    if (date > today){
        dispText.append("You sure can't be that young... <br>");
        hadError = true;
    }

    return !hadError;
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

function clearRegForm(){
    $("#regUsername").val("");
    $("#regPassword").val("");
    $("#regFirstName").val("");
    $("#regSurname").val("");

    $("#regGender").val("Prefer not to say");

    $("#regYear").val(new Date().getFullYear());
    $("#regMonth").val("1");
    $("#regDay").val("1");
}

function clearSignInForm(){
    $("#floatingInput").val("");
    $("#floatingPassword").val("");
}
