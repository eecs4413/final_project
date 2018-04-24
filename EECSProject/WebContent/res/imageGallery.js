var slideIndex = 0;

function plusDivs(n) {
	showDivs(n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  
  
  //show first set of books
  for (i = 0; i < 7; i++) {
	  
	  if(slideIndex > x.length-1){
		  slideIndex = 0;
	  }
	  
     x[slideIndex].style.display = "inline";
     slideIndex++;
  }
  
  //hide next set of books
 
  if(slideIndex > x.length-1){
	  slideIndex = 0;
  }
	  
  
  
     x[slideIndex].style.display = "none";
     x[slideIndex+1].style.display = "none";


  
  
  
  
  //x[slideIndex-1].style.display = "block";  
}