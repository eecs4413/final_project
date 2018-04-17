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

function doSimpleAjax(address) {
	var request = new XMLHttpRequest();
	var data = '';
	/* add your code here to grab all parameters from form */

	var searchBar = document.getElementById("searchBar").value;

	data += "searchBar=" + searchBar;

	request.onreadystatechange = function() {
		handler(request);
	};

	request.open("GET", (address + "?" + data), true);
	request.onreadystatechange = function() {
		handler(request);
	};
	request.send(null);
}

function handler(request) {
	//var target = document.getElementById("ajaxResult");
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("ajaxResult");
		target.innerHTML = request.responseText;
	}
}
