function validateLogin() {
	var ok = true;
	var p = document.getElementById("username").value;
	if (p == "") {
		alert("Invalid username!");
		document.getElementById("usernameErr").innerHTML = "*";
		ok = false;
	}
	if (!ok)
		return false;
	p = document.getElementById("password").value;
	if (p == "") {
		alert("Incorrect password!");
		document.getElementById("passwordErr").innerHTML = "*";
		ok = false;
	}
	return ok;
}