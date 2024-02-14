$(document).ready(function() {
	$("#logoutForm").submit(function(event) {
		event.preventDefault();

		$.ajax({
			url: '/SODA/Logout',
			method: 'POST',
			success: function(message) {
				window.alert(message);
				window.location.href = '/SODA/Home';
			},
			error: function(error) {
				console.error('로그아웃 에러:', error);
			}
		});
	});
});
$(document).ready(function() {
	$("#kakaoLogoutForm").submit(function(event) {
		event.preventDefault();

		$.ajax({
			url: '/SODA/logout',
			method: 'POST',
			success: function(message) {
				window.alert(message);
				window.location.href = '/SODA/Home';
			},
			error: function(error) {
				console.error('로그아웃 에러:', error);
			}
		});
	});
});
$(document).on('click', '#changeJobOK', function(e) {
	e.preventDefault();
	var jobName = $('#edit_jobName').val();
	$.ajax({
		url: '/editJob',
		method: 'POST',
		data: {
			jobName: jobName,
		},
		success: function(response) {
			if (response.status === 'success') {
				$('#SPJob').text(jobName);
				$('#MySPJobName .read-mode').css('display', 'block');
				$('#MySPJobName .editor-mode').css('display', 'none');
				$('#jobAlert').show();
				setTimeout(function() {
					$('#jobAlert').fadeOut(1500);
				}, 2000);
			} else {
				console.error(response.message);
			}
		},
		error: function(xhr, status, error) {
		}
	});
});
$(document).on('click', '#changeNameOK', function(e) {
	e.preventDefault();
	var userName = $('#edit_userName').val();
	$.ajax({
		url: '/editName',
		method: 'POST',
		data: {
			userName: userName
		},
		success: function(response) {
			if (response.status === 'success') {
				$('#SPName').text(userName);
				$('#MySPUserName .read-mode').css('display', 'block');
				$('#MySPUserName .editor-mode').css('display', 'none');
				$('#nameAlert').show();
				setTimeout(function() {
					$('#nameAlert').fadeOut(1500);
				}, 2000);
			} else {
				console.error(response.message);
			}
		},
		error: function(xhr, status, error) {
			console.log(error)
		}
	});
});
$(document).on('click', '#changePhoneOK', function(e) {
	e.preventDefault();
	var phoneNumber = $('#edit_phoneNumber').val();
	$.ajax({
		url: '/editPhone',
		method: 'POST',
		data: {
			phoneNumber: phoneNumber,
		},
		success: function(response) {
			if (response.status === 'success') {
				$('#SPPhone').text(phoneNumber);
				$('#MySPPhoneNumber .read-mode').css('display', 'block');
				$('#MySPPhoneNumber .editor-mode').css('display', 'none');
				$('#phoneAlert').show();
				setTimeout(function() {
					$('#phoneAlert').fadeOut(1500);
				}, 2000);
			} else {
				console.log(response.message);
			}
		},
		error: function(xhr, status, error) {
		}
	});
});
$(document).on('click', '#changePWOK', function(e) {
	e.preventDefault();
	var currentPW = $('#currentPW').val();
	var newPW = $('#newPW').val();
	$.ajax({
		url: '/editPW',
		method: 'POST',
		data: {
			currentPW: currentPW,
			newPW: newPW
		},
		success: function(response) {
			if (response.status === "success") {
				$('#MySPUserPassword .read-mode').css(
					'display', 'block');
				$('#MySPUserPassword .editor-mode').css(
					'display', 'none');
				$('#pwAlert').show();
				setTimeout(function() {
					$('#pwAlert').fadeOut(1500);
				}, 2000);
			} else if (response.status === "fail") {
				$('#MySPUserPassword .read-mode').css(
					'display', 'block');
				$('#MySPUserPassword .editor-mode').css(
					'display', 'none');
				$('#pwFailAlert').show();
				setTimeout(function() {
					$('#pwFailAlert').fadeOut(1500);
				}, 2000);
			}
		},
		error: function(xhr, status, error) {
			console.log(error);
		}
	});
});
$(document).ready(function() {
	$.ajax({
		url: '/editCom',
		method: 'POST',
		success: function(response) {
			$('#SPCom').html(response);
		},
		error: function(xhr, status, error) {
			console.log(status + ": " + status);
		}
	});
});
$(document).ready(function() {
	var userNum = $('#profileMemId').val();
	console.log('console1=' + userNum);
	$('#uploadProfileImg').change(function() {
		var formData = new FormData();
		formData.append('file', $('#uploadProfileImg')[0].files[0]);
		console.log(formData);
		$.ajax({
			url: '/uploadProfile',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function(response) {
				if (response !== 'failed') {
					var filename = encodeURIComponent($('#uploadProfileImg')[0].files[0].name);
					var newImage = '/upload/upload_' + userNum + '_' + filename;
					console.log("console2=" + userNum);
					updateProfileImg(response);
					console.log("success: ", newImage);
					window.alert("변경되었습니다");
					setTimeout(function() {
						window.location.reload();
					}, 1000);
				} else {
					console.log('업로드 실패');
				}
			},
			error: function(error) {
				console.log(error);
			}
		});
	});
	function updateProfileImg(newImage) {
		console.log('newImgPath: ', newImage);
		$('#profileImg').attr('src', newImage);
	}
});
function redirectToHome() {
	window.location.href = "/SODA_DASHBOARD"; // 초기화면의 JSP 페이지 경로로 변경
}

