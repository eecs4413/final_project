document.addEventListener('DOMContentLoaded', getRatings);
const starsTotal = 5;

function getRatings(){
	const starPercentage = (rating / starsTotal) * 100;
	
	const starPercentageRounded = '${Math.round(starPercentage / 10) * 10}%';
	
	document.getElementById();
}