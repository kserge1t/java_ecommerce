// AJAX real-time checking
$(document).ready(function(){
	$("#confirmPassword").prop("disabled", true);
	
    $("#firstName,#lastName input").keyup(function(){
		var firstName = spaceTrim(document.forms["userForm"]["firstName"].value);
		var lastName = spaceTrim(document.forms["userForm"]["lastName"].value);
		
		if (firstName.length < 1) {
			$("#firstName .msg").css("color", "red");
			$("#firstName .msg").html("&#x2718; - First Name cannot be blank!");
		} else {
			$("#firstName .msg").css("color", "green");
			$("#firstName .msg").html("&#x2714;");
		}
		
		if (lastName.length < 1) {
			$("#lastName .msg").css("color", "red");
			$("#lastName .msg").html("&#x2718; - Last Name cannot be blank!");
		} else {
			$("#lastName .msg").css("color", "green");
			$("#lastName .msg").html("&#x2714;");
		}
    });
    
    $("#firstName,#lastName input").focusout(function(){
    	document.forms["userForm"]["firstName"].value = spaceTrim(document.forms["userForm"]["firstName"].value);
    	document.forms["userForm"]["lastName"].value = spaceTrim(document.forms["userForm"]["lastName"].value);
    });
	
    $("#email input").keyup(function(){
		var email = document.forms["userForm"]["email"].value;
		
		if (!validateEmail(email)) {
			$("#email .msg").css("color", "red");
			$("#email .msg").html("&#x2718; - '"+email+"' is not a valid email!");
		} else {
			$("#email .msg").css("color", "green");
			$("#email .msg").html("&#x2714;");
		}
    });
	
    $("#password input").keyup(function(){
		var password = document.forms["userForm"]["password"].value;
		document.forms["userForm"]["confirmPassword"].value = "";
		$("#confirmPassword .msg").text("");

		if (!validatePassword(password)) {
			$("#password .msg").css("color", "red");
			$("#password .msg").html("&#x2718; - "+password.length+" characters password is too short, make it at least 6!");
			$("#confirmPassword").prop("disabled", true);
		} else {
			$("#password .msg").css("color", "green");
			$("#password .msg").html("&#x2714;");
			$("#confirmPassword").prop("disabled", false);
		}
    });
	
    $("#confirmPassword input").keyup(function(){
		var password = document.forms["userForm"]["password"].value;
		var confirmPassword = document.forms["userForm"]["confirmPassword"].value;

		if (password != confirmPassword) {
			$("#confirmPassword .msg").css("color", "red");
			$("#confirmPassword .msg").html("&#x2718; - Passwords do not match!");
		} else {
			$("#confirmPassword .msg").css("color", "green");
			$("#confirmPassword .msg").html("&#x2714;");
		}
    });
	
});


// JS on-submit validation
function validateUserForm() {
	
	var firstName = $.trim(document.forms["userForm"]["firstName"].value);
	var lastName = $.trim(document.forms["userForm"]["lastName"].value);
    var email = document.forms["userForm"]["email"].value;
    var password = document.forms["userForm"]["password"].value;
    var confirmPassword = document.forms["userForm"]["confirmPassword"].value;
    
    if (firstName.length == 0 || lastName.length == 0 || email.length == 0 || password.length == 0 || confirmPassword.length == 0) {
    	alert("Please fill all the required fields.");
        return false;
    }
    
    if (!validateEmail(email)) {
    	alert("You have entered an invalid email address!");
    	userForm.email.focus();
        return false;
    }
    
    if (!validatePassword(password)) {
    	alert("Password must be at least 6 characters long!");
    	userForm.password.focus();
        return false;
    }
    
    if (password != confirmPassword) {
    	alert("Both password fields must match!");
    	userForm.password.focus();
        return false;
    }
}

//validating email
function validateEmail(email) {
    var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,63})+$/;
	return (emailFormat.test(email));
}

// validating password
function validatePassword(password) {
    var passwordFormat = /.{6,}/;
	return (passwordFormat.test(password));
}

// Trim beginning/end whitespace, replace multi-whitespace with single space 
function spaceTrim(string) {
	return $.trim(string.replace(/\s{2,}/g," "));
}

