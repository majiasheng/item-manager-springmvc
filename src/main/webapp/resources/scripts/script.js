/**
 * 
 */
function validateRegistration() {
	var errors = [];
	
	if(document.getElementById("additem-form").month.value=="month") {
		errors.push("Select a month");
	}
	if(document.getElementById("additem-form").day.value=="day") {
		errors.push("Select a day");		
	}
	if(document.getElementById("additem-form").year.value=="year") {
		errors.push("Select a year");
	}
	
	if(errors.length != 0) {
		alert(errors.join("\n"));
	}
	
}