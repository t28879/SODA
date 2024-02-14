//==============================================피드에서 프로젝트 폴더 설정 js=======================================================

// 피드에서 프로젝트 폴더 설정할때 호버 되는 버튼
$(document).on('click', '#projectFolderContent .folderli', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('#projectFolderContent .folderli').removeClass('hover'); // 모든 버튼에서 hover 클래스 제거
	$(this).addClass('hover'); // 클릭한 버튼에만 hover 클래스 추가
});

// 프로젝트 폴더 설정 눌렀을때 팝업뜨게
$(document).on('click', '#MSdetailSettingLabelBtn', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('#projectFolderOpWrap').show();
	$('#MSdetailSettingLayer').hide();
});

$(document).on('click', '#ProjectFolderCancle', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('#projectFolderOpWrap').hide();
});

$(document).on('click', '#ProjectFolderOk', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('#projectFolderOpWrap').hide();
});

//==============================================피드에서 프로젝트 색상 설정 js=======================================================

// 프로젝트 색상 팝업에서 취소 누르면 팝업창 none
$(document).on('click', '#SHColorChangeCanclecan', function(event) {
	event.stopPropagation();
	$('#ProjectColorChange').css('display', 'none');
});

// 프로젝트 색상 팝업에서 확인 누르면 none
$(document).on('click', '#SHColorChangeOk', function(event) {
	event.stopPropagation();
	$('#ProjectColorChange').css('display', 'none');
});

// 색상 박스 누르면 나오는 색상 변경 팝업창
$(document).on('click', '.project-color-area', function() {
	$('#ProjectColorChange').css('display', 'block');
});

// 색상 hover
$(document).on('click', '.hoverBtn', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('.hoverBtn').removeClass('hover'); // 모든 버튼에서 hover 클래스 제거
	$(this).addClass('hover'); // 클릭한 버튼에만 hover 클래스 추가
});

//==============================================피드에서 프로젝트 나가기 js=======================================================
$(document).on('click', '#MSdetailSettingExitbtn', function() {
	$('#MSdetailSettingLayer').hide();
	$('#projectOutPopup').show();
});

$(document).on('click', '#projectOutCancle', function() {
	$('#projectOutPopup').hide();
});

//==============================================피드에서 프로젝트 삭제 js=======================================================
$(document).on('click', '#MSdetailSettingProjectDeleteBtn', function() {
	$('#MSdetailSettingLayer').hide();
	$('#projectDeletePopup').show();
});

$(document).on('click', '#projectDeleteCancle', function() {
	$('#projectDeletePopup').hide();
});


//==============================================피드에서 프로필 팝업 js=======================================================
// 팝업의 닫기 이미지나 외부 클릭하면 팝업 닫히게
$(document).on('click', ".profile-close", function() {
	var profilePopup = $('.profile-popup');

	profilePopup.hide();
});

$(document).on('click', function(event) {
	var popup = $('.profile-popup');
	var postAuthor = $('.post-author');

	// 팝업 외부를 클릭한 경우
	if (!popup.is(event.target) && popup.has(event.target).length === 0 &&
		!postAuthor.is(event.target) && postAuthor.has(event.target).length === 0) {
		// 팝업 숨기기
		popup.hide();
	}
});

//==============================================전체적인 피드 js=======================================================

//--업무 통계 표 js--
var taskCount = 0;
document.addEventListener('DOMContentLoaded', function() {
	var ReportArea = document.querySelector('.section-title-area');
	var taskReportLayer = document.querySelector('.js-task-report-layer.d-none');

	taskReportLayer.style.display = 'none';

	ReportArea.addEventListener('click', function() {
		if (taskCount == 0) {
			taskReportLayer.style.display = 'block';
			taskCount++;
		} else if (taskCount == 1) {
			taskReportLayer.style.display = "none";
			taskCount--;
		}
	});
});

//업무 통계 표 js 뭐가 진짜지
var reportLayerCnt = 1;
$(document).on('click', '#taskReportOnOff', function() {
	if (reportLayerCnt == 1) {
		$('.js-task-report-layer').show();
		reportLayerCnt--;
	} else if (reportLayerCnt == 0) {
		$('.js-task-report-layer').hide();
		reportLayerCnt++;
	}
});

