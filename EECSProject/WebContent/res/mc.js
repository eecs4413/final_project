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

function validateRegisterAccount() {
	var ok = true;
	var error = "";

	var p = document.getElementById("fname").value;
	if (p == null || p == "") {
		error = error + "First Name Cannot Be Empty";
		ok = false;
	}

	p = document.getElementById("lname").value;
	if (p == null || p == "") {
		error = error + "\nLast Name Cannot Be Empty";
		ok = false;
	}

	p = document.getElementById("email").value;
	if (!validateEmail(p)) {
		error = error + "\nInvalid Email";
		ok = false;
	}

	p = document.getElementById("password").value;
	if (p == null || p == "") {
		error = error + "\nPassword Cannot Be Empty";
		ok = false;
	}

	var x = p;

	p = document.getElementById("repassword").value;
	if (p == null || p == "" || p != x) {
		error = error + "\nPassword Missmatch";
		ok = false;
	}

	if (!ok) {
		alert(error);
	}

	return ok;
}

function validateEmail(email) {
	var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	return re.test(email);

}

function validateRegisterAccountAddress() {
	var ok = true;
	var error = "";

	var p = document.getElementById("street").value;
	if (p == null || p == "") {
		error = error + "Street Name Cannot Be Empty";
		ok = false;
	}

	p = document.getElementById("city").value;
	if (p == null || p == "") {
		error = error + "\nCity Name Cannot Be Empty";
		ok = false;
	}
	p = document.getElementById("city").value;
	if (p == null || p == "") {
		error = error + "\nCity Name Cannot Be Empty";
		ok = false;
	}
	p = document.getElementById("province").value;
	if (p == null || p == "") {
		error = error + "\nProvince Name Cannot Be Empty";
		ok = false;
	}

	p = document.getElementById("country").value;
	if (p == null || p == "") {
		error = error + "\nCountry Name Cannot Be Empty";
		ok = false;
	}

	p = document.getElementById("zip").value;
	if (!validateZip(p)) {
		error = error + "\nInvalid Zip";
		ok = false;
	}
	p = document.getElementById("phone").value;
	if (!validatephone(p)) {
		error = error + "\nInvalid Phone number";
		ok = false;
	}

	if (!ok) {
		alert(error);
	}

	return ok;
}

function validatePurchaseForm() {
	var ok = true;
	var error = "";

	var p = document.getElementById("street").value;
	if (p == null || p == "") {
		error = error + "Street Name Cannot Be Empty";
		ok = false;
	}

	p = document.getElementById("city").value;
	if (p == null || p == "") {
		error = error + "\nCity Name Cannot Be Empty";
		ok = false;
	}
	p = document.getElementById("city").value;
	if (p == null || p == "") {
		error = error + "\nCity Name Cannot Be Empty";
		ok = false;
	}
	p = document.getElementById("province").value;
	if (p == null || p == "") {
		error = error + "\nProvince Name Cannot Be Empty";
		ok = false;
	}

	p = document.getElementById("country").value;
	if (p == null || p == "") {
		error = error + "\nCountry Name Cannot Be Empty";
		ok = false;
	}

	p = document.getElementById("zip").value;
	if (p == null || p == "") {
		error = error + "\nInvalid Zip";
		ok = false;
	}
	p = document.getElementById("phone").value;
	if (p == null || p == "") {
		error = error + "\nInvalid Phone number";
		ok = false;
	}

	p = document.getElementById("fname").value;
	if (p == null || p == "") {
		error = error + "\nFirst Name cannot be empty";
		ok = false;
	}
	p = document.getElementById("lname").value;
	if (p == null || p == "") {
		error = error + "\nLast Name cannot be empty";
		ok = false;
	}
	
	p = document.getElementById("crednum").value;
	if (p == null || p == "") {
		error = error + "\nCredit Number cannot be empty";
		ok = false;
	}
	
	p = document.getElementById("expMonth").value;
	if (p == null || p == "") {
		error = error + "\nExpiry Month cannot be empty";
		ok = false;
	}
	
	p = document.getElementById("expYear").value;
	if (p == null || p == "") {
		error = error + "\nExpiry Year cannot be empty";
		ok = false;
	}
	
	if (!ok) {
		alert(error);
	}

	return ok;
}

