document.getElementById('profileSetting_btn').addEventListener('click',function(){
	document.querySelector('.MySettingWrap').style.display = "block";
});
document.getElementById('MySettingClose').addEventListener('click',function(){
	location.reload();
});
document.getElementById('editorProfileBtn').addEventListener('click',function(){
    var editProfile = document.getElementById('editorProfile');
    if (editProfile.style.display === 'none') {
        editProfile.style.display = 'block';
    } else {
        editProfile.style.display = 'none';
    }
});
document.addEventListener('click', function(e) {
    var editProfile = document.getElementById('editorProfile');
    var editBtn = document.getElementById('editorProfileBtn');
    var target = e.target;

    if (target !== editProfile && target !== editBtn) {
        editProfile.style.display = 'none';
    }
});
document.getElementById('accountSettingBtn').addEventListener('click',function(){
	document.querySelector('#adSPColorDot').style.backgroundColor = "#c0c0c0";
	document.querySelector('#adSPColor').style.color = "#c0c0c0";
	document.querySelector('#acSPColorDot').style.backgroundColor = "#50b5bf";
	document.querySelector('#acSPColor').style.color = "#50b5bf";
	document.querySelector('#scSPColorDot').style.backgroundColor = "#c0c0c0";
	document.querySelector('#scSPColor').style.color = "#c0c0c0";
	document.querySelector('.accountSPMain').style.display = "block";
	document.querySelector('.alert_displayMain').style.display = "none";
	document.querySelector('.securitySPMain').style.display = "none";
});
document.getElementById('alert_displaySettingBtn').addEventListener('click',function(){
	document.querySelector('#acSPColorDot').style.backgroundColor = "#c0c0c0";
	document.querySelector('#acSPColor').style.color = "#c0c0c0";
	document.querySelector('#scSPColorDot').style.backgroundColor = "#c0c0c0";
	document.querySelector('#scSPColor').style.color = "#c0c0c0";
	document.querySelector('#adSPColorDot').style.backgroundColor = "#50b5bf";
	document.querySelector('#adSPColor').style.color = "#50b5bf";
	document.querySelector('.accountSPMain').style.display = "none";
	document.querySelector('.alert_displayMain').style.display = "block";
	document.querySelector('.securitySPMain').style.display = "none";
});
document.getElementById('securitySettingBtn').addEventListener('click',function(){
	document.querySelector('#acSPColorDot').style.backgroundColor = "#c0c0c0";
	document.querySelector('#acSPColor').style.color = "#c0c0c0";
	document.querySelector('#adSPColorDot').style.backgroundColor = "#c0c0c0";
	document.querySelector('#adSPColor').style.color = "#c0c0c0";
	document.querySelector('#scSPColorDot').style.backgroundColor = "#50b5bf";
	document.querySelector('#scSPColor').style.color = "#50b5bf";
	document.querySelector('.accountSPMain').style.display = "none";
	document.querySelector('.alert_displayMain').style.display = "none";
	document.querySelector('.securitySPMain').style.display = "block";
});
document.getElementById('changeNameBtn').addEventListener('click',function(){
	document.querySelector('#MySPUserName .read-mode').style.display = "none";
	document.querySelector('#MySPUserName .editor-mode').style.display = "block";
});
document.getElementById('changeNameCancel').addEventListener('click',function(){
	document.querySelector('#MySPUserName .read-mode').style.display = "block";
	document.querySelector('#MySPUserName .editor-mode').style.display = "none";
});
document.getElementById('changeDepartBtn').addEventListener('click',function(){
	document.querySelector('#MySPDepartName .read-mode').style.display = "none";
	document.querySelector('#MySPDepartName .editor-mode').style.display = "block";
});
document.getElementById('changeDepartCancel').addEventListener('click',function(){
	document.querySelector('#MySPDepartName .read-mode').style.display = "block";
	document.querySelector('#MySPDepartName .editor-mode').style.display = "none";
});
document.getElementById('changeJobBtn').addEventListener('click',function(){
	document.querySelector('#MySPJobName .read-mode').style.display = "none";
	document.querySelector('#MySPJobName .editor-mode').style.display = "block";
});
document.getElementById('changeJobCancel').addEventListener('click',function(){
	document.querySelector('#MySPJobName .read-mode').style.display = "block";
	document.querySelector('#MySPJobName .editor-mode').style.display = "none";
});
document.getElementById('changePhoneBtn').addEventListener('click',function(){
	document.querySelector('#MySPPhoneNumber .read-mode').style.display = "none";
	document.querySelector('#MySPPhoneNumber .editor-mode').style.display = "block";
});
document.getElementById('changePhoneCancel').addEventListener('click',function(){
	document.querySelector('#MySPPhoneNumber .read-mode').style.display = "block";
	document.querySelector('#MySPPhoneNumber .editor-mode').style.display = "none";
});
document.getElementById('changePasswordBtn').addEventListener('click',function(event){
	event.preventDefault();
	document.querySelector('#MySPUserPassword .read-mode').style.display = "none";
	document.querySelector('#MySPUserPassword .editor-mode').style.display = "block";
});
document.getElementById('changePwCancel').addEventListener('click',function(){
	document.querySelector('#MySPUserPassword .read-mode').style.display = "block";
	document.querySelector('#MySPUserPassword .editor-mode').style.display = "none";
});
document.getElementById('darkBtn').addEventListener('click',function(){
	document.getElementById('darkThemeBtn1').style.display = "block";
	document.getElementById('darkThemeBtn2').style.display = "none";
	document.getElementById('lightThemeBtn1').style.display = "none";
	document.getElementById('lightThemeBtn2').style.display = "block";
});
document.getElementById('lightBtn').addEventListener('click',function(){
	document.getElementById('darkThemeBtn1').style.display = "none";
	document.getElementById('darkThemeBtn2').style.display = "block";
	document.getElementById('lightThemeBtn1').style.display = "block";
	document.getElementById('lightThemeBtn2').style.display = "none";
});
document.getElementById('alertProjectBtn1').addEventListener('click',function(){
	document.getElementById('alertProjectBtn1').style.display = "none";
	document.getElementById('alertProjectBtn2').style.display = "block";
});
document.getElementById('alertProjectBtn2').addEventListener('click',function(){
	document.getElementById('alertProjectBtn1').style.display = "block";
	document.getElementById('alertProjectBtn2').style.display = "none";
});
document.getElementById('alertChatBtn1').addEventListener('click',function(){
	document.getElementById('alertChatBtn1').style.display = "none";
	document.getElementById('alertChatBtn2').style.display = "block";
});
document.getElementById('alertChatBtn2').addEventListener('click',function(){
	document.getElementById('alertChatBtn1').style.display = "block";
	document.getElementById('alertChatBtn2').style.display = "none";
});
document.getElementById('RandomThemeBtn').addEventListener('click',function(){
	document.getElementById('RandomThemeBtn1').style.display = "block";
	document.getElementById('RandomThemeBtn2').style.display = "none";
	document.getElementById('WhiteThemeBtn1').style.display = "none";
	document.getElementById('WhiteThemeBtn2').style.display = "block";
});
document.getElementById('WhiteThemeBtn').addEventListener('click',function(){
	document.getElementById('RandomThemeBtn1').style.display = "none";
	document.getElementById('RandomThemeBtn2').style.display = "block";
	document.getElementById('WhiteThemeBtn1').style.display = "block";
	document.getElementById('WhiteThemeBtn2').style.display = "none";
});
document.getElementById('FavoriteOnBtn').addEventListener('click',function(){
	document.getElementById('FavoriteOnBtn1').style.display = "block";
	document.getElementById('FavoriteOnBtn2').style.display = "none";
	document.getElementById('FavoriteOffBtn1').style.display = "none";
	document.getElementById('FavoriteOffBtn2').style.display = "block";
});
document.getElementById('FavoriteOffBtn').addEventListener('click',function(){
	document.getElementById('FavoriteOnBtn1').style.display = "none";
	document.getElementById('FavoriteOnBtn2').style.display = "block";
	document.getElementById('FavoriteOffBtn1').style.display = "block";
	document.getElementById('FavoriteOffBtn2').style.display = "none";
});
//
let chatVisible = false;
let alertVisible = false;
let profileVisible = false;