// --프로젝트 색상버튼 옆에 span 3개 누르면 나오는 프로젝트 설정 버튼--
var detailSettingCount = 1;

$(document).on('click', '#MSdetailSettingTopButton', function() {
	if (detailSettingCount % 2 == 1) {
		$('.MSproject-setup-wrap').show();
		detailSettingCount++;
	} else if (detailSettingCount % 2 == 0) {
		$('.MSproject-setup-wrap').hide();
		detailSettingCount--;
	}
});

//--전체보기 누르면 나오는 팝업--
var joinerPopupCount = 1;

// 전체보기 팝업이 show 일때 외부 누르면 팝업 닫히게
$(document).on('click', function(event) {
	// 팝업 외부를 클릭한 경우
	if (!$(event.target).closest('#allSendiencePopup, #allSendienceBtn').length) {
		// 팝업 숨기기
		$('#allSendiencePopup').hide();
		joinerPopupCount = 1; // 팝업 외부 클릭 시 팝업 카운트 초기화
	}
});

$(document).on('click', '#allSendienceBtn', function() {
	if (joinerPopupCount % 2 == 1) {
		$('#allSendiencePopup').show();
		joinerPopupCount++;
	} else if (joinerPopupCount % 2 == 0) {
		$('#allSendiencePopup').hide();
		joinerPopupCount--;
	}
});

//-- 초대하기 버튼과 팝업에 대한 js --
$(document).on('click', '#inviteLayer', function() {
	var inviteWrap = $('#inviteWrap');

	if (inviteWrap.css('display') == 'none') {
		inviteWrap.show();
	} else if (inviteWrap.css('display') == 'block') {
		inviteWrap.hide();
	}
});

$(document).on('click', '#inviteBlockClose', function() {
	$('#inviteWrap').hide();
});



//==============================================★★★피드 팝업★★★ js=======================================================
// 피드 페이지 에서 피드 생성 클릭시 팝업 노출 createPostUl
document.addEventListener("DOMContentLoaded", function() {
	var createPostArea = document.getElementById("createPostArea");

	// createPostArea을 클릭했을 때 postPopup을 보이게 함
	createPostArea.addEventListener("click", function(event) {
		postPopup.style.display = "block";

		// 일정 시간 후에 알람 띄우기
		setTimeout(function() {
			alert('생성할 피드의 탭을 한 번 눌러주세요!');
		}, 500); // 

		event.stopPropagation(); // 이벤트 전파 막기
	});

	// 문서 전체를 클릭했을 때 postPopup을 숨김
	document.addEventListener("click", function(event) {
		if (event.target !== postPopup
			&& !postPopup.contains(event.target)) {
			postPopup.style.display = "none";
		}
	});

	// postPopup 내부 클릭 시 이벤트 전파 막기
	postPopup.addEventListener("click", function(event) {
		event.stopPropagation();
	});
});

//--팝업창에서 닫기2 이미지 누르면 팝업 닫힘--
document.querySelector('.img-close2').addEventListener('click', function() {
	document.getElementById('postPopup').style.display = 'none';
});

// 팝업을 닫았다가 다시 열면 초기화

// 팝업 input 키 다운 프레스

// 피드 업무의 하위업무 요청 상태 버튼
document.addEventListener("DOMContentLoaded", function() {
	// 초기화 버튼에 이벤트 리스너 추가
	document.querySelector('.img-close2').addEventListener('click', function() {
		$('.task-state-button').css('background', 'rgb(238, 238, 238)');
		$('.task-state-button').css('color', 'rgb(119, 119, 119)');

		$('#MSsubtask-button1').css('display', 'block');
		$('#MSsubtask-button2').css('display', 'none');
		$('#MSsubtask-button3').css('display', 'none');
		$('#MSsubtask-button4').css('display', 'none');
		$('#MSsubtask-button5').css('display', 'none');

		$('#MSstartDate').css('display', 'none');
		$('#MSendDate').css('display', 'none');
		$('#MSpriorityNumbers').css('display', 'none');
		$('#MSprogressGraph').css('display', 'none');
		$('.more-button').css('display', 'block');

		$('#MSsubtaskPriorityButton').css('display', 'block');
		$('#subPriorityPopup').css('display', 'none');

	});
});