function validatephone(phone) {
	var phoneno = /^\(?([0-9]{3})\)?[-]?([0-9]{3})[-]?([0-9]{4})$/;
	return phoneno.test(phone);
}

function validateZip(zip) {
	var re = /^[A-Za-z]\d[A-Za-z][ ]?\d[A-Za-z]\d$/;

	return re.test(zip);

}

function doSimpleAjax(address) {
	var request = new XMLHttpRequest();
	var data = '';
	/* add your code here to grab all parameters from form */

	var searchBar = document.getElementById("searchBar").value;

	data += "searchBar=" + searchBar;
	
	alert(address);
	//alert((address + "?" + data));
	alert(address + "?" + data);
	request.open("GET", address + "?" + data, true);
	request.onreadystatechange = function() {
		handler(request);
	};
	
	request.send();
}

function handler(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var target = document.getElementById("demo");
		alert(request.responseText);
		target.innerHTML = request.responseText;
	}
	
	if (request.status == 200){
		alert("status?");
	}
	
	if (request.readyState == 4){
		//alert("work?");
	}
}

function addCartToast(){
	var x = document.getElementById("snackbar");
    x.className = "show";
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}

function loadSearch(){
	
	var searchBar = document.getElementById("searchBar").value;
	var searchBtn = document.getElementById("searchButton").value;
	//alert(searchBar);
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("page").innerHTML = this.responseText;
	     	alert(this.responseText)
	    }
	        
	  };
	  //alert('SearchResults.jspx?searchBar=' +searchBar + "&searchButton=" + searchBtn);
	  xhttp.open("GET", 'Search?searchBar=' +searchBar + "&searchButton=" + searchBtn, true);
	  //xhttp.open("GET", 'SearchResults.jspx?searchBar=' +searchBar + "&searchButton=" + searchBtn, true);
	  xhttp.send(null);
	
}

function loadSearchFilter(){
	
	var searchBar = document.getElementById("searchBar").value;
	var searchBtn = document.getElementById("searchButton").value;
	//alert(searchBar);
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("filter").innerHTML = this.responseText;
	     	alert(this.responseText)
	    }
	        
	  };
	  //alert('SearchResults.jspx?searchBar=' +searchBar + "&searchButton=" + searchBtn);
	  xhttp.open("GET", 'SearchFilter?searchBar=' +searchBar + "&searchButton=" + searchBtn, true);
	  //xhttp.open("GET", 'SearchResults.jspx?searchBar=' +searchBar + "&searchButton=" + searchBtn, true);
	  xhttp.send(null);
	
}

function loadCategorySearch(category){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("page").innerHTML = this.responseText;
	     	//alert(this.responseText)
	    }      
	  };
	  
	  alert('Search?searchBar=' +searchBar + "&" + category + "=");
	  xhttp.open("GET", 'Search?searchBar=' +searchBar + "&" + category + "=", true);
	  xhttp.send(null);
}

function filterSearchByPrice(filterType){
	
	var query = "";
	
	if(filterType == "u15"){
		
		query += "&u15=true"
		
	} else if(filterType == "r15_r25"){
		
		query += "&r15_r25=true"
		
	} else if(filterType == "r25_r50"){
		
		query += "&r25_r50=true"
		
	} else if(filterType == "o50"){
		
		query += "&o50=true"
		
	}
	
	var searchBar = document.getElementById("searchBar").value;
	var searchBtn = document.getElementById("searchButton").value;
	alert(query);
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     document.getElementById("page").innerHTML = this.responseText;
	     	//alert(this.responseText)
	    }      
	  };

	  xhttp.open("GET", 'Search?searchBar=' +searchBar + query, true);
	  xhttp.send(null);
	
}