const btn = document.getElementById('btn2');
const chat = document.getElementById('chatLayer');
const btn_close = document.getElementById('chatClose');
btn.onclick = function(event) {
	event.stopPropagation();
	chatVisible = !chatVisible;
	alertVisible = false;
	profileVisible = false;
	updateVisibility();
};

btn_close.onclick = function() {
	chatVisible = false;
	updateVisibility();
};

const alarmOn = document.getElementById('alarmOn');
const alarmOff = document.getElementById('alarmOff');
const exOn = document.getElementById('exOn');
const exOff = document.getElementById('exOff');

alarmOn.onclick = function(event) {
	alarmOn.style.display = 'none';
	alarmOff.style.display = 'block';
}

alarmOff.onclick = function(event) {
	alarmOff.style.display = 'none';
	alarmOn.style.display = 'block';
}

alarmOn.addEventListener('mouseover', function(event) {
	exOn.style.display = 'block';
});

alarmOn.addEventListener('mouseout', function(event) {
	exOn.style.display = 'none';
});

alarmOff.addEventListener('mouseover', function(event) {
	exOff.style.display = 'block';
});

alarmOff.addEventListener('mouseout', function(event) {
	exOff.style.display = 'none';
});

const alertbtn = document.getElementById('btn3');
const alert = document.getElementById('alertLayer');
const alertbtn_close = document.getElementById('alertClose');
alertbtn.onclick = function(event) {
	event.stopPropagation();
	alertVisible = !alertVisible;
	chatVisible = false;
	profileVisible = false;
	updateVisibility();
};

