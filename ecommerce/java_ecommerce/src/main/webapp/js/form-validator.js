// AJAX form validation
$(document).ready(function(){
	
	var originalMsg = $("#msg").html(); // Store original message
	
	// Restore original message and style
	$("input").focusout(function(){
		$("#msg").html(originalMsg);
		$("#msg").attr({"style":""});
	});
	
    // Validate email
    $(".email").keyup(function(){
    	var email = $(this).val();
		if (!validateEmail(email)) {
			$(this).prop("id","err");
			errMsg("Typed Email \""+email+"\" is not a valid!");
		} else {
			$(this).prop("id","");
			okMsg("Provided Email \""+email+"\" is valid.");
		}
    });
	
	// Validate password
    $(".password").keyup(function(){
    	var password = $(this).val();
		if (!validatePassword(password)) {
			$(this).prop("id","err");
			errMsg(password.length+" characters password is too short, make it at least 6!");
		} else {
			$(this).prop("id","");
			okMsg("Password is valid.");
		}
		$(this).closest("form").find(".confirmPassword").prop("id","err");
		$(this).closest("form").find(".confirmPassword").val("");
    });
	
    // Confirm password
    $(".confirmPassword").keyup(function(){
    	var password = $(this).closest("form").find(".password").val();
    	var confirmation = $(this).val();
		if (password != confirmation) {
			$(this).prop("id","err");
			errMsg("Passwords do not match!");
		} else {
			$(this).prop("id","");
			okMsg("Passwords match.");
		}
    });
    
    // Enable-Disable submit button
    $("input").keyup(function(){
		if ($(this).closest("form").find("#err").length > 0) {
			$(this).closest("form").find("input[type=submit]").prop("disabled",true);
		} else {
			$(this).closest("form").find("input[type=submit]").prop("disabled",false);
		}
		$(this).closest("form").find("#delete").prop('checked', false);
    });
    
    // Enable-Disable submit button (with delete checkbox)
    $("#delete").focus(function(){
		if ($(this).is(':checked')) {
			$(this).closest("form").find("input[type=submit]").prop("disabled",false);
			errMsg("Warning! Your account will be deleted!")
		} else {
			$(this).closest("form").find("input[type=submit]").prop("disabled",true);
		}
    });
	
});


// Email validation
function validateEmail(email) {
    var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,63})+$/;
	return (emailFormat.test(email));
}

// Password validation
function validatePassword(password) {
    var passwordFormat = /.{6,}/;
	return (passwordFormat.test(password));
}

// Trim beginning/end whitespace, replace multi-whitespace with single space 
function spaceTrim(string) {
	return $.trim(string.replace(/\s{2,}/g," "));
}

// Error Message
function errMsg(msg) {
	$("#msg").css("color", "red");
	$("#msg").html("&#x2718; - "+msg);
}

// Okay Message
function okMsg(msg) {
	$("#msg").css("color", "green");
	$("#msg").html("&#x2714; - "+msg);
}