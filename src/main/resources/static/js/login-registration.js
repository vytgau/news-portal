/****************************************************
 * ************* Switch to login form ***************
 ****************************************************/
function switchToLoginForm() {
    $('#login-form').css('display', 'block');
    $('#register-form').css('display', 'none');

    $( "#login-form-link").addClass('card-link-active').removeClass('card-link-inactive');
    $( "#registration-form-link").addClass('card-link-inactive').removeClass('card-link-active');
}


/****************************************************
 * ********** Switch to registration form ***********
 ****************************************************/
function switchToRegistrationForm() {
    $('#login-form').css('display', 'none');
    $('#register-form').css('display', 'block');

    $( "#login-form-link").addClass('card-link-inactive').removeClass('card-link-active');
    $( "#registration-form-link").addClass('card-link-active').removeClass('card-link-inactive');
}


/****************************************************
 ** Checks if password and confirm password match  **
 ****************************************************/
function keyupEvent() {
    var pass    =   $('#reg-password').val();
    var cpass   =   $('#reg-confirm-password').val();
    if(pass!=cpass){
        $('#indicator').attr({class:'red'});
        $('#register-submit').attr({disabled:true});
    }
    else{
        $('#indicator').attr({class:'green'});
        $('#register-submit').attr({disabled:false});
    }
}

//clicks login link
$( "#login-form-link" ).click(switchToLoginForm);

//clicks registration link
$( "#registration-form-link" ).click(switchToRegistrationForm);

//events for each entered character inside password or confirm password fields
$('#reg-password').keyup(keyupEvent);
$('#reg-confirm-password').keyup(keyupEvent);