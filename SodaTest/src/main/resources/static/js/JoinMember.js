$('#joinComName').on('focus', function() {
    $('#joinComName').css('borderColor', '#333');
    $('#errorJoin').css('display', 'none');
    $('#findComText').css('display', 'none');
});
$('#j_checkEssential').on('focus',function(){
	$('#j_checkError').css('display','none');
});
$('#joinSignUpEmail').on('focus',function(){
	$('#joinSignUpEmail').css('borderColor','#333');
	$('#jErrorEmail').css('display','none');
});
$('#joinSignUpName').on('focus',function(){
	$('#joinSignUpName').css('borderColor','#333');
	$('#jErrorName').css('display','none');
});
$('#joinSignUpPw').on('focus',function(){
	$('#joinSignUpPw').css('borderColor','#333');
	$('#jErrorPw').css('display','none');
});
$('#joinSignUpRePw').on('focus',function(){
	$('#joinSignUpRePw').css('borderColor','#333');
	$('#jErrorRePw').css('display','none');
});
document.getElementById("joinSignUpForm").addEventListener("submit", function(event){
	var jSignUpEmail = document.getElementById("joinSignUpEmail").value;
	var jSignUpName = document.getElementById("joinSignUpName").value;
	var jSignUpPw = document.getElementById("joinSignUpPw").value;
	var jSignUpRePw = document.getElementById("joinSignUpRePw").value;
	var checkBox = document.getElementById("j_checkEssential");
	
	if(jSignUpEmail.trim()===""||jSignUpName.trim()===""||jSignUpPw.trim()===""||jSignUpRePw.trim()===""||jSignUpPw != jSignUpRePw||!checkBox.checked){
		event.preventDefault();
		if(jSignUpEmail.trim()===""){
			document.getElementById("jErrorEmail").style.display = "block";
			document.getElementById("joinSignUpEmail").style.borderColor = "#ff6b6b";
		} else if(jSignUpEmail.trim() != ""){
			document.getElementById("jErrorEmail").style.display = "none";
			document.getElementById("joinSignUpEmail").style.borderColor = "#ddd";
		}
		if(jSignUpName.trim()===""){
			document.getElementById("jErrorName").style.display = "block";
			document.getElementById("joinSignUpName").style.borderColor = "#ff6b6b";
		} else if(jSignUpName.trim() != ""){
			document.getElementById("jErrorName").style.display = "none";
			document.getElementById("joinSignUpName").style.borderColor = "#ddd";
		}
		if(jSignUpPw.trim()===""){
			document.getElementById("jErrorPw").style.display = "block";
			document.getElementById("joinSignUpPw").style.borderColor = "#ff6b6b";
		} else if(jSignUpPw.trim() != ""){
			document.getElementById("jErrorPw").style.display = "none";
			document.getElementById("joinSignUpPw").style.borderColor = "#ddd";
		}
		if(jSignUpRePw.trim()===""){
			document.getElementById("jErrorRePw").style.display = "block";
			document.getElementById("joinSignUpRePw").style.borderColor = "#ff6b6b";
			document.getElementById("jErrorRePw").textContent = "비밀번호를 입력해주세요."
		} else if(jSignUpPw != jSignUpRePw){
			document.getElementById("jErrorRePw").style.display = "block";
			document.getElementById("newSignUpRePw").style.borderColor = "#ff6b6b";
			document.getElementById("jErrorRePw").textContent = "비밀번호가 일치하지 않습니다.";
		} else if(jSignUpRePw.trim() != ""){
			document.getElementById("jErrorRePw").style.display = "none";
			document.getElementById("joinSignUpRePw").style.borderColor = "#ddd";
		}
		if(!checkBox.checked){
			document.getElementById("j_checkError").style.display = "block";
		} else{
			document.getElementById("j_checkError").style.display = "none";
		}
	}
});