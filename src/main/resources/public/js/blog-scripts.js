$(function() {
    $('#messages li').click(function() {
        $(this).fadeOut();
    });
    setTimeout(function() {
        $('#messages li.info').fadeOut();
    }, 3000);
});

function validate() {
  var elements = document.getElementsByClassName("formError");
  var loginValue = document.getElementById("username").value;
  var loginLength = loginValue.length;
  var passwordValue = document.getElementById("password").value;
  var passwordLength = passwordValue.length;

  if ((loginLength < 2 || loginLength > 30) && (loginLength > 0)) {
    elements[0].innerHTML = "Username size should be in the range [2...30]";
  } else {
    elements[0].innerHTML = "";
  }
   if ((passwordLength < 3 || passwordLength > 50) && (passwordLength > 0)) {
    elements[1].innerHTML = "Size must be between 3 and 50";
  } else {
    elements[1].innerHTML = "";
  }
}