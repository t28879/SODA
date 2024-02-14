$('#comName').on('focus', function() {
    $('#comName').css('borderColor', '#333');
    $('#errorCompany').css('display', 'none');
});

$('#comURL').on('focus', function() {
    $('#comURL').css('borderColor', '#333');
    $('#errorURL').css('display', 'none');
});



document.getElementById("CreateCompanyForm").addEventListener("submit", function(event){
	var comName = document.getElementById("comName").value;
	var comURL = document.getElementById("comURL").value;
	if(comName.trim()===""||comURL.trim()===""){
		event.preventDefault();
		if(comName.trim()===""){
			document.getElementById("errorCompany").style.display = "block";
			document.getElementById("comName").style.borderColor = "#ff6b6b";
		} else if(comName.trim()!=""){
			document.getElementById("errorCompany").style.display = "none";
			document.getElementById("comName").style.borderColor = "#ddd";
		}
		if(comURL.trim()===""){
			document.getElementById("errorURL").style.display = "block";
			document.getElementById("comURL").style.borderColor = "#ff6b6b";
		} else if(comURL.trim()!=""){
			document.getElementById("errorURL").style.display = "none";
			document.getElementById("comURL").style.borderColor = "#ddd";
		}
	}
	
});