// 팝업창에서 내용입력시 엔터키가 쳐지게 하는 스크립트--
document.addEventListener("DOMContentLoaded", function() {
	var textInput = document.getElementById("textInput");

	textInput.addEventListener("keypress", function(event) {
		// 엔터 키가 눌렸을 때 실행할 동작
		if (event.key === "Enter" || event.keyCode === 13) {
			// 입력된 값을 얻어와서 콘솔에 출력
			textInput.replace(/\n/g, "<br>");

			// 기본 엔터 키 동작 막기
			event.preventDefault();
		}
	});
});

// --피드 팝업 창에서 각각의 메뉴 탭에서 누르면 그 탭에 맞는 팝업이 생성--

document.addEventListener('DOMContentLoaded', function() {
	var write2Tab = document.querySelector('.create-tab.tab-write2');
	var taskTab = document.querySelector('.create-tab.tab-task');
	var scheduleTab = document.querySelector('.create-tab.tab-schedule');
	var todoTab = document.querySelector('.create-tab.tab-todo');
	var taskOption = document.querySelector('.task-option');
	var scheduleOption = document.querySelector('.schedule-option');
	var todoOption = document.querySelector('.todo-option');

	var postContentInput = document.querySelector('.cke_inner.cke_reset');

	// 초기 화면 설정
	taskOption.style.display = 'none';
	scheduleOption.style.display = 'none';
	todoOption.style.display = 'none';
	postContentInput.style.display = 'block';

	write2Tab.addEventListener('click', function() {
		taskOption.style.display = 'none';
		scheduleOption.style.display = 'none';
		todoOption.style.display = 'none';

		if (postContentInput.style.display = 'none') {
			postContentInput.style.display = 'block';
		}

	});

	taskTab.addEventListener('click', function() {
		taskOption.style.display = 'block';
		scheduleOption.style.display = 'none';
		todoOption.style.display = 'none';

		if (postContentInput.style.display = 'none') {
			postContentInput.style.display = 'block';
		}

	});

	scheduleTab.addEventListener('click', function() {
		scheduleOption.style.display = 'block';
		taskOption.style.display = 'none';
		todoOption.style.display = 'none';

		if (postContentInput.style.display = 'none') {
			postContentInput.style.display = 'block';
		}

	});

	todoTab.addEventListener('click', function() {
		todoOption.style.display = 'block';
		taskOption.style.display = 'none';
		scheduleOption.style.display = 'none';

		if (postContentInput.style.display = 'block') {
			postContentInput.style.display = 'none';
		}
	});
});