alertbtn_close.onclick = function() {
	alertVisible = false;
	updateVisibility();
};

const profile = document.getElementById('profileLayer');
const btn4 = document.getElementById('btn4');

btn4.onclick = function(event) {
	event.stopPropagation();
	profileVisible = !profileVisible;
	chatVisible = false;
	alertVisible = false;
	updateVisibility();
}

// 하나의 window.onclick 이벤트 핸들러에서 모든 처리를 수행
window.onclick = function(event) {
	// 클릭된 요소가 chat 버튼이거나 chat 영역 내부의 요소인 경우 chat 숨김
	if (!event.target.closest('#chatLayer') && chatVisible) {
		chatVisible = false;
		updateVisibility();
	}

	// 클릭된 요소가 alert 버튼이거나 alert 영역 내부의 요소인 경우 alert 숨김
	if (!event.target.closest('#alertLayer') && alertVisible) {
		alertVisible = false;
		updateVisibility();
	}

	// 클릭된 요소가 profile 버튼이거나 profile 영역 내부의 요소인 경우 profile 숨김
	if (!event.target.closest('#profileLayer') && profileVisible) {
		profileVisible = false;
		updateVisibility();
	}
};

function updateVisibility() {
	// chat 영역 업데이트
	chat.style.display = chatVisible ? 'block' : 'none';

	// alert 영역 업데이트
	alert.style.display = alertVisible ? 'block' : 'none';

	// profile 영역 업데이트
	profile.style.display = profileVisible ? 'block' : 'none';
}

$(document).ready(function() {
	const menuItems = $('.scroll li');
	const boardContainer = $('.main-container');
	menuItems.each(function(index, item) {
		$(item).on('click', function() {
			$('#background_car').hide();
			$('.좌측사이드바').css('background-color', 'black');
			$.ajax({
				url: `sidebarfunction${index + 1}.jsp`,
				method: 'POST',
				data: {userNum: userNum},
				success: function(data) {
					boardContainer.html(data);

				},
				error: function(error) {
					console.error('Error fetching content:', error);
				}
			});

		});
	});
	
	var $list3 = $('.list-3');

    $list3.children(':nth-child(2)').on('click', function () {
        window.open("sodaadmin.jsp?userNum="+userNum);
    });
});