document.addEventListener('click', function(event) {
	var popupMain = document.getElementById('popupMain'); /*id가 popupMain인 요소를 찾아 할당함 옵션 팝업*/
	var closeButton = document.getElementById('popupCloseButton');
	var searchInput = document.getElementById('searchInput');

	// 클릭한 요소가 팝업 내부가 아니라면 팝업을 닫음
	if (event.target !== popupMain && !popupMain.contains(event.target) && event.target !== searchInput) {
		popupMain.style.display = 'none';
		// 팝업이 닫힐 때 닫기 이미지를 숨기도록 설정
		closeButton.style.display = 'none';
	}

	// 바깥 검색창에서 팝업을 닫았을 때 닫기 이미지를 숨기도록 설정
	if (searchInput.value.trim() === '') {
		closeButton.style.display = 'none';
	}
});
// 환경설정

document.getElementById('profileSetting_btn').addEventListener('click', function() {
	document.querySelector('.MySettingWrap').style.display = "block";
});
document.getElementById('home_img').addEventListener('click', function() {
	document.querySelector('.MySettingWrap').style.display = "block";
});
document.getElementById('MySettingClose').addEventListener('click', function() {
	document.querySelector('.MySettingWrap').style.display = "none";
});
document.getElementById('editorProfileBtn').addEventListener('click', function() {
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
document.getElementById('accountSettingBtn').addEventListener('click', function() {
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
document.getElementById('alert_displaySettingBtn').addEventListener('click', function() {
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
document.getElementById('securitySettingBtn').addEventListener('click', function() {
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
document.getElementById('changeNameBtn').addEventListener('click', function() {
	document.querySelector('#MySPUserName .read-mode').style.display = "none";
	document.querySelector('#MySPUserName .editor-mode').style.display = "block";
});
document.getElementById('changeNameCancel').addEventListener('click', function() {
	document.querySelector('#MySPUserName .read-mode').style.display = "block";
	document.querySelector('#MySPUserName .editor-mode').style.display = "none";
});
document.getElementById('changeDepartBtn').addEventListener('click', function() {
	document.querySelector('#MySPDepartName .read-mode').style.display = "none";
	document.querySelector('#MySPDepartName .editor-mode').style.display = "block";
});
document.getElementById('changeDepartCancel').addEventListener('click', function() {
	document.querySelector('#MySPDepartName .read-mode').style.display = "block";
	document.querySelector('#MySPDepartName .editor-mode').style.display = "none";
});
document.getElementById('changeJobBtn').addEventListener('click', function() {
	document.querySelector('#MySPJobName .read-mode').style.display = "none";
	document.querySelector('#MySPJobName .editor-mode').style.display = "block";
});
document.getElementById('changeJobCancel').addEventListener('click', function() {
	document.querySelector('#MySPJobName .read-mode').style.display = "block";
	document.querySelector('#MySPJobName .editor-mode').style.display = "none";
});
document.getElementById('changePhoneBtn').addEventListener('click', function() {
	document.querySelector('#MySPPhoneNumber .read-mode').style.display = "none";
	document.querySelector('#MySPPhoneNumber .editor-mode').style.display = "block";
});
document.getElementById('changePhoneCancel').addEventListener('click', function() {
	document.querySelector('#MySPPhoneNumber .read-mode').style.display = "block";
	document.querySelector('#MySPPhoneNumber .editor-mode').style.display = "none";
});
document.getElementById('changePasswordBtn').addEventListener('click', function(event) {
	event.preventDefault();
	document.querySelector('#MySPUserPassword .read-mode').style.display = "none";
	document.querySelector('#MySPUserPassword .editor-mode').style.display = "block";
});
document.getElementById('changePwCancel').addEventListener('click', function() {
	document.querySelector('#MySPUserPassword .read-mode').style.display = "block";
	document.querySelector('#MySPUserPassword .editor-mode').style.display = "none";
});
document.getElementById('darkBtn').addEventListener('click', function() {
	document.getElementById('darkThemeBtn1').style.display = "block";
	document.getElementById('darkThemeBtn2').style.display = "none";
	document.getElementById('lightThemeBtn1').style.display = "none";
	document.getElementById('lightThemeBtn2').style.display = "block";
});
document.getElementById('lightBtn').addEventListener('click', function() {
	document.getElementById('darkThemeBtn1').style.display = "none";
	document.getElementById('darkThemeBtn2').style.display = "block";
	document.getElementById('lightThemeBtn1').style.display = "block";
	document.getElementById('lightThemeBtn2').style.display = "none";
});
document.getElementById('alertProjectBtn1').addEventListener('click', function() {
	document.getElementById('alertProjectBtn1').style.display = "none";
	document.getElementById('alertProjectBtn2').style.display = "block";
});
document.getElementById('alertProjectBtn2').addEventListener('click', function() {
	document.getElementById('alertProjectBtn1').style.display = "block";
	document.getElementById('alertProjectBtn2').style.display = "none";
});
document.getElementById('alertChatBtn1').addEventListener('click', function() {
	document.getElementById('alertChatBtn1').style.display = "none";
	document.getElementById('alertChatBtn2').style.display = "block";
});
document.getElementById('alertChatBtn2').addEventListener('click', function() {
	document.getElementById('alertChatBtn1').style.display = "block";
	document.getElementById('alertChatBtn2').style.display = "none";
});
document.getElementById('RandomThemeBtn').addEventListener('click', function() {
	document.getElementById('RandomThemeBtn1').style.display = "block";
	document.getElementById('RandomThemeBtn2').style.display = "none";
	document.getElementById('WhiteThemeBtn1').style.display = "none";
	document.getElementById('WhiteThemeBtn2').style.display = "block";
});
document.getElementById('WhiteThemeBtn').addEventListener('click', function() {
	document.getElementById('RandomThemeBtn1').style.display = "none";
	document.getElementById('RandomThemeBtn2').style.display = "block";
	document.getElementById('WhiteThemeBtn1').style.display = "block";
	document.getElementById('WhiteThemeBtn2').style.display = "none";
});
document.getElementById('FavoriteOnBtn').addEventListener('click', function() {
	document.getElementById('FavoriteOnBtn1').style.display = "block";
	document.getElementById('FavoriteOnBtn2').style.display = "none";
	document.getElementById('FavoriteOffBtn1').style.display = "none";
	document.getElementById('FavoriteOffBtn2').style.display = "block";
});
document.getElementById('FavoriteOffBtn').addEventListener('click', function() {
	document.getElementById('FavoriteOnBtn1').style.display = "none";
	document.getElementById('FavoriteOnBtn2').style.display = "block";
	document.getElementById('FavoriteOffBtn1').style.display = "block";
	document.getElementById('FavoriteOffBtn2').style.display = "none";
});

// 환경설정 끝

document.getElementById('cate-project').addEventListener("click", function() {
	document.querySelector('.hiddenPopup').style.display = "flex";
	document.querySelector('.category-buttons').style.display = "none";
	document.getElementById('hidden-popup-change').textContent = "프로젝트";
});
document.getElementById('cate-post').addEventListener("click", function() {
	document.querySelector('.hiddenPopup').style.display = "flex";
	document.querySelector('.category-buttons').style.display = "none";
	document.getElementById('hidden-popup-change').textContent = "글·댓글";
});
document.getElementById('cate-file').addEventListener("click", function() {
	document.querySelector('.hiddenPopup').style.display = "flex";
	document.querySelector('.category-buttons').style.display = "none";
	document.getElementById('hidden-popup-change').textContent = "파일";
});
document.getElementById('pointX').addEventListener("click", function() {
	document.querySelector('.hiddenPopup').style.display = "none";
	document.querySelector('.category-buttons').style.display = "flex";
	document.getElementById('categoryTitle').textContent = '카테고리';
});
document.getElementById('pointX').addEventListener("mouseover", function() {
	document.getElementById('pointX').src = 'images/HoverClose.png';
});
document.getElementById('pointX').addEventListener("mouseout", function() {
	document.getElementById('pointX').src = 'images/Close.png';
});
document.getElementById('popupCloseButton').addEventListener("click", function() {
	document.getElementById('popupInput').value = '';
});

function togglepopupMain() {
	var popupMain = document.getElementById('popupMain');
	var closeButton = document.getElementById('popupCloseButton');
	var searchInput = document.getElementById('searchInput');
	var popupInput = document.getElementById('PopupInput');

	//document.getElementById('searchInput').addEventListener("click", function(){
	//    document.getElementById('project-joinner').style.display = "none";
	//    document.getElementById('project-input').style.display = "none";
	//});



	if (popupMain.style.display === 'none' || popupMain.style.display === '') {
		popupMain.style.display = 'block';
		// 팝업이 열릴 때 닫기 이미지를 보이도록 설정
		toggleCloseButton();
	} else {
		if (popupInput.value.trim() !== '') {
			// 입력된 내용이 있을 때만 지우기
			popupInput.textContent = '';
		}
		// 팝업이 닫힐 때 닫기 이미지를 숨기도록 설정
		closeButton.style.display = 'none';
		// 팝업을 닫을 때 팝업의 내용을 초기화할 수도 있습니다.
		// 예: document.getElementById('PopupInput').value = '';
	}

	// 바깥 검색창에서 팝업을 닫았을 때 닫기 이미지를 숨기도록 설정
	if (searchInput.value.trim() === '') {
		closeButton.style.display = 'none';
	}
}


function toggleCloseButton() {
	var closeButton = document.getElementById('popupCloseButton');
	var popupInput = document.getElementById('popupInput');

	// 입력된 내용이 있을 때만 팝업 내의 닫기 이미지를 보이도록 설정
	closeButton.style.display = popupInput.value.trim() !== '' ? 'block' : 'none';
}

// 추가된 함수: 카테고리 타이틀 변경
function changeCategoryTitle(title) {
	var categoryTitle = document.getElementById('categoryTitle');
	categoryTitle.textContent = title;
}

// ------------------------------ 좌측 사이드바 스크립트 -------------------------------------
let rotationDegrees1 = {};

function toggleSublistAndRotate1(itemId, arrowId) {
	const item = document.getElementById(itemId);
	const sublist = item.querySelector('.sub_list1');
	if (event.target.id === itemId) {
		sublist.style.display = sublist.style.display === 'none' ? 'block' : 'none';
		rotateArrow1(arrowId);
	}
}
function rotateArrow1(arrowId) {
	if (!rotationDegrees1[arrowId]) {
		rotationDegrees1[arrowId] = 0;
	}
	rotationDegrees1[arrowId] += 180;
	const image = document.getElementById(arrowId);
	image.style.transform = `rotate(${rotationDegrees1[arrowId]}deg)`;
}
document.querySelectorAll('.sub_list1 .item').forEach(function(item) {
	item.addEventListener('click', function() {
		event.stopPropagation();
	});
});
let rotationDegrees2 = {};
function toggleSublistAndRotate2(itemId, arrowId) {
	const item = document.getElementById(itemId);
	const sublist = item.querySelector('.sub_list2');
	if (event.target.id === itemId) {
		sublist.style.display = sublist.style.display === 'none' ? 'block' : 'none';
		rotateArrow2(arrowId);
	}
}
function rotateArrow2(arrowId) {
	if (!rotationDegrees2[arrowId]) {
		rotationDegrees2[arrowId] = 0;
	}
	rotationDegrees2[arrowId] += 180;
	const image = document.getElementById(arrowId);
	image.style.transform = `rotate(${rotationDegrees2[arrowId]}deg)`;
}
document.querySelectorAll('.sub_list2 .item').forEach(function(item) {
	item.addEventListener('click', function() {
		event.stopPropagation();
	});
});
let rotationDegrees3 = {};
function toggleSublistAndRotate3(itemId, arrowId) {
	const item = document.getElementById(itemId);
	const sublist = item.querySelector('.sub_list3');
	if (event.target.id === itemId) {
		sublist.style.display = sublist.style.display === 'none' ? 'block' : 'none';
		rotateArrow3(arrowId);
	}
}
function rotateArrow3(arrowId) {
	if (!rotationDegrees3[arrowId]) {
		rotationDegrees3[arrowId] = 0;
	}
	rotationDegrees3[arrowId] += 180;
	const image = document.getElementById(arrowId);
	image.style.transform = `rotate(${rotationDegrees3[arrowId]}deg)`;
}
document.querySelectorAll('.sub_list3 .item').forEach(function(item) {
	item.addEventListener('click', function() {
		event.stopPropagation();
	});
});

// ----------------------------- 상태메세지 설정 --------------------------------------
function handleEnterKey(event) {
	if (event.key === "Enter") {
		saveStatus();
	}
}

function saveStatus() {
	var statusValue = $("#inputBox").val();

	$.ajax({
		type: "POST",
		url: "/updateStatus",
		data: {
			statusValue: statusValue,
		},
		success: function() {
			console.log("상태 메시지가 업데이트되었습니다.");
			$("#inputBox").blur();
		},
		error: function(error) {
			console.error("오류 발생: " + error.message);
		}
	});
}

var inputBox = document.getElementById("inputBox");
inputBox.addEventListener("keyup", handleEnterKey);

// ------------------------------- 현재 날짜 불러오기 -------------------------------------
var currentDate = new Date();
var year = currentDate.getFullYear();
var month = currentDate.getMonth() + 1;
var day = currentDate.getDate();
var dayOfWeek = currentDate.toLocaleDateString('ko-KR', { weekday: 'long' });
var dateString = year + '년 ' + month + '월 ' + day + '일 ' + dayOfWeek;
document.getElementById('currentDate').innerText = dateString;

// --------------------------------- 위젯 --------------------------------------------
const column = document.querySelector('.column');

new Sortable(column, {
	animation: 150,
	ghostClass: "blue-background-class", // 움직이는 동안 보이는 배경 클래스
	onEnd: function(evt) {
		// 드래그 앤 드롭 완료 시 호출되는 콜백 함수
		const order = Array.from(column.children).map(item => item.classList[0]);
		console.log('New Order:', order);

		// 서버에 새로운 순서를 전송
		sendOrderToServer(order);
	}
});

function sendOrderToServer(order) {
	// AJAX를 사용하여 서버로 데이터를 전송
	// 여기서는 jQuery의 $.ajax를 사용하고 있습니다.
	$.ajax({
		type: 'POST',
		url: 'UpdateWidget', // 실제 서버 업데이트 로직이 구현된 JSP 파일
		data: {
			order: JSON.stringify(order, (key, value) => {
				return typeof value === 'string' ? value : value;
			}),
		},
		success: function(response) {
			console.log('순서가 성공적으로 업데이트되었습니다.');
		},
		error: function(error) {
			console.log(order);
			console.error('업데이트 중 오류 발생:', xhr.status, xhr.statusText, error);
		}
	});
}
// -------------------------------- 클릭시 밑줄 -------------------------------------
document.addEventListener('DOMContentLoaded', function() {
	toggleUnderline(document.querySelector('.fw'));
});
let currentClickedDiv = null;
function toggleUnderline(element) {
	if (currentClickedDiv) {
		currentClickedDiv.classList.remove('clicked');
	}
	element.classList.add('clicked');
	currentClickedDiv = element;
}

document.addEventListener('DOMContentLoaded', function() {
	toggleUnderline2(document.querySelector('.chatLine'));
});
let p = null;
function toggleUnderline2(element) {
	if (p) {
		p.classList.remove('clicked2');
	}
	element.classList.add('clicked2');
	p = element;
}

document.addEventListener('DOMContentLoaded', function() {
	toggleUnderline3(document.querySelector('.alertLine'));
});
let t = null;
function toggleUnderline3(element) {
	if (t) {
		t.classList.remove('clicked3');
	}
	element.classList.add('clicked3');
	t = element;
}

function toggleBorder(element) {
	var option1 = document.getElementById('chatInviteOption1');
	var option2 = document.getElementById('chatInviteOption2');

	if (element.id === 'chatInviteOption1') {
		option1.style.borderBottom = '2px solid black';
		option2.style.borderBottom = 'none';
	} else {
		option1.style.borderBottom = 'none';
		option2.style.borderBottom = '2px solid black';
	}
}

// ------------------------------ 우측 상단 메뉴 --------------------------------------------------
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

// --------------------------------------- 배경 이미지 설정 -----------------------------------------
var bgVisible = false;

function toggleBackground() {
	var bgImg = document.getElementById('bgImg');
	bgVisible = !bgVisible;
	bgImg.style.display = bgVisible ? 'block' : 'none';
}

function hideBackground() {
	var bgImg = document.getElementById('bgImg');
	bgVisible = false;
	bgImg.style.display = 'none';
}

// -------------------------------------- 위젯 삭제 버튼 ----------------------------------------------------------------------
const removePops = document.querySelectorAll('.removePop');

removePops.forEach(removePop => {
	// Click event listener 추가
	document.addEventListener('click', function(event) {
		const isRemovePop = removePop.contains(event.target);
		const isDrop = event.target.classList.contains('drop');

		// 클릭된 요소가 removePop 또는 drop이 아닌 경우에만 visible 클래스 제거
		if (!isRemovePop && !isDrop) {
			removePop.classList.remove('visible');
		}
	});

	// 기존의 click 이벤트 리스너 수정
	removePop.previousElementSibling.addEventListener('click', function(event) {
		// 기존에 보여진 removePop이 있는지 확인
		const visibleRemovePop = document.querySelector('.removePop.visible');

		// 현재 클릭한 removePop과 다른 것이 보여지고 있다면 숨김
		if (visibleRemovePop && visibleRemovePop !== removePop) {
			visibleRemovePop.classList.remove('visible');
		}

		// 현재 클릭한 removePop을 토글
		removePop.classList.toggle('visible');

		event.stopPropagation();
	});
});


// ----------------------------- 배경 바꾸기 ----------------------------------------
function changeBackground(imagePath) {
	var backgroundCar = document.getElementById('background_car');
	backgroundCar.src = imagePath;

	$.ajax({
		type: 'POST',
		url: '/updateBackground',
		data: {
			imagePath: imagePath,
		},
		success: function(response) {
			console.log('배경이 성공적으로 업데이트되었습니다.');
		},
		error: function(xhr, status, error) {
			console.error('업데이트 중 오류 발생:', xhr.status, xhr.statusText, error);
		}
	});
}

// ----------------------- 프로젝트 폴더 만들기 ------------------------------------
$('#plus').on('click', function() {
	$('#projectFolderNewWrap').css('display', 'block');
});

$('#pfnClose').on('click', function() {
	$('#projectFolderNewWrap').hide();
	$('#pfnInput').val('');
});

$('#pfnBtn_1').on('click', function() {
	$('#projectFolderNewWrap').hide();
	$('#pfnInput').val('');
});

$('#pfnBtn_2').on('click', function() {
	var folderName = $('#pfnInput').val().trim();
	if (!folderName) {
		window.alert('폴더 이름을 입력하세요.');
		return;
	}
	$.ajax({
		type: 'POST',
		url: '/insertfoldername',
		data: {
			folderName: folderName
		},
		success: function(data) {
			console.log('폴더 이름이 성공적으로 업데이트되었습니다.');
			$('#projectFolderNewWrap').hide();
			$('#pfnInput').val('');
			console.log('ddd'+data);
			updateFolderList(data);
		},
		error: function(error) {
			console.error('업데이트 중 오류 발생:', error);
		}
	});
});
function updateFolderList(data) {
    var $ul = $('.sub_list3');
    $ul.empty();  

    data.forEach(function(folder) {
        var $li = $('<li>').text(folder.name);
        var $img = $('<img>').attr('src', 'images/태그.png').attr('id', '태그1');
        $li.prepend($img);
        $ul.append($li);
    });
}

$('#createProjectBtn').on('click', function(e) {
	e.preventDefault();
	var projectName = $('#creProjectName').val().trim();
	console.log('console: ' + projectName);
	$.ajax({
		type: 'POST',
		url: '/insertProject',
		data: { projectName: projectName },
		contentType: 'application/x-www-form-urlencoded',
		success: function(response) {
			console.log('프로젝트 생성 성공');
			if(response.status === 'success'){
				window.alert('프로젝트가 생성되었습니다.');
				redirectToHome();
			}
		},
		error: function(error) {
			console.error('생성 실패', error);
		}
	});
});
// ------------------------------------- 프로젝트 검색 ------------------------------------------------
var clickedId;
var currentColor = 'black';
var result = $('#time_hello').data('ok');

// 로컬 스토리지에서 프로젝트 이름 가져오기
function getProjectNameFromLocalStorage() {
   return localStorage.getItem('selectedProjectName');
}

function getProjectTypeFromLocalStorage() {
   const storedProjectTypes = localStorage.getItem('projectTypes');
   return storedProjectTypes ? JSON.parse(storedProjectTypes) : [];
}

function getProjectTitleFromLocalStorage() {
   const storedProjectTitles = localStorage.getItem('projectTitles');
   return storedProjectTitles ? JSON.parse(storedProjectTitles) : [];
}

// 로컬 스토리지에 프로젝트 이름 저장하기
function saveProjectNameToLocalStorage(projectName) {
   localStorage.setItem('selectedProjectName', projectName);
}

function saveProjectTypeToLocalStorage(projectTypes) {
   localStorage.setItem('projectTypes', JSON.stringify(projectTypes));
}

function saveProjectTitleToLocalStorage(projectTitles) {
   localStorage.setItem('projectTitles', JSON.stringify(projectTitles));
}

$('#dpBtn1').on('click', function() {
   $('#projectSelectWrap').css('display', 'block');
});

$('#psBtn_1').on('click', function() {
   $('#projectSelectWrap').css('display', 'none');
   $('.psBodyProject').css('color', 'black').removeClass('clicked');
});

$('#psClose').on('click', function() {
   $('#projectSelectWrap').css('display', 'none');
   $('.psBodyProject').css('color', 'black').removeClass('clicked');
});

$('.psBodyProject').on('click', function() {
   currentColor = $(this).css('color');

   if (currentColor === 'rgb(0, 0, 0)') {
      $(this).css('color', 'rgb(128, 0, 255)').addClass('clicked');
   } else {
      $(this).css('color', 'black').removeClass('clicked');
   }

   if ($(this).hasClass('clicked')) {
      clickedId = $(this).data('id');
      console.log('클릭된 .psBodyProject의 data-id:', clickedId);
   }

   $('.psBodyProject').not(this).css('color', 'black').removeClass('clicked');

});

$('#projectSelectName').on('click', function() {
   $('#projectSelectWrap').css('display', 'block');
});

$('#psBtn_2').on('click', function() {
   if (currentColor === 'rgb(0, 0, 0)') {
      $('#projectSelectResult').css('display', 'block');
      $('#projectSelectWrap').css('display', 'none');
      $('#projectSelectNameFirst').css('display', 'none');
      $('#projectSelectName').css('display', 'inline-block');
      $('.widgetProject').css('display', 'none');
      fetchProjectDetails(clickedId);

      $.ajax({
         type: 'POST',
         url: '/selectProjectName',
         data: {
            clickedId: clickedId
         },
         success: function(res) {
            $('#projectSelectName').text(res.name);

            // 로컬 스토리지에 프로젝트 이름 저장
            saveProjectNameToLocalStorage(res.name);
         }
      });
   } else {
      window.alert("프로젝트를 선택하세요.");
   }
});

function fetchProjectDetails(clickedId) {
   var projectSelectResult = $("#projectSelectResult");

   $.ajax({
      type: 'POST',
      url: '/findFeedType',
      data: {
         clickedId: clickedId
      },
      success: function(response) {
         projectSelectResult.empty();

         var projectTypes = [];
         var projectTitles = [];

         for (var i = 0; i < response.length; i++) {
            switch (response[i].type) {
               case 1: response[i].type = "images/글icon.png"; break;
               case 2: response[i].type = "images/업무icon.png"; break;
               case 3: response[i].type = "images/일정icon.png"; break;
               case 4: response[i].type = "images/할일icon.png"; break;
            }
            projectTypes.push(response[i].type);
            projectTitles.push(response[i].title);

            projectSelectResult.append('<div class="psr"><span class="psrSpan"><img class="psrImg" src="' + response[i].type + '"></span><span>' + response[i].title + '</span></div>');
         }

         saveProjectTypeToLocalStorage(projectTypes);
         saveProjectTitleToLocalStorage(projectTitles);
         result = 1;
         
         $.ajax({
            type: 'POST',
            url: '/ok',
            data: {
               result: result
            },
            success: function() {
            }
         });
      },
      error: function(xhr, status, error) {
         console.error('Error:', xhr.status, error);
      }
   });
}

// 페이지 로드 시 로컬 스토리지에서 프로젝트 이름 가져와서 표시
$(document).ready(function() {
   const storedProjectName = getProjectNameFromLocalStorage();
   const storedProjectTypes = getProjectTypeFromLocalStorage();
   const storedProjectTitles = getProjectTitleFromLocalStorage();
   if (result) {
      $('#projectSelectName').css('display', 'inline-block');
      $('.widgetProject').css('display', 'none');
      $('#projectSelectNameFirst').css('display', 'none');
      $('#projectSelectName').text(storedProjectName);
      $('#projectSelectResult').empty();
      for (var i = 0; i < storedProjectTypes.length; i++) {
         $('#projectSelectResult').append('<div class="psr"><span class="psrSpan"><img class="psrImg" src="' + storedProjectTypes[i] + '"></span><span>' + storedProjectTitles[i] + '</span></div>');
      }
   }
});

// -------------------------------------- 캘린더 -----------------------------------------
document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendarSH');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		selectable: true,
		dateClick: function(info) {
			var selectedDateCells = document.querySelectorAll('.selected-date');
			selectedDateCells.forEach(function(cell) {
				cell.classList.remove('selected-date');
			});

			info.dayEl.classList.add('selected-date');

			var selectedDateMoment = moment(info.date);

			sendDateToServer(selectedDateMoment.format('YY/MM/DD'));
			console.log('Selected Date:', selectedDateMoment.format('YY/MM/DD'));
		}
	});

	sendDateToServer(getTodayDate());

	calendar.render();

	function sendDateToServer(date) {
		$.ajax({
			type: "POST",
			url: "schedule",
			data: {
				clickedDate: date,
			},
			success: function(response) {
				handleServerResponse(response);
			},
			error: function(xhr, status, error) {
				console.error("Error occurred:", error);
			}
		});
	}

	function handleServerResponse(response) {
		var noScheduleMsg = document.getElementById('no-schedule-msg');
		var yesScheduleMsg = document.getElementById('yes-schedule-msg');
		var main = $("#yes-schedule-msg");

		main.empty();

		if (response.length === 0) {
			noScheduleMsg.style.display = 'block';
			yesScheduleMsg.style.display = 'none';
		} else {
			noScheduleMsg.style.display = 'none';
			for (var i = 0; i < response.length; i++) {
				var formattedStartDate = moment(response[i].start_date).format('MM/DD');
				var formattedDeadline = moment(response[i].deadline).format('MM/DD');

				main.append('<div id="scheduleTitle">' + '<img src="images/scheduleTitleIcon.png">' + '<div id="sTdiv">' + '<p>' + response[i].title + '</p>' + '<div>' + '시작일 : ' + formattedStartDate + ', 마감일 : ' + formattedDeadline + '</div>' + '</div>' + '</div>');
			}
			yesScheduleMsg.style.display = 'block';
		}
	}

	function getTodayDate() {
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0');
		var yyyy = today.getFullYear().toString().substr(-2);

		return yyyy + '/' + mm + '/' + dd;
	}
});

// --------------------------------- 내가 담당중인 업무 내용 출력 ---------------------------
if (document.getElementById('fwNum').textContent != 0) {
	document.getElementById('feedworkBefore1').style.display = 'none'
	document.getElementById('feedworkAfter1').style.display = 'block'
} else if (document.getElementById('fwNum').textContent == 0) {
	document.getElementById('feedworkBefore1').style.display = 'flex'
}

document.querySelector('.fw').addEventListener('click', function() {
	if (document.getElementById('fwNum').textContent == 0) {
		document.getElementById('feedworkBefore1').style.display = "flex";
		document.getElementById('feedworkBefore2').style.display = "none";
		document.getElementById('feedworkAfter1').style.display = "none";
		document.getElementById('feedworkAfter2').style.display = "none";
	} else if (document.getElementById('fwNum').textContent != 0) {
		document.getElementById('feedworkAfter1').style.display = 'block'
		document.getElementById('feedworkAfter2').style.display = 'none'
		document.getElementById('feedworkBefore1').style.display = 'none'
		document.getElementById('feedworkBefore2').style.display = 'none'
	}
});

document.querySelector('.lw').addEventListener('click', function() {
	if (document.getElementById('lwNum').textContent == 0) {
		document.getElementById('feedworkBefore2').style.display = "flex";
		document.getElementById('feedworkBefore1').style.display = "none";
		document.getElementById('feedworkAfter1').style.display = "none";
		document.getElementById('feedworkAfter2').style.display = "none";
	} else if (document.getElementById('lwNum').textContent != 0) {
		document.getElementById('feedworkAfter2').style.display = 'block'
		document.getElementById('feedworkBefore1').style.display = 'none'
		document.getElementById('feedworkBefore2').style.display = 'none'
		document.getElementById('feedworkAfter1').style.display = 'none'
	}
});

// -------------------- 프로젝트 요소 출력 변경 --------------------------------
document.addEventListener("DOMContentLoaded", function() {
	var fwProcessElements = document.querySelectorAll('.fwProcess');
	var lwProcessElements = document.querySelectorAll('.lwProcess');
	var fwPriorityElements = document.querySelectorAll('.fwPriority');
	var lwPriorityElements = document.querySelectorAll('.lwPriority');

	fwProcessElements.forEach(function(element) {
		var processValue = element.getAttribute('data-process');

		switch (processValue) {
			case '1':
				element.innerHTML = '<span style="background-color: #00b2ff;"></span>요청';
				break;
			case '2':
				element.innerHTML = '<span style="background-color: #00b01c;"></span>진행';
				break;
			case '3':
				element.innerHTML = '<span style="background-color: #fd7900;"></span>피드백';
				break;
			case '4':
				element.innerHTML = '<span style="background-color: #402a9d;"></span>완료';
				break;
			case '5':
				element.innerHTML = '<span style="background-color: #777;"></span>보류';
				break;
		}
	});

	lwProcessElements.forEach(function(element) {
		var processValue = element.getAttribute('data-process');

		switch (processValue) {
			case '1':
				element.innerHTML = '<span style="background-color: #00b2ff;"></span>요청';
				break;
			case '2':
				element.innerHTML = '<span style="background-color: #00b01c;"></span>진행';
				break;
			case '3':
				element.innerHTML = '<span style="background-color: #fd7900;"></span>피드백';
				break;
			case '4':
				element.innerHTML = '<span style="background-color: #402a9d;"></span>완료';
				break;
			case '5':
				element.innerHTML = '<span style="background-color: #777;"></span>보류';
				break;
		}
	});

	fwPriorityElements.forEach(function(element) {
		var priorityValue = element.getAttribute('data-priority');

		switch (priorityValue) {
			case '1':
				element.innerHTML = '<img src="images/우선순위_낮음.png">';
				break;
			case '2':
				element.innerHTML = '<img src="images/우선순위_보통.png">';
				break;
			case '3':
				element.innerHTML = '<img src="images/우선순위_높음.png">';
				break;
			case '4':
				element.innerHTML = '<img src="images/우선순위_긴급.png">';
				break;
		}
	});

	lwPriorityElements.forEach(function(element) {
		var priorityValue = element.getAttribute('data-priority');

		switch (priorityValue) {
			case '1':
				element.innerHTML = '<img src="images/우선순위_낮음.png">';
				break;
			case '2':
				element.innerHTML = '<img src="images/우선순위_보통.png">';
				break;
			case '3':
				element.innerHTML = '<img src="images/우선순위_높음.png">';
				break;
			case '4':
				element.innerHTML = '<img src="images/우선순위_긴급.png">';
				break;
		}
	});
});

// -------------------------------- 메모장 ----------------------------------------------
var memoWrap = document.getElementById('memoWrap');
var memoFirst = document.getElementById('memoFirst');

if (memoWrap.textContent.trim() !== "") {
	memoFirst.style.display = "none";
} else {
	memoFirst.style.display = "block";
}

memoWrap.addEventListener('input', function() {
	if (memoWrap.textContent.trim() !== "") {
		memoFirst.style.display = "none";
	} else {
		memoFirst.style.display = "block";
	}
});

$('#memoBtn').click(function() {
	var memo = $('#memoWrap').text().trim();

	$.ajax({
		type: 'POST',
		url: '/updateMemo',
		data: {
			memo: memo,
		},
		success: function() {
			console.log("메모장 업데이트 성공");
		},
		error: function() {
			console.log("bad");
		}
	});
});


// ------------------------------------- 채팅방, 연락처 -----------------------------------------------------------------
document.querySelector('.chatLine').addEventListener("click", function() {
	document.getElementById('chatRoomMain').style.display = "block";
	document.getElementById('chatMemberList').style.display = "none";
});

document.querySelector('.contactLine').addEventListener("click", function() {
	document.getElementById('chatRoomMain').style.display = "none";
	document.getElementById('chatMemberList').style.display = "block";
});

var chatInviteWrap = document.getElementById('chatInviteWrap');
var openChattingButton = document.getElementById('openChatting');

openChattingButton.addEventListener("click", function() {
	var currentDisplay = window.getComputedStyle(chatInviteWrap).display;
	document.getElementById('chatLayer').style.display = "none";
	if (currentDisplay === "none") {
		chatInviteWrap.style.display = "block";
	} else {
		chatInviteWrap.style.display = "none";
	}
});

document.getElementById('chatInviteBtn1').addEventListener("click", function() {
	document.getElementById('chatInviteWrap').style.display = "none";
	$('.companyMem').removeClass('selected');
	$('.otherMem').removeClass('selected');
	selectedItems = [];
});

document.getElementById('chatInviteOption1').addEventListener("click", function() {
	document.getElementById('chatInviteSelect1').style.display = "block";
	document.getElementById('chatInviteSelect2').style.display = "none";
});

document.getElementById('chatInviteOption2').addEventListener("click", function() {
	document.getElementById('chatInviteSelect1').style.display = "none";
	document.getElementById('chatInviteSelect2').style.display = "block";
});

// ------------------------------------- 채팅방 초대 ---------------------------------------------------------------
var selectedItems = [];

$('#chatInviteSelect1').find('.companyMem').on('click', function() {
	var isSelected = $(this).hasClass('selected');
	var username = $(this).data('id');

	if (!isSelected) {
		selectedItems.push(username);
	} else {
		var index = selectedItems.indexOf(username);
		if (index !== -1) {
			selectedItems.splice(index, 1);
		}
	}

	$(this).toggleClass('selected');

	// 추가: 선택된 모든 username 출력
	console.log('선택된 username들:', selectedItems);
});

$('#chatInviteSelect2').find('.otherMem').on('click', function() {
	var isSelected = $(this).hasClass('selected');
	var username = $(this).data('id');

	if (!isSelected) {
		selectedItems.push(username);
	} else {
		var index = selectedItems.indexOf(username);
		if (index !== -1) {
			selectedItems.splice(index, 1);
		}
	}

	$(this).toggleClass('selected');

	// 추가: 선택된 모든 username 출력
	console.log('선택된 username들:', selectedItems);
});


/////////////////////////////////////미슐랭★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★검색기능///////////////////////////////////////////////////
document.getElementById("chatSearch").addEventListener("input", function() {
	var searchText = this.value.toLowerCase();
	var chatMembers = document.getElementsByClassName("chatMember");

	for (var i = 0; i < chatMembers.length; i++) {
		var usernameAttr = chatMembers[i].getAttribute("data-username");
		var username = usernameAttr ? usernameAttr.toLowerCase() : "";
		var display = username.includes(searchText) ? "inline-flex" : "none";
		chatMembers[i].style.display = display;
	}
});

document.getElementById("psInput").addEventListener("input", function() {
	var searchText = this.value.toLowerCase();
	var psInputResult = document.getElementsByClassName("psBodyProject");
	var noResult = document.getElementById('noResult');
	var hasMatchingResults = false;

	for (var i = 0; i < psInputResult.length; i++) {
		var projectnameAttr = psInputResult[i].getAttribute("data-name");
		var psProjectName = projectnameAttr ? projectnameAttr.toLowerCase() : "";
		var display = psProjectName.includes(searchText) ? "flex" : "none";
		psInputResult[i].style.display = display;

		if (display === "flex") {
			hasMatchingResults = true;
		}
	}

	if (!hasMatchingResults && searchText !== "") {
		noResult.style.display = "flex";
	} else {
		noResult.style.display = "none";
	}
});

document.getElementById("chatInviteInput").addEventListener("input", function() {
	if (document.getElementById('chatInviteSelect2').style.display === "block") {
		var searchText = this.value.toLowerCase();
		var otherMembers = document.getElementsByClassName("otherMem");

		for (var i = 0; i < otherMembers.length; i++) {
			var usernameAttr = otherMembers[i].getAttribute("data-username");
			var username = usernameAttr ? usernameAttr.toLowerCase() : "";
			var display = username.includes(searchText) ? "inline-flex" : "none";
			otherMembers[i].style.display = display;
		}
	} else {
		var searchText = this.value.toLowerCase();
		var companyMembers = document.getElementsByClassName("companyMem");

		for (var i = 0; i < companyMembers.length; i++) {
			var usernameAttr = companyMembers[i].getAttribute("data-username");
			var username = usernameAttr ? usernameAttr.toLowerCase() : "";
			var display = username.includes(searchText) ? "inline-flex" : "none";
			companyMembers[i].style.display = display;
		}
	}
});

// ------------------------------------- 채팅방 열기 -----------------------------------------
$('#chatInviteBtn2').on('click', function() {
	// 클라이언트에서 선택된 항목들을 문자열로 변환
	var selectedItemsAsString = selectedItems.map(String);
	if (selectedItemsAsString.length != 0) {
		$.ajax({
			url: '/openChatting',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(selectedItemsAsString),
			success: function(data) {
				for (var i = 0; i < data.length; i++) {
					console.log(data[i]);
				}
			},
			error: function(error) {
				console.error('Error fetching content:', error);
			}
		});

		$.ajax({
			url: '/mychatt',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(selectedItemsAsString),
			success: function(data) {
				var newWindow = window.open('', '_blank', 'width=600,height=600,top=' + (screen.height - 400) + ',left=' + (screen.width - 300));
				newWindow.document.write(data);
			},
			error: function(error) {
				console.error('Error fetching content:', error);
			}
		});
	} else {
		window.alert("아무도 선택되지 않았습니다.");
	}
});
//-------------------------------------------인삿말-------------------------------------------------
function getCurrentTime() {
	var now = new Date();
	var hours = now.getHours();

	return hours;
}
function setGreeting() {
	var greetingText = document.getElementById('time_hello');
	var currentTime = getCurrentTime();
	var greeting = '';

	if (currentTime >= 5 && currentTime < 12) {
		greeting = '님 좋은 아침입니다!';
	} else if (currentTime >= 12 && currentTime < 18) {
		greeting = '님 즐거운 오후입니다!';
	} else {
		greeting = '님 행복한 저녁입니다!';
	}

	greetingText.textContent += ' ' + greeting;
}

// 페이지 로딩 시 호출
window.onload = setGreeting;