//팝업의 일정에서 date 표시하기
document.addEventListener("DOMContentLoaded", function() {
	// 현재 날짜를 가져오기
	const now = new Date();
	const year = now.getFullYear();
	let month = (now.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 +1
	let day = now.getDate().toString().padStart(2, '0');

	// 날짜 형식을 YYYY-MM-DD로 만들어서 입력 필드에 설정
	const currentDate = `${year}-${month}-${day}`;
	document.getElementById("MSdatePicker1").value = currentDate;
});

document.addEventListener("DOMContentLoaded", function() {
	// 현재 날짜를 가져오기
	const now = new Date();
	const year = now.getFullYear();
	let month = (now.getMonth() + 1).toString().padStart(2, '0'); // 월은 0부터 시작하므로 +1
	let day = now.getDate().toString().padStart(2, '0');

	// 날짜 형식을 YYYY-MM-DD로 만들어서 입력 필드에 설정
	const currentDate = `${year}-${month}-${day}`;
	document.getElementById("MSdatePicker2").value = currentDate;
});

//우선순위 팝업
var priorityCount = 1;
document.addEventListener('DOMContentLoaded', function() {
	var priorityButton = document.getElementById('priorityButton');
	var priorityPopup = document.getElementById('priorityPopup');

	priorityButton.addEventListener('click', function() {
		if (priorityCount % 2 == 0) {
			priorityPopup.style.display = 'none';
			priorityCount++;
		} else {
			priorityPopup.style.display = 'block';
			priorityCount++;
		}
	});

});

//추가 항목 눌렀을 때 나머지들 뜨게
document.addEventListener('DOMContentLoaded', function() {
	// 추가항목입력 버튼	
	var addButton = document.querySelector('.more-button.add-button');
	var startDate = document.getElementById('MSstartDate');
	var endDate = document.getElementById('MSendDate');
	var priorityNumbers = document.getElementById('MSpriorityNumbers');
	var progressGraph = document.getElementById('MSprogressGraph');

	addButton.addEventListener('click', function() {
		startDate.style.display = 'block';
		endDate.style.display = 'block';
		priorityNumbers.style.display = 'block';
		progressGraph.style.display = 'block';
		addButton.style.display = 'none';
	});

});

// 우선순위 팝업에서 선택시 선택한 팝업이 우선순위 추가 버튼으로 가고 나머지는 none
document.addEventListener('DOMContentLoaded', function() {
	//우선순위 추가 버튼
	var priorityButton = document.getElementById('priorityButton');
	// 우선순위 추가 팝업
	var priorityPopup = document.getElementById('priorityPopup');
	//각각의 팝업안의 버튼
	//낮음
	var priorityButtons1 = document.getElementById('priorityButtons-1');
	//보통
	var priorityButtons2 = document.getElementById('priorityButtons-2');
	//높음
	var priorityButtons3 = document.getElementById('priorityButtons-3');
	//긴급
	var priorityButtons4 = document.getElementById('priorityButtons-4');

	//바깥쪽 우선순위 버튼들
	var priorityRow = document.querySelector('.priority-row');
	var priorityNormal = document.querySelector('.priority-normal');
	var priorityArrow = document.querySelector('.priority-arrow');
	var prioritySOS = document.querySelector('.priority-sos');

	// 닫는 버튼
	var priorityClose1 = document.getElementById('priorityClose1');
	var priorityClose2 = document.getElementById('priorityClose2');
	var priorityClose3 = document.getElementById('priorityClose3');
	var priorityClose4 = document.getElementById('priorityClose4');

	priorityButtons1.addEventListener('click', function() {
		priorityButton.style.display = "none";
		priorityPopup.style.display = "none";
		priorityRow.style.display = "block";
	});
	priorityButtons2.addEventListener('click', function() {
		priorityButton.style.display = "none";
		priorityPopup.style.display = "none";
		priorityNormal.style.display = "block";
	});
	priorityButtons3.addEventListener('click', function() {
		priorityButton.style.display = "none";
		priorityPopup.style.display = "none";
		priorityArrow.style.display = "block";
	});
	priorityButtons4.addEventListener('click', function() {
		priorityButton.style.display = "none";
		priorityPopup.style.display = "none";
		prioritySOS.style.display = "block";
	});

	priorityClose1.addEventListener('click', function() {
		priorityButton.style.display = 'block';
		priorityRow.style.display = 'none';
		priorityNormal.style.display = 'none';
		priorityArrow.style.display = 'none';
		prioritySOS.style.display = 'none';
	});
	priorityClose2.addEventListener('click', function() {
		priorityButton.style.display = 'block';
		priorityRow.style.display = 'none';
		priorityNormal.style.display = 'none';
		priorityArrow.style.display = 'none';
		prioritySOS.style.display = 'none';
	});
	priorityClose3.addEventListener('click', function() {
		priorityButton.style.display = 'block';
		priorityRow.style.display = 'none';
		priorityNormal.style.display = 'none';
		priorityArrow.style.display = 'none';
		prioritySOS.style.display = 'none';
	});
	priorityClose4.addEventListener('click', function() {
		priorityButton.style.display = 'block';
		priorityRow.style.display = 'none';
		priorityNormal.style.display = 'none';
		priorityArrow.style.display = 'none';
		prioritySOS.style.display = 'none';
	});

});

// 요청 상태 눌렀을 때 색깔 바뀌게
document.addEventListener('DOMContentLoaded', function() {
	var buttonGroup = document.querySelector('.state-button-group');

	buttonGroup.addEventListener('click', function(event) {
		var clickedButton = event.target;

		// 모든 버튼에 회색 배경을 적용
		buttonGroup.querySelectorAll('.task-state-button').forEach(function(button) {
			button.style.background = '#eee';
			button.style.color = '#777';
		});

		// 클릭된 버튼에 해당하는 클래스를 추가하여 배경색을 변경
		if (clickedButton.classList.contains('request')) {
			clickedButton.style.background = '#00b2ff';
			clickedButton.style.color = '#eee';
		} else if (clickedButton.classList.contains('progress')) {
			clickedButton.style.background = '#00b01c';
			clickedButton.style.color = '#eee';
		} else if (clickedButton.classList.contains('feedback')) {
			clickedButton.style.background = '#fd7900';
			clickedButton.style.color = '#eee';
		} else if (clickedButton.classList.contains('completion')) {
			clickedButton.style.background = '#402a9d';
			clickedButton.style.color = '#eee';
		} else if (clickedButton.classList.contains('hold')) {
			clickedButton.style.background = '#777';
			clickedButton.style.color = '#eee';
		}
	});
});

/* 피드 업무의 담당자 */
$('#addWorkerCancel').on('click', function() {
	$('#allAllManagerLists').hide();
});

/* 피드 하위 업무의 담당자 변경 팝업 */
$('.modify-subtask-worker').on('click', function(){
	var target = $(this).siblings('.sub-host-add-all-manager');
	target.show();
});

// 하위업무담당자 변경 팝업 닫기
$('.sub-host-add-worker-cancle').on('click', function(){
	$('.sub-host-add-all-manager').hide();
});

// 진척도 퍼센트를 표시하는 함수
// 현재 진행 중인 인덱스를 저장하는 변수
var currentProgressIndex = 0;
// 진척도 퍼센트를 표시하는 함수
function fillProgress(index) {
	// 현재 클릭한 버튼까지만 빨간색으로 설정
	for (var i = 0; i <= index; i++) {
		$('#progressPercent' + i).css('background-color', 'red');
	}

	// 이전 진행 상태를 초기화
	for (var t = index + 1; t <= 10; t++) {
		$('#progressPercent' + t).css('background-color', '');
	}

	// 현재 진행 상태를 저장
	currentProgressIndex = index;

	// %에 따라 텍스트 변경
	var progressTextElement = document.querySelector('.ms-progress-text');
	progressTextElement.textContent = index * 10 + '%';
}


//팝업의 업무의 시작일 마감일 달력
// JavaScript 코드로 flatpickr 초기화 (시작일)
document.addEventListener("DOMContentLoaded", function() {
	flatpickr("#MSscheduleStartDate", {
		enableTime: false,
		dateFormat: "Y-m-d",
		placeholder: "시작일 추가",
		static: true, // 달력을 선택한 날짜로 고정
		readOnly: true,
	});
});

// JavaScript 코드로 flatpickr 초기화 (마감일)
document.addEventListener("DOMContentLoaded", function() {
	flatpickr("#MSscheduleEndDate", {
		enableTime: false,
		dateFormat: "Y-m-d",
		placeholder: "마감일 추가",
		static: true, // 달력을 선택한 날짜로 고정
		readOnly: true,
	});
});


//하위업무 상태 버튼에 대한 js
function clickRequest() {
	if ($('#MSsubtask-button2').is(':visible') && $('#MSsubtask-button3').is(':visible') && $('#MSsubtask-button4').is(':visible') && $('#MSsubtask-button5').is(':visible')) {
		$('#MSsubtask-button2').hide();
		$('#MSsubtask-button3').hide();
		$('#MSsubtask-button4').hide();
		$('#MSsubtask-button5').hide();
		return;
	}
	$('#MSsubtask-button2').show();
	$('#MSsubtask-button3').show();
	$('#MSsubtask-button4').show();
	$('#MSsubtask-button5').show();
}

function clickProgress() {
	if ($('#MSsubtask-button1').is(':visible') && $('#MSsubtask-button3').is(':visible') && $('#MSsubtask-button4').is(':visible') && $('#MSsubtask-button5').is(':visible')) {
		$('#MSsubtask-button1').hide();
		$('#MSsubtask-button3').hide();
		$('#MSsubtask-button4').hide();
		$('#MSsubtask-button5').hide();
		return;
	}
	$('#MSsubtask-button1').show();
	$('#MSsubtask-button3').show();
	$('#MSsubtask-button4').show();
	$('#MSsubtask-button5').show();
}

function clickFeedback() {
	if ($('#MSsubtask-button2').is(':visible') && $('#MSsubtask-button1').is(':visible') && $('#MSsubtask-button4').is(':visible') && $('#MSsubtask-button5').is(':visible')) {
		$('#MSsubtask-button2').hide();
		$('#MSsubtask-button1').hide();
		$('#MSsubtask-button4').hide();
		$('#MSsubtask-button5').hide();
		return;
	}
	$('#MSsubtask-button2').show();
	$('#MSsubtask-button1').show();
	$('#MSsubtask-button4').show();
	$('#MSsubtask-button5').show();
}

function clickCompletion() {
	if ($('#MSsubtask-button2').is(':visible') && $('#MSsubtask-button3').is(':visible') && $('#MSsubtask-button1').is(':visible') && $('#MSsubtask-button5').is(':visible')) {
		$('#MSsubtask-button2').hide();
		$('#MSsubtask-button3').hide();
		$('#MSsubtask-button1').hide();
		$('#MSsubtask-button5').hide();
		return;
	}
	$('#MSsubtask-button2').show();
	$('#MSsubtask-button3').show();
	$('#MSsubtask-button1').show();
	$('#MSsubtask-button5').show();
}

function clickHold() {
	if ($('#MSsubtask-button2').is(':visible') && $('#MSsubtask-button3').is(':visible') && $('#MSsubtask-button4').is(':visible') && $('#MSsubtask-button1').is(':visible')) {
		$('#MSsubtask-button2').hide();
		$('#MSsubtask-button3').hide();
		$('#MSsubtask-button4').hide();
		$('#MSsubtask-button1').hide();
		return;
	}
	$('#MSsubtask-button2').show();
	$('#MSsubtask-button3').show();
	$('#MSsubtask-button4').show();
	$('#MSsubtask-button1').show();
}

//팝업의 업무의 하위업무의 마감일 버튼
document.addEventListener("DOMContentLoaded", function() {
	flatpickr(".MSsubtask-endDate-input", {
		enableTime: false,
		dateFormat: "Y-m-d",
		placeholder: "마감일 설정",
		static: true, // 달력을 선택한 날짜로 고정
		readOnly: true,
	});

});

//팝업의 하위업무의 우선순위의 팝업
//팝업의 업무에서 하위업므에서 우선순위 버튼끼리 바뀌는거
function dmddody() {
	if ($('#subPriorityPopup').is(':visible')) {
		$('#subPriorityPopup').hide();
	}
	else {
		$('#subPriorityPopup').show();
	}

}

function popupdh(index) {
	switch (index) {
		case 1: {
			var img = document.querySelector('.sosbtndh');
			img.src = '/images/긴급사태.png';
			$('#subPriorityPopup').hide();
			break;
		}
		case 2: {
			var img = document.querySelector('.sosbtndh');
			img.src = '/images/row화살표.png';
			$('#subPriorityPopup').hide();
			break;
		}
		case 3: {
			var img = document.querySelector('.sosbtndh');
			img.src = '/images/보통쓰.png';
			$('#subPriorityPopup').hide();
			break;
		}
		case 4: {
			var img = document.querySelector('.sosbtndh');
			img.src = '/images/빨간화살표.png';
			$('#subPriorityPopup').hide();
			break;
		}
		case 5: {
			var img = document.querySelector('.sosbtndh');
			img.src = '/images/긴급.png';
			$('#subPriorityPopup').hide();
			break;
		}
	}

}

//할일에서 달력버튼
document.addEventListener('DOMContentLoaded', function() {
	var MStodoInput = document.getElementById('MStodoInput');
	var flatpickrInstance = null;

	// Create a new Flatpickr instance with placeholder text
	flatpickrInstance = flatpickr(MStodoInput, {
		// Flatpickr options here
		placeholder: "할 일 날짜 설정"
	});

	MStodoInput.addEventListener('focus', function() {
		// Destroy the previous Flatpickr instance, if it exists
		if (flatpickrInstance !== null) {
			flatpickrInstance.destroy();
		}

		// Create a new Flatpickr instance
		flatpickrInstance = flatpickr(MStodoInput, {
			// Flatpickr options here
		});
	});
});

//팝업 업무에서 담당자 팝업 뜨게
// host-worker-input 클래스를 가진 input 요소를 클릭했을 때
$(".worker-search-input").on("click", function() {
	// host-add-all-manager 클래스를 가진 요소의 display 속성을 토글
	$(".add-all-manager").toggle();
});

// 수정팝업 담당자 인풋
$('.modify-work-input').on('click', function() {
	$('.host-add-all-manager').show();
});

// 피드 수정시 뜨는 업무 담당자 팝업
$('.host-add-worker-cancle').on('click', function() {
	$('.host-add-all-manager').hide();
});

// 팝업의 하위업무 담당자 팝업
$('.manager-img').on('click', function() {
	$('.st-add-all-manager').toggle();
});

$('#st-addWorkerCancel').on('click', function() {
	$('.st-add-all-manager').hide();
});


// 팝업창의 등록버튼 누르면 팝업 닫히게
$('#popupSubmit').on('click', function() {
	$('.soda-all-background-1').hide();
});

//===============================피드팝업에서 게시물 생성 후 생성되는 게시물들에 대한 js=======================================================
// 피드수정 버튼 클릭시 show hide (DH 코드)
$(document).on('click', '.settingBtn1', function() {
	var feed_id = $(this).data('feed-id');
	var targetSetGroup = $('.setGroup1[data-feed-id="' + feed_id + '"]');

	if (targetSetGroup.css('display') == 'none') {
		targetSetGroup.show();
	} else if (targetSetGroup.css('display') == 'block') {
		targetSetGroup.hide();
	}

});


// 피드 수정 삭제 버튼 클릭시 팝업 뜨게 2번(DH코드)
$(document).on('click', '.settingBtn2', function() {
	var feed_id = $(this).data('feed-id');
	var targetSetGroup = $('.setGroup2[data-feed-id="' + feed_id + '"]');

	if (targetSetGroup.css('display') == 'none') {
		targetSetGroup.show();
	} else if (targetSetGroup.css('display') == 'block') {
		targetSetGroup.hide();
	}

});

// 피드 수정 삭제 버튼 클릭시 팝업 뜨게 3번(DH코드)
$(document).on('click', '.settingBtn3', function() {
	var feed_id = $(this).data('feed-id');
	var targetSetGroup = $('.setGroup3[data-feed-id="' + feed_id + '"]');

	if (targetSetGroup.css('display') == 'none') {
		targetSetGroup.show();
	} else if (targetSetGroup.css('display') == 'block') {
		targetSetGroup.hide();
	}
});

// 피드 수정 삭제 버튼 클릭시 팝업 뜨게 4번(DH코드)
$(document).on('click', '.settingBtn4', function() {
	var feed_id = $(this).data('feed-id');
	var targetSetGroup = $('.setGroup4[data-feed-id="' + feed_id + '"]');

	if (targetSetGroup.css('display') == 'none') {
		targetSetGroup.show();
	} else if (targetSetGroup.css('display') == 'block') {
		targetSetGroup.hide();
	}
});

// 피드 수정 버튼 누르면 나오는 수정 팝업1 글=======================================================
$(document).on('click', '.modifyBtn1', function() {
	var feed_id = $(this).closest('ul').data('feed-id');
	var modifyPopupLayer = $('#modifyPopupLayer[data-feed-id="' + feed_id + '"]');
	var setGroup1 = $('.setGroup1[data-feed-id="' + feed_id + '"]');
	modifyPopupLayer.show();
	setGroup1.hide();

});

$(document).on('click', '#modifyNO', function() {
	var feed_id = $(this).closest('#modifyPopupLayer').data('feed-id');
	$('#modifyPopupLayer[data-feed-id="' + feed_id + '"]').css('display', 'none');
});
// ========================================================================================

// 피드 수정 버튼 누르면 나오는 수정 팝업2 업무=======================================================
$(document).on('click', '.mprocesshover', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('.mprocesshover').removeClass('hover'); // 모든 버튼에서 hover 클래스 제거
	$(this).addClass('hover'); // 클릭한 버튼에만 hover 클래스 추가
});

