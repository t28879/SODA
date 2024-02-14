$("#checkEssential").on("focus", function() {
    $("#checkError").hide();
});

$('#newSignUpEmail').on('focus', function() {
    $('#newSignUpEmail').css('borderColor', '#333');
    $('#errorEmail').css('display', 'none');
});

$('#newSignUpName').on('focus', function() {
    $('#newSignUpName').css('borderColor', '#333');
    $('#errorName').css('display', 'none');
});

$('#newSignUpPw').on('focus', function() {
    $('#newSignUpPw').css('borderColor', '#333');
    $('#errorPw').css('display', 'none');
});

$('#newSignUpRePw').on('focus', function() {
    $('#newSignUpRePw').css('borderColor', '#333');
    $('#errorRePw').css('display', 'none');
});

document.getElementById("newSignUpForm").addEventListener("submit", function(event) {
	var signUpEmail = document.getElementById("newSignUpEmail").value;
	var signUpName = document.getElementById("newSignUpName").value;
	var signUpPw = document.getElementById("newSignUpPw").value;
	var signUpRePw = document.getElementById("newSignUpRePw").value;
	var checkBox = document.getElementById("checkEssential");
	
	if(signUpEmail.trim()===""||signUpName.trim()===""||signUpPw.trim()===""||signUpRePw.trim()===""||signUpPw!=signUpRePw||!checkBox.checked){
		event.preventDefault();
		
		if(signUpEmail.trim()===""){
			document.getElementById("errorEmail").style.display = "block";
			document.getElementById("newSignUpEmail").style.borderColor = "#ff6b6b";
		} else if(signUpEmail.trim() != ""){
			document.getElementById("errorEmail").style.display = "none";
			document.getElementById("newSignUpEmail").style.borderColor = "#ddd";
		}
		if(signUpName.trim()===""){
			document.getElementById("errorName").style.display = "block";
			document.getElementById("newSignUpName").style.borderColor = "#ff6b6b";
		} else if(signUpName.trim() != ""){
			document.getElementById("errorName").style.display = "none";
			document.getElementById("newSignUpName").style.borderColor = "#ddd";
		}
		if(signUpPw.trim()===""){
			document.getElementById("errorPw").style.display = "block";
			document.getElementById("newSignUpPw").style.borderColor = "#ff6b6b";
		} else if(signUpPw.trim() != ""){
			document.getElementById("errorPw").style.display = "none";
			document.getElementById("newSignUpPw").style.borderColor = "#ddd";
		}
		if(signUpRePw.trim()===""){
			document.getElementById("errorRePw").style.display = "block";
			document.getElementById("newSignUpRePw").style.borderColor = "#ff6b6b";
			document.getElementById("errorRePw").textContent = "비밀번호를 입력해주세요."
		} else if(signUpPw != signUpRePw){
			document.getElementById("errorRePw").style.display = "block";
			document.getElementById("newSignUpRePw").style.borderColor = "#ff6b6b";
			document.getElementById("errorRePw").textContent = "비밀번호가 일치하지 않습니다.";
		} else if(signUpRePw.trim() != ""){
			document.getElementById("errorRePw").style.display = "none";
			document.getElementById("newSignUpRePw").style.borderColor = "#ddd";
		}
		if(!checkBox.checked){
			document.getElementById("checkError").style.display = "block";
		} else{
			document.getElementById("checkError").style.display = "none";
		}
	}
});

