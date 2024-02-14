document.getElementById("find_pw").addEventListener("click", function () {
	document.querySelector(".loginWrap").style.display = "none";
    document.querySelector(".findPwPage").style.display = "block";
});
document.getElementById("userId").addEventListener("focus", function () {
	document.querySelector("#userId").style.borderColor = "#333";
    document.querySelector(".error_id_text").style.display = "none";
});
document.getElementById("userPw").addEventListener("focus", function () {
	document.querySelector("#userPw").style.borderColor = "#333";
    document.querySelector(".error_pw_text").style.display = "none";
});
document.getElementById("inputPw").addEventListener("focus", function(){
	document.getElementById("inputPw").style.borderColor = "#ddd";
	document.querySelector(".errorText").style.display = "none";
});