$(document).on('click', '.mpriorityhover', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('.mpriorityhover').removeClass('hover'); // 모든 버튼에서 hover 클래스 제거
	$(this).addClass('hover'); // 클릭한 버튼에만 hover 클래스 추가
});

$(document).on('click', '.mprogresshover', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('.mprogresshover').removeClass('hover'); // 모든 버튼에서 hover 클래스 제거
	$(this).addClass('hover'); // 클릭한 버튼에만 hover 클래스 추가
});

$(document).on('click', '.STmprocesshover', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('.STmprocesshover').removeClass('hover'); // 모든 버튼에서 hover 클래스 제거
	$(this).addClass('hover'); // 클릭한 버튼에만 hover 클래스 추가
});

$(document).on('click', '.mStpriorityhover', function(event) {
	event.stopPropagation(); // 이벤트 전파 중지
	$('.mStpriorityhover').removeClass('hover'); // 모든 버튼에서 hover 클래스 제거
	$(this).addClass('hover'); // 클릭한 버튼에만 hover 클래스 추가
});

$(document).on('click', '.modifyBtn2', function() {
	var feed_id = $(this).closest('ul').data('feed-id');
	var modifyWorkPopup = $('#modifyWorkPopup[data-feed-id="' + feed_id + '"]');
	var setGroup2 = $('.setGroup2[data-feed-id="' + feed_id + '"]');
	modifyWorkPopup.show();
	setGroup2.hide();

});

$(document).on('click', '#modifyWorkNO', function() {
	var feed_id = $(this).closest('#modifyWorkPopup').data('feed-id');
	$('#modifyWorkPopup[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

// ========================================================================================

// 피드 수정 버튼 누르면 나오는 수정 팝업3 일정=======================================================
$(document).on('click', '.modifyBtn3', function() {
	var feed_id = $(this).closest('ul').data('feed-id');
	var modifySchedulePopup = $('#modifySchedulePopup[data-feed-id="' + feed_id + '"]');
	var setGroup3 = $('.setGroup3[data-feed-id="' + feed_id + '"]');
	modifySchedulePopup.show();
	setGroup3.hide();

});

$(document).on('click', '#modifyScheNO', function() {
	var feed_id = $(this).closest('#modifySchedulePopup').data('feed-id');
	$('#modifySchedulePopup[data-feed-id="' + feed_id + '"]').css('display', 'none');
});
// ========================================================================================

// 피드 수정 버튼 누르면 나오는 수정 팝업4 할일=======================================================
$(document).on('click', '.modifyBtn4', function() {
	var feed_id = $(this).closest('ul').data('feed-id');
	var modifyTodoPopupLayer = $('#modifyTodoPopupLayer[data-feed-id="' + feed_id + '"]');
	var setGroup4 = $('.setGroup4[data-feed-id="' + feed_id + '"]');
	modifyTodoPopupLayer.show();
	setGroup4.hide();
});

$(document).on('click', '#modifyTodoNO', function() {
	var feed_id = $(this).closest('#modifyTodoPopupLayer').data('feed-id');
	$('#modifyTodoPopupLayer[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$('.add-worker-ok').on('click', function(){
	$('.sub-worker-wrap').hide();
});

// ========================================================================================