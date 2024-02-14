// =========================================================검색바 검색기능 구현==============================================================
$(document).on('click', '#SearchCancle', function() {
	$('#SearchbarPopupLayer').hide();
	location.reload();
});

$(document).on('click', '#SearchCanclePost', function() {
	$('#SearchbarPopupLayerPost').hide();
	location.reload();
});

$(document).on('click', '#SearchCancleFile', function() {
	$('#SearchbarPopupLayerFile').hide();
	location.reload();
});


var mySearchType;
$('#cate-project, #cate-post, #cate-file').on('click', function() {
	mySearchType = $(this).data('search-code');
	console.log(mySearchType);
});

$('#searchBarBtn').on('click', function() {
	$('#popupMain').hide();

	var searchInput = $('#popupInput').val();

	var send_json_data = {
		"mySearchType": mySearchType.toString(),
		"searchInput": searchInput,
	};

	var jsondata = JSON.stringify(send_json_data);  //Json으로 변환
	$.ajax({
		type: "post",
		dataType: "json",
		contentType: 'application/json; charset=UTF-8',
		url: "/SODA/Searchbar",
		data: jsondata,

		success: function(response) {
			if (mySearchType == 31) {
				$('#SearchbarPopupLayer').show();

				$('#searchWord').text(searchInput);

				for (var i = 0; i < response.length; i++) {
					var type = response[i].type;
					var projectName = response[i].projectName;
					var project_id = response[i].project_id;
					console.log(response.length);

					$('#projectSearchArea').append('<a href="/SODA/Feed/' + project_id + '"><ul id="projectSearchResult" style="background: ' + type + '">' +
						'</ul>' +
						'</a>'+
						'<a href="/SODA/Feed/' + project_id + '"><div id="projectSearchItem">' +
						'<div id="searchProjectAll">' +
						' ' + projectName + ' ' +
						'</div>' +
						'</div>' +
						'</a>'+
						'<hr>');
				}

			} else if (mySearchType == 32) { // 글·댓글 검색 결과
				$('#SearchbarPopupLayerPost').show();

				$('#searchWordPost').text(searchInput);

				for (var i = 0; i < response.length; i++) {
					var title = response[i].title;
					var content = response[i].content;
					var memberName = response[i].memberName;
					var regist_date = response[i].regist_date;
					var projectName = response[i].projectName;
					var project_id = response[i].project_id;

					if (response[i].feed_type == 1) {
						$('#MSpostSearchResult').append('<li class="MSpost-search-item"><img src="/images/글.png" class="MSimg-post-type text">' +
							'<div class="MSsearch-sub-text-wrap">' +
							'<div class="MS-search-text-type-1">' +
							'<div class="MScontents-tit">' +
							'<a href="/SODA/Feed/' + project_id + '" class="MStask-title" style="font-size: 15px"> [글] <strong>' +
							' ' + title + ' </strong>' +
							'</a>' +
							'</div>' +
							'<div class="MSsearch-result-cont"> ' + content + ' </div>' +
							'</div>' +
							'<p class="MSname-date-projectName">' +
							'<span class="MSsearch-info"> <span class="MSsearch-name">' +
							' ' + memberName + '  </span> <span class="MSdate"> ' + regist_date + '' +
							' </span> <span title="구분선"> <img src="/images/구분선.png">' +
							'</span>' +
							'</span> <em class="MSproject-search-title"> <img src="/images/프로젝트이동.png"> ' +
							' ' + projectName + ' ' +
							'</em>' +
							'</p>' +
							'</div>' +
							'</li>' +
							'<hr>');
					} else if (response[i].feed_type == 2) {
						$('#MSpostSearchResult').append('<li class="MSpost-search-item"><img src="/images/업무.png" class="MSimg-post-type work">' +
							'<div class="MSsearch-sub-text-wrap">' +
							'<div class="MS-search-text-type-2">' +
							'<div class="MScontents-tit">' +
							'<a href="/SODA/Feed/' + project_id + '" class="MStask-title" style="font-size: 15px"> [업무] <strong>' +
							'' + title + '</strong>' +
							'</a>' +
							'</div>' +
							'<div class="MSsearch-result-cont">' + content + '</div>' +
							'</div>' +
							'<p class="MSname-date-projectName">' +
							'<span class="MSsearch-info"> <span class="MSsearch-name">' +
							'' + memberName + ' </span> <span class="MSdate"> ' + regist_date + '' +
							'</span> <span title="구분선"> <img src="/images/구분선.png">' +
							'</span>' +
							'</span> <em class="MSproject-search-title"> <img src="/images/프로젝트이동.png">' +
							'' + projectName + '' +
							'</em>' +
							'</p>' +
							'</div>' +
							'</li>' +
							'<hr>');

					} else if (response[i].feed_type == 3) {
						$('#MSpostSearchResult').append('<li class="MSpost-search-item"><img src="/images/일정.png" class="MSimg-post-type schedule">' +
							'<div class="MSsearch-sub-text-wrap">' +
							'<div class="MS-search-text-type-3">' +
							'<div class="MScontents-tit">' +
							'<a href="/SODA/Feed/' + project_id + '" class="MStask-title" style="font-size: 15px"> [일정] <strong>' +
							'' + title + '</strong>' +
							'</a>' +
							'</div>' +
							'<div class="MSsearch-result-cont">' + content + '</div>' +
							'</div>' +
							'<p class="MSname-date-projectName">' +
							'<span class="MSsearch-info"> <span class="MSsearch-name">' +
							'' + memberName + ' </span> <span class="MSdate"> ' + regist_date + ' ' +
							' </span> <span title="구분선"> <img src="/images/구분선.png">' +
							'</span>' +
							'</span> <em class="MSproject-search-title"> <img src="/images/프로젝트이동.png"> ' +
							'' + projectName + '' +
							'</em>' +
							'</p>' +
							'</div>' +
							'</li>' +
							'<hr>');

					} else if (response[i].feed_type == 4) {
						$('#MSpostSearchResult').append('<li class="MSpost-search-item"><img src="/images/할일.png" class="MSimg-post-type todo">' +
							'<div class="MSsearch-sub-text-wrap">' +
							'<div class="MS-search-text-type-4">' +
							'<div class="MScontents-tit">' +
							'<a href="/SODA/Feed/' + project_id + '" class="MStask-title" style="font-size: 15px"> [할 일] <strong>' +
							'' + title + '</strong>' +
							'</a>' +
							'</div>' +
							'<div class="MSsearch-result-cont">' + content + '</div>' +
							'</div>' +
							'<p class="MSname-date-projectName">' +
							'<span class="MSsearch-info"> <span class="MSsearch-name">' +
							'' + memberName + ' </span> <span class="MSdate"> ' + registDate + '' +
							' </span> <span title="구분선"> <img src="/images/구분선.png">' +
							'</span>' +
							'</span> <em class="MSproject-search-title"> <img src="/images/프로젝트이동.png">' +
							'' + projectName + '' +
							'</em>' +
							'</p>' +
							'</div>' +
							'</li>' +
							'<hr>');
					}

				}

			} else if (mySearchType == 33) {
				$('#SearchbarPopupLayerFile').show();

				$('#searchWordFile').text(searchInput);

				for (var i = 0; i < response.length; i++) {
					var fileNname = response[i].fileNname;
					var quantity = response[i].quantity;
					var memberName = response[i].memberName;
					var regist_date = response[i].regist_date;
					var projectName = response[i].projectName;
					var project_id = response[i].project_id;

					$('#MSfileSearchResult').append('<li class="MSfile-search-item"><img src="/images/파일사진.png" class="MSimg-file">' +
						'<div class="MSsearch-sub-text-wrap">' +
						'<a href="/SODA/Feed/' + project_id + '" class="MSsearch-text-type-file">' +
						'<p>' +
						'' + fileNname + ' <strong class="MSfile-name"></strong>' +
						'<em class="MSfile-quanti">' + quantity + '</em>' +
						'</p>' +
						'</a>' +
						'<p class="MSname-date-projectName">' +
						'<span class="MSsearch-info"> <span class="MSsearch-name">' +
						'' + memberName + ' </span> <span class="MSdate"> ' +
						'' + regist_date + '</span> <span title="구분선"> <img src="/images/구분선.png">' +
						'</span>' +
						'</span> <em class="MSproject-search-title"> <img src="/images/프로젝트이동.png">' +
						' ' + projectName + ' ' +
						'</em>' +
						'</p>' +
						'</div> <!-- 게시물 이동, 다운로드 -->' +
						'<div>' +
						'<button type="button" class="MSall-search-file-download"' +
						'style="position: relative;">' +
						'<span>게시물 이동</span>' +
						'</button>' +
						'<button type="button" class="MSall-search-file-download"' +
						'style="position: relative;">' +
						'<span>다운로드</span>' +
						'</button>' +
						'</div>' +
						'</li>' +
						'<hr>');
				}

			}

		},
		error: function(status, error) {
			alert("검색결과가 존재하지 않습니다. 다시 시도해 주세요.");
			location.reload(); // 페이지 새로고침
		}
	});
});

// ==========================================================================================================================================

// ===========================================================피드 팝업 기능 구현=================================================================

// 피드 팝업에서 각 탭에 대한 data code 받기
var myPostType;
$('.create-tab').on('click', function() {
	myPostType = $(this).data('post-code');
	console.log(myPostType);
});

// 피드 업무의 요청상태 data code 받기
var processCode;
$('.task-state-button').on('click', function() {
	processCode = $(this).data('work-process');
	console.log(processCode);
});

// 피드 업무의 우선순위 data code 받기
var priorityCode;
$('.ms-priority-setting-button').on('click', function() {
	priorityCode = $(this).data('work-priority');
	console.log(priorityCode);
});

// 피드 업무의 진척도 data code 받기
var progressCode;
$('.ms-progress-button').on('click', function() {
	progressCode = $(this).data('progress-value');
	console.log(progressCode);
});

// 피드 업무의 하위업무 요청 상태 data code 받기 
var subProcessCode;
$('.subtask-sts-data').on('click', function() {
	subProcessCode = $(this).data('subwork-process');
	console.log(subProcessCode);
});

// 피드 업무의 하위업무 우선순위 data code 받기
var subPriorityCode;
$('.subPrioData').on('click', function() {
	subPriorityCode = $(this).data('subwork-priority');
	console.log(subPriorityCode);
});

// 워크매니저 지정할 사람의 멤버 아이디
var selectedMembers = [];
// 피드 업무 업무매니저 체크박스 반복
$('.modi-add-worker-ok').on('click', function() {
	$("#workManagerCheck:checked").each(function() {
		var member_id = $(this).closest('li').data('workermanager-code');
		selectedMembers.push(member_id);
	});
	console.log(selectedMembers);
	$('.add-all-manager').hide();
});

// INSERT 하위업무 담당자 멤버 아이디 배열
var selectSubMembers = [];
$('#st-addWorkerOk').on('click', function() {
	$(".st-workManagerCheck:checked").each(function() {
		var member_id = $(this).closest('li').data('subworker-code');
		selectSubMembers.push(member_id);
	});
	console.log(selectSubMembers);
	$('.st-add-all-manager').hide();
});

$('#popupSubmit').on('click', function() {
	$('#postPopup').hide();

	// 공통 사용 title,content 값
	var titleInput = $('#postTitle').val();
	var contentInput = $('#textInput').val();
	var type;

	// 임시 값
	var project_id = $('.js-complete-btn').data('project-id');

	if (myPostType == 91) {
		type = 1;
		var send_json_data = {
			"type": type,
			"titleInput": titleInput,
			"contentInput": contentInput,
			"project_id": project_id
		};

		var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

		$.ajax({
			type: "Post",
			//dataType: "json",
			contentType: 'application/json; charset=UTF-8',
			url: "/CreateFeed",
			data: jsondata,
			success: function(response) {
				alert("글 생성 완료");
				location.reload();
			},
			error: function(status, error) {
				alert("글 생성 실패.");
				location.reload(); // 페이지 새로고침
			}
		});

	} else if (myPostType == 92) {
		type = 2;
		// 요청 상태 값 지정
		var process;
		if (processCode == 71) {
			process = 1;
		} else if (processCode == 72) {
			process = 2;
		} else if (processCode == 73) {
			process = 3;
		} else if (processCode == 74) {
			process = 4;
		} else if (processCode == 75) {
			process = 5;
		}

		// 업무 시작일, 마감일 값 받기
		var start_date = $('#MSscheduleStartDate').val();
		var deadline = $('#MSscheduleEndDate').val();

		// 우서 순위 값 지정
		var priority;
		if (priorityCode == 41) {
			priority = 1;
		} else if (priorityCode == 42) {
			priority = 2;
		} else if (priorityCode == 43) {
			priority = 3;
		} else if (priorityCode == 44) {
			priority = 4;
		}

		// 진척도 값 지정
		var progress;
		if (progressCode == 10) {
			progress = 10;
		} else if (progressCode == 20) {
			progress = 20;
		} else if (progressCode == 30) {
			progress = 30;
		} else if (progressCode == 40) {
			progress = 40;
		} else if (progressCode == 50) {
			progress = 50;
		} else if (progressCode == 60) {
			progress = 60;
		} else if (progressCode == 70) {
			progress = 70;
		} else if (progressCode == 80) {
			progress = 80;
		} else if (progressCode == 90) {
			progress = 90;
		} else if (progressCode == 100) {
			progress = 100;
		}


		// 하위 업무 요청 상태 값 지정
		var subProcess;
		if (subProcessCode == 11) {
			subProcess = 1;
		} else if (subProcessCode == 12) {
			subProcess = 2;
		} else if (subProcessCode == 13) {
			subProcess = 3;
		} else if (subProcessCode == 14) {
			subProcess = 4;
		} else if (subProcessCode == 15) {
			subProcess = 5;
		}

		// 하위 업무 title input 받기
		var subtaskInput = $('.MSsubtask-input').val();

		// 하위 업무 마감일 input 받기
		var subdeadline = $('.MSsubtask-endDate-input').val();

		// 하위 업무 우선순위 값 지정
		var subPriority;
		if (subPriorityCode == 81) {
			subPriority = 1;
		} else if (subPriorityCode == 82) {
			subPriority = 2;
		} else if (subPriorityCode == 83) {
			subPriority = 3;
		} else if (subPriorityCode == 84) {
			subPriority = 4;
		}

		// 피드 하위 업무 진척도는 default로 0
		var subProgress = 0;
		// 피드 하위 업무 시작일 default 0
		var subStart_date = null;

		var send_json_data = {
			"type": type,
			"titleInput": titleInput,
			"contentInput": contentInput,
			"process": process,
			"start_date": start_date,
			"deadline": deadline,
			"priority": priority,
			"progress": progress,
			"subProcess": subProcess,
			"subtaskInput": subtaskInput,
			"subdeadline": subdeadline,
			"subPriority": subPriority,
			"project_id": project_id,
			"subProgress": subProgress,
			"subStart_date": subStart_date,
			"selectedMembers": selectedMembers,
			"selectSubMembers": selectSubMembers
		};

		var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

		$.ajax({
			type: "Post",
			//dataType: "json",
			contentType: 'application/json; charset=UTF-8',
			url: "/CreateFeed",
			data: jsondata,
			success: function(response) {
				selectedMembers = [];
				selectSubMembers = [];
				alert("업무 생성 완료");
				location.reload();
			},
			error: function(status, error) {
				alert("업무 생성 실패.");
				location.reload(); // 페이지 새로고침
			}
		});

	} else if (myPostType == 93) {
		type = 3;
		// 일정의 시작일, 마김일 값 받기
		var start_date = $('#MSdatePicker1').val();
		var deadline = $('#MSdatePicker2').val();

		// 일정의 참석, 불참, 미정
		var go = 0;
		var no = 0;
		var thinking = 0;

		var send_json_data = {
			"type": type,
			"titleInput": titleInput,
			"contentInput": contentInput,
			"start_date": start_date,
			"deadline": deadline,
			"project_id": project_id,
			"go": go,
			"no": no,
			"thinking": thinking
		};

		var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

		$.ajax({
			type: "Post",
			//dataType: "json",
			contentType: 'application/json; charset=UTF-8',
			url: "/CreateFeed",
			data: jsondata,
			success: function(response) {
				alert("일정 생성 완료");
				location.reload();
			},
			error: function(status, error) {
				alert("일정 생성 실패.");
				location.reload(); // 페이지 새로고침
			}
		});

	} else if (myPostType == 94) {
		type = 4;
		// 할일의 내용 값 받기
		var todoInput = $('.todo-text').val();

		// 할일의 마감일 받기
		var deadline = $('#MStodoInput').val();

		// 할일 진척도 default 0
		var finish = 0;

		var send_json_data = {
			"type": type,
			"titleInput": titleInput,
			"todoInput": todoInput,
			"deadline": deadline,
			"project_id": project_id,
			"finish": finish
		};

		var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

		$.ajax({
			type: "Post",
			//dataType: "json",
			contentType: 'application/json; charset=UTF-8',
			url: "/CreateFeed",
			data: jsondata,
			success: function(response) {
				alert("할일 생성 완료");
				location.reload();
			},
			error: function(status, error) {
				alert("할일 생성 실패.");
				location.reload(); // 페이지 새로고침
			}
		});

	}

});

// ==========================================================================================================================================

// =============================================================피드 댓글 구현===================================================================
$(document).on('keypress', '.comment-input', function(e) {
	if (e.which === 13) { // Enter 키의 keyCode는 13입니다.
		var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');
		var commentText = $(this).val(); // 현재 입력란에 대한 값을 가져오도록 수정

		$.ajax({
			type: "Post",
			url: "/CreateReply",
			data: {
				feed_id: feed_id,
				commentText: commentText
			},
			success: function(response) {
				alert(feed_id + "번 피드에 댓글이 달렸습니다.");
				location.reload();
			},
			error: function(xhr, status, error) {
				alert("실패하였습니다. 다시 시도해 주세요.");
				location.reload();
			}
		});

		// 댓글 작성 후 입력란 비우기
		$(this).val('');
	}
});

// ==========================================================================================================================================

// ======================================================프로젝트 색상 변경 구현===================================================================
var myProColorTypes;

$(document).on('click', '.ColorBtn', function() {
	myProColorTypes = $(this).data('projectcolor-code');
	console.log(myProColorTypes);
});

$(document).on('click', '#SHColorChangeOk', function() {

	var project_id = $('#SHColorChangeOk').data('project-id');

	$.ajax({
		url: '/UpdateProColor',
		method: 'POST',
		data: {
			myProColorTypes: myProColorTypes,
			project_id: project_id
		},
		success: function(response) {
			var projectColorArea = $('.project-color-area');
            projectColorArea.css('background', response);
		},
		error: function(error) {
			alert("색상 변경에 실패 했습니다. 다시 시도해 주세요");
			location.reload();
		}
	});
});

// ==========================================================================================================================================

// ======================================================프로젝트 폴더 추가 구현===================================================================
$(document).on('click', '#ProjectFolderOk', function() {
	var selectedFolder = $('#projectFolderContent .folderli.hover');

	//임의 값
	var project_id = $('#ProjectFolderOk').data('project-id');

	if (selectedFolder.length > 0) {
		var folderName = selectedFolder.text(); 

		// 서버로 전송
		$.ajax({
			url: '/InsertProFeedFolder', 
			method: 'POST',
			data: {
				folderName: folderName,
				project_id: project_id
			},
			success: function(response) {
				selectedFolder.empty();
			},
			error: function(error) {
				alert("폴더가 추가되지 못했습니다. 다시 시도해 주세요.");
				location.reload();
			}
		});
	} else {
		console.log('선택된 폴더가 없습니다.');
	}
});

// ==========================================================================================================================================

// ==================================================피드에서 프로젝트 나가기 구현===================================================================

$(document).on('click', '#projectOutOk', function() {
	// 임시값
	var project_id = $('#projectOutOk').data('project-id');

	$.ajax({
		url: '/DeleteProjectMember',
		method: 'POST',
		data: {
			project_id: project_id
		},
		success: function(response) {
			alert('프로젝트에서 나가졌습니다.');
			window.location.href='/SODA_DASHBOARD';
			//location.reload();
		},
		error: function(xhr, status, error) {
			alert('관리자가 한명이거나 인원이 한명이라면 나갈 수가 없습니다. 프로젝트를 삭제해 주세요.');
			location.reload();
		}
	});
});


// ==========================================================================================================================================

// ====================================================피드에서 프로젝트 삭제 구현==================================================================

$(document).on('click', '#projectDeleteOk', function() {
	
	var project_id = $('#projectDeleteOk').data('project-id');

	$.ajax({
		url: '/deleteProjectInFeed',
		method: 'POST',
		data: {
			project_id: project_id
		},
		success: function() {
			alert('프로젝트를 삭제했습니다.');
			//location.reload();
			window.location.href='/SODA_DASHBOARD';
		},
		error: function() {
			alert("관리자가 아닙니다. 접근 할 수 없습니다.");
			location.reload();
		}
	});

});


// ==========================================================================================================================================

// =========================================================피드 수정 기능 구현===================================================================

// 피드 글 수정
$(document).on('click', '#modifyOk', function() {
	// 팝업 none 먼저
	var feed_id = $(this).closest('#modifyPopupLayer').data('feed-id');
	$('#modifyPopupLayer[data-feed-id="' + feed_id + '"]').css('display', 'none');
	var targetdiv = $('#modifyPopupLayer[data-feed-id="' + feed_id + '"]');
	var feedModifyTitle = targetdiv.find('#modifyTitle').val();
	var feedModifyContent = targetdiv.find('#modifyContent').val();
	$.ajax({
		url: '/UpdateFeedText',
		method: 'Post',
		data: {
			feedModifyTitle: feedModifyTitle,
			feedModifyContent: feedModifyContent,
			feed_id: feed_id
		},
		success: function(response) {
			alert(feed_id + '번 피드 수정 성공!');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('피드 수정 실패. 다시 시도해 주세요.');
		}
	});

});

// 피드 업무 수정 에서 각 받아오는 값들
// 수정할때 상태버튼 값
var myModiWorkProcessType;
$(document).on('click', '.mprocesshover', function() {
	myModiWorkProcessType = $(this).data('modifyprocess-code');
});

// 수정할때 우선순위 버튼 값
var myModiWorkPriorityType;
$(document).on('click', '.mpriorityhover', function() {
	myModiWorkPriorityType = $(this).data('modipriority-code');
});

// 수정할때 진척도 버튼 값
var myModiWorkProgressType;
$(document).on('click', '.mprogresshover', function() {
	myModiWorkProgressType = $(this).data('modiprogress-code');
});

// 수정할때 하위 업무 상태버튼
var myModiWorkSTProcessType;
$(document).on('click', '.STmprocesshover', function() {
	myModiWorkSTProcessType = $(this).data('modifystprocess-code');
});

// 수정할때 하위 업무 우선순위
var myModiWorkSTPriorityType;
$(document).on('click', '.mStpriorityhover', function() {
	myModiWorkSTPriorityType = $(this).data('modistpriority-code');
});

// 워크매니저 지정할 사람의 멤버 아이디
var modifyMembers = [];
// 피드 업무 업무매니저 체크박스 반복
$('.host-add-work-ok').on('click', function() {
	$("#hostWorkCheck:checked").each(function() {
		var member_id = $(this).closest('li').data('modifyworkmanger-code');
		modifyMembers.push(member_id);
	});
	console.log(modifyMembers);
	$('.host-add-all-manager').hide();
});

//하위 업무 매니저 지정할 사람의 멤버 아이디
var subModifyMembers = [];
$('.sub-host-add-work-ok').on('click', function() {
	$('.sub-host-work-check:checked').each(function() {
		var member_id = $(this).closest('li').data('modifyworkmanger-code');
		subModifyMembers.push(member_id);
	});
	console.log(subModifyMembers);
	$('.sub-host-add-all-manager').hide();
});

// 피드 업무 수정
$(document).on('click', '#modifyWorkOk', function() {
	var feed_id = $(this).closest('#modifyWorkPopup').data('feed-id');
	$('#modifyWorkPopup[data-feed-id="' + feed_id + '"]').css('display', 'none');
	var sub_task_id = $(this).closest('.modify-work-popup').data('subtask-code');
	console.log(sub_task_id);

	var targetdiv = $('#modifyWorkPopup[data-feed-id="' + feed_id + '"]');
	var workModifyTitle = targetdiv.find('#modifyWorkTitle').val();
	var workModifyContent = targetdiv.find('#modifyWorkContent').val();
	var workModifyStart = targetdiv.find('#modifyWorkStart').val();
	var workModifyEnd = targetdiv.find('#modifyWorkEnd').val();
	var workModifyTitleST = targetdiv.find('#sTmodifyTitle').val();
	var workModifyEndST = targetdiv.find('#sTmodiDeadline').val();

	var send_json_data = {
		"feed_id": feed_id,
		"workModifyTitle": workModifyTitle,
		"workModifyContent": workModifyContent,
		"workModifyStart": workModifyStart,
		"workModifyEnd": workModifyEnd,
		"workModifyTitleST": workModifyTitleST,
		"workModifyEndST": workModifyEndST,
		"myModiWorkProcessType": myModiWorkProcessType,
		"myModiWorkPriorityType": myModiWorkPriorityType,
		"myModiWorkProgressType": myModiWorkProgressType,
		"myModiWorkSTProcessType": myModiWorkSTProcessType,
		"myModiWorkSTPriorityType": myModiWorkSTPriorityType,
		"modifyMembers": modifyMembers,
		"sub_task_id": sub_task_id,
		"subModifyMembers": subModifyMembers,
	};

	var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

	$.ajax({
		url: '/UpdateFeedWork',
		method: 'POST',
		//dataType: "json",
		contentType: 'application/json; charset=UTF-8',
		data: jsondata,
		success: function(response) {
			ModifyMembers = [];
			alert(feed_id + '번 피드 수정 성공!');
			location.reload();
		},
		error: function(xhr, status, error) {
			console.error("AJAX Error:", status, error, xhr.responseText);
			alert('피드 수정 실패. 다시 시도해 주세요.');
			location.reload();
		}
	});

});

// 피드 일정 수정
$(document).on('click', '#modifyScheOk', function() {
	var feed_id = $(this).closest('#modifySchedulePopup').data('feed-id');
	$('#modifySchedulePopup[data-feed-id="' + feed_id + '"]').css('display', 'none');
	var targetdiv = $('#modifySchedulePopup[data-feed-id="' + feed_id + '"]');
	var scheduleModifyTitle = targetdiv.find('#modifyScheTitle').val();
	var scheduleModifyStart = targetdiv.find('#modiScheStart').val();
	var scheduleModifyEnd = targetdiv.find('#modiScheEnd').val();
	var scheduleModifyContent = targetdiv.find('#modifyScheContent').val();

	console.log(scheduleModifyTitle);

	var send_json_data = {
		"feed_id": feed_id,
		"scheduleModifyTitle": scheduleModifyTitle,
		"scheduleModifyStart": scheduleModifyStart,
		"scheduleModifyEnd": scheduleModifyEnd,
		"scheduleModifyContent": scheduleModifyContent,
	};

	var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

	$.ajax({
		url: '/UpdateFeedSchedule',
		method: 'POST',
		data: jsondata,
		contentType: 'application/json; charset=UTF-8',
		success: function(response) {
			alert(feed_id + '번 피드 수정 성공!');
			location.reload();
		},
		error: function(xhr, status, error) {
			console.error('피드 수정 실패. 다시 시도해 주세요.', xhr, status, error);
			alert('피드 수정 실패. 다시 시도해 주세요.' + xhr + '1 ' + status + '2 ' + error);
		}
	});

});

// 피드 할일 수정
$(document).on('click', '#modifyTodoOk', function() {
	var feed_id = $(this).closest('#modifyTodoPopupLayer').data('feed-id');
	$('#modifyTodoPopupLayer[data-feed-id="' + feed_id + '"]').css('display', 'none');
	var targetdiv = $('#modifyTodoPopupLayer[data-feed-id="' + feed_id + '"]');
	var title = targetdiv.find('#modifyTodoTitle').val();
	var end = targetdiv.find('#modifyTodoFicker').val();
	var content = targetdiv.find('#modifyTodoContent	').val();

	var send_json_data = {
		"feed_id": feed_id,
		"title": title,
		"end": end,
		"content": content,
	};

	var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

	$.ajax({
		url: '/UpdateFeedTodo',
		method: 'POST',
		contentType: 'application/json; charset=UTF-8',
		data: jsondata,
		success: function(response) {
			alert(feed_id + '번 피드 수정 성공!');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('피드 수정 실패. 다시 시도해 주세요.');
			location.reload();
		}
	});
});

// 피드 할일 체크박스 값 주기
$(document).on('change', '.todo-checkbox', function() {
	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');
	var checkbox = $('.MSpost-card-wrap[data-feed-id="' + feed_id + '"]').find('.todo-checkbox');
	var isChecked = checkbox.prop('checked') ? 1 : 0;

	$.ajax({
		url: '/UpdateTodoChek',
		method: 'POST',
		data: {
			feed_id: feed_id,
			isChecked: isChecked
		},
		success: function(response) {
			$('.todo-cnt').text(1);
			$('.todo-header-right').text('100%');
			$('.todo-percent-bar').css('background' , '#00FF7F');
			$('.todo-checkbox[type="checkbox"]').prop('checked', true);
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 담당자에게 문의 해주세요.');
			location.reload();
		}
	});
});


// ==========================================================================================================================================

// =========================================================피드 삭제 기능 구현===================================================================

// 피드 글 삭제---------------------------------------------------
$(document).on('click', '.deleteBtn1', function() {
	var feed_id = $(this).closest('ul').data('feed-id');
	var feedDeletePopupText = $('#feedDeletePopupText[data-feed-id="' + feed_id + '"]');
	var setGroup1 = $('.setGroup1[data-feed-id="' + feed_id + '"]');
	feedDeletePopupText.show();
	setGroup1.hide();
});

$(document).on('click', '#feedDeleteNoText', function() {
	var feed_id = $(this).closest('#feedDeletePopupText').data('feed-id');
	$('#feedDeletePopupText[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$(document).on('click', '#feedDeleteOkText', function() {
	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');
	$('#feedDeletePopupText[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$(document).on('click', '#feedDeleteOkText', function() {
	var feed_id = $(this).closest('#feedDeletePopupText').data('feed-id');
	$('#feedDeletePopupText[data-feed-id="' + feed_id + '"]').css('display', 'none');


	$.ajax({
		url: '/DeleteFeedText',
		method: 'POST',
		data: {
			feed_id: feed_id
		},
		success: function(response) {
			alert(feed_id + '번 피드 삭제 성공.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 담당자에게 문의 해주세요.');
			location.reload();
		}
	});
});

// ------------------------------------------------------------------
// 피드 업무 삭제---------------------------------------------------
$(document).on('click', '.deleteBtn2', function() {
	var feed_id = $(this).closest('ul').data('feed-id');
	var feedDeletePopupWork = $('#feedDeletePopupWork[data-feed-id="' + feed_id + '"]');
	var setGroup2 = $('.setGroup2[data-feed-id="' + feed_id + '"]');
	feedDeletePopupWork.show();
	setGroup2.hide();
});

$(document).on('click', '#feedDeleteNoWork', function() {
	var feed_id = $(this).closest('#feedDeletePopupWork').data('feed-id');
	$('#feedDeletePopupWork[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$(document).on('click', '#feedDeleteOkWork', function() {
	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');
	$('#feedDeletePopupWork[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$(document).on('click', '#feedDeleteOkWork', function() {
	var feed_id = $(this).closest('#feedDeletePopupWork').data('feed-id');
	$('#feedDeletePopupWork[data-feed-id="' + feed_id + '"]').css('display', 'none');


	$.ajax({
		url: '/DeleteFeedWork',
		method: 'POST',
		data: {
			feed_id: feed_id
		},
		success: function(response) {
			alert(feed_id + '번 피드 삭제 성공.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 담당자에게 문의 해주세요.');
			location.reload();
		}
	});
});

// 피드 일정 삭제---------------------------------------------------
$(document).on('click', '.deleteBtn3', function() {
	var feed_id = $(this).closest('ul').data('feed-id');
	var feedDeletePopupSchedule = $('#feedDeletePopupSchedule[data-feed-id="' + feed_id + '"]');
	var setGroup3 = $('.setGroup3[data-feed-id="' + feed_id + '"]');
	feedDeletePopupSchedule.show();
	setGroup3.hide();
});

$(document).on('click', '#feedDeleteNoSchedule', function() {
	var feed_id = $(this).closest('#feedDeletePopupSchedule').data('feed-id');
	$('#feedDeletePopupSchedule[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$(document).on('click', '#feedDeleteOkSchedule', function() {
	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');
	$('#feedDeletePopupSchedule[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$(document).on('click', '#feedDeleteOkSchedule', function() {
	var feed_id = $(this).closest('#feedDeletePopupSchedule').data('feed-id');
	$('#feedDeletePopupSchedule[data-feed-id="' + feed_id + '"]').css('display', 'none');


	$.ajax({
		url: '/DeleteFeedSchedule',
		method: 'POST',
		data: {
			feed_id: feed_id
		},
		success: function(response) {
			alert(feed_id + '번 피드 삭제 성공.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 담당자에게 문의 해주세요.');
			location.reload();
		}
	});
});

// 피드 할일 삭제---------------------------------------------------
$(document).on('click', '.deleteBtn4', function() {
	var feed_id = $(this).closest('ul').data('feed-id');
	var feedDeletePopupTodo = $('#feedDeletePopupTodo[data-feed-id="' + feed_id + '"]');
	var setGroup4 = $('.setGroup4[data-feed-id="' + feed_id + '"]');
	feedDeletePopupTodo.show();
	setGroup4.hide();
});

$(document).on('click', '#feedDeleteNoTodo', function() {
	var feed_id = $(this).closest('#feedDeletePopupTodo').data('feed-id');
	$('#feedDeletePopupTodo[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$(document).on('click', '#feedDeleteOkTodo', function() {
	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');
	$('#feedDeletePopupTodo[data-feed-id="' + feed_id + '"]').css('display', 'none');
});

$(document).on('click', '#feedDeleteOkTodo', function() {
	var feed_id = $(this).closest('#feedDeletePopupTodo').data('feed-id');
	$('#feedDeletePopupTodo[data-feed-id="' + feed_id + '"]').css('display', 'none');


	$.ajax({
		url: '/DeleteFeedTodo',
		method: 'POST',
		data: {
			feed_id: feed_id
		},
		success: function(response) {
			alert(feed_id + '번 피드 삭제 성공.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 담당자에게 문의 해주세요.');
			location.reload();
		}
	});
});

// ==========================================================================================================================================

// ======================================================피드 댓글 수 기능 구현===================================================================

// ==========================================================================================================================================

// =====================================================피드 좋아요 수 기능 구현===================================================================

$(document).on('click', '.MSpost-bottom-button1', function() {
	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');
	console.log(feed_id);
	$.ajax({
		url: '/ToggleLikey',
		method: 'POST',
		data: {
			feed_id: feed_id
		},
		success: function(response) {
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 관리자에게 문의 해주세요.');
			location.reload();
		}
	});

});

// ==========================================================================================================================================


// =========================================================북마크 추가 기능 구현==================================================================
$(document).on('click', '.MSpost-bottom-button2', function() {
	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');

	$.ajax({
		url: '/ToggleBookmark',
		method: 'POST',
		data: {
			feed_id: feed_id
		},
		success: function(response) {
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 담당자에게 문의 해주세요.');
			location.reload();
		}
	});

});

// ==========================================================================================================================================

// ===========================================================댓글 수정하기 기능 구현==============================================================
// 글의 댓글 수정
$(document).on('click', '.textOk', function() {
	var reply_id = $(this).closest('div').data('reply-id');
	var modifyTextReply = $('#modifyTextReply[data-reply-id="' + reply_id + '"]');
	modifyTextReply.show();
});


$(document).on('click', '#modiReplyNOPE', function() {
	var reply_id = $(this).closest('#modifyTextReply').data('reply-id');
	$('#modifyTextReply[data-reply-id="' + reply_id + '"]').css('display', 'none');
});

$(document).on('click', '#modiReplyOK', function() {
	var reply_id = $(this).closest('#modifyTextReply').data('reply-id');
	$('#modifyTextReply[data-reply-id="' + reply_id + '"]').css('display', 'none');
	var targetdiv = $('#modifyTextReply[data-reply-id="' + reply_id + '"]');
	var modifyReplyText = targetdiv.find('#modifyReplyText').val();
	console.log(reply_id);
	console.log(modifyReplyText);

	$.ajax({
		url: '/UpdateTextReply',
		method: 'POST',
		data: {
			reply_id: reply_id,
			modifyReplyText: modifyReplyText
		},
		success: function(response) {
			alert('댓글이 수정되었습니다. 확인해 주세요.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('수정은 본인만 할 수 있습니다.');
			location.reload();
		}
	});
});

// 업무의 댓글 수정
$(document).on('click', '.workOk', function() {
	var reply_id = $(this).closest('div').data('reply-id');
	var modifyWorkReply = $('#modifyWorkReply[data-reply-id="' + reply_id + '"]');
	modifyWorkReply.show();
});


$(document).on('click', '#modiReplyWorkNOPE', function() {
	var reply_id = $(this).closest('#modifyWorkReply').data('reply-id');
	$('#modifyWorkReply[data-reply-id="' + reply_id + '"]').css('display', 'none');
});

$(document).on('click', '#modiReplyWorkOK', function() {
	var reply_id = $(this).closest('#modifyWorkReply').data('reply-id');
	$('#modifyWorkReply[data-reply-id="' + reply_id + '"]').css('display', 'none');
	var targetdiv = $('#modifyWorkReply[data-reply-id="' + reply_id + '"]');
	var modifyReplyWork = targetdiv.find('#modifyReplyWork').val();

	$.ajax({
		url: '/UpdateWorkReply',
		method: 'POST',
		data: {
			reply_id: reply_id,
			modifyReplyWork: modifyReplyWork
		},
		success: function(response) {
			alert('댓글이 수정되었습니다. 확인해 주세요.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('수정은 본인만 할 수 있습니다.');
			location.reload();
		}
	});
});

// 일정의 댓글 수정
$(document).on('click', '.scheduleOk', function() {
	var reply_id = $(this).closest('div').data('reply-id');
	var modifySchedulekReply = $('#modifySchedulekReply[data-reply-id="' + reply_id + '"]');
	modifySchedulekReply.show();
});


$(document).on('click', '#modiReplyScheduleNOPE', function() {
	var reply_id = $(this).closest('#modifySchedulekReply').data('reply-id');
	$('#modifySchedulekReply[data-reply-id="' + reply_id + '"]').css('display', 'none');
});

$(document).on('click', '#modiReplyScheduleOK', function() {
	var reply_id = $(this).closest('#modifySchedulekReply').data('reply-id');
	$('#modifySchedulekReply[data-reply-id="' + reply_id + '"]').css('display', 'none');
	var targetdiv = $('#modifySchedulekReply[data-reply-id="' + reply_id + '"]');
	var modifyReplySchedule = targetdiv.find('#modifyReplySchedule').val();

	$.ajax({
		url: '/UpdateScheduleReply',
		method: 'POST',
		data: {
			reply_id: reply_id,
			modifyReplySchedule: modifyReplySchedule
		},
		success: function(response) {
			alert('댓글이 수정되었습니다. 확인해 주세요.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('수정은 본인만 할 수 있습니다.');
			location.reload();
		}
	});
});

// 할일의 댓글 수정
$(document).on('click', '.todoOk', function() {
	var reply_id = $(this).closest('div').data('reply-id');
	var modifyTodoReply = $('#modifyTodoReply[data-reply-id="' + reply_id + '"]');
	modifyTodoReply.show();
});


$(document).on('click', '#modiReplyTodoNOPE', function() {
	var reply_id = $(this).closest('#modifyTodoReply').data('reply-id');
	$('#modifyTodoReply[data-reply-id="' + reply_id + '"]').css('display', 'none');
});

$(document).on('click', '#modiReplyTodoOK', function() {
	var reply_id = $(this).closest('#modifyTodoReply').data('reply-id');
	$('#modifyTodoReply[data-reply-id="' + reply_id + '"]').css('display', 'none');
	var targetdiv = $('#modifyTodoReply[data-reply-id="' + reply_id + '"]');
	var modifyReplyTodo = targetdiv.find('#modifyReplyTodo').val();

	$.ajax({
		url: '/UpdateTodoReply',
		method: 'POST',
		data: {
			reply_id: reply_id,
			modifyReplyTodo: modifyReplyTodo
		},
		success: function(response) {
			alert('댓글이 수정되었습니다. 확인해 주세요.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('수정은 본인만 할 수 있습니다.');
			location.reload();
		}
	});
});

// ==========================================================================================================================================

// ===========================================================댓글 삭제하기 기능 구현==============================================================
// 글의 댓글 삭제
$(document).on('click', '.textDelete', function() {
	var reply_id = $(this).closest('div').data('reply-id');
	var deleteReplyText = $('#deleteReplyText[data-reply-id="' + reply_id + '"]');
	deleteReplyText.show();
});


$(document).on('click', '#deleteReplyNOPE', function() {
	var reply_id = $(this).closest('#deleteReplyText').data('reply-id');
	$('#deleteReplyText[data-reply-id="' + reply_id + '"]').css('display', 'none');
});

$(document).on('click', '#deleteReplyOK', function() {
	var reply_id = $(this).closest('#deleteReplyText').data('reply-id');
	$('#deleteReplyText[data-reply-id="' + reply_id + '"]').css('display', 'none');

	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');

	$.ajax({
		url: '/DeleteTextReply',
		method: 'POST',
		data: {
			reply_id: reply_id,
			feed_id: feed_id
		},
		success: function(response) {
			alert('댓글이 삭제 되었습니다.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('삭제는 본인만 할 수 있습니다.');
			location.reload();
		}
	});
});


// 업무의 댓글 삭제
$(document).on('click', '.workDelete', function() {
	var reply_id = $(this).closest('div').data('reply-id');
	var deleteReplyWork = $('#deleteReplyWork[data-reply-id="' + reply_id + '"]');
	deleteReplyWork.show();
});


$(document).on('click', '#deleteReplyNOPEWork', function() {
	var reply_id = $(this).closest('#deleteReplyWork').data('reply-id');
	$('#deleteReplyWork[data-reply-id="' + reply_id + '"]').css('display', 'none');
});

$(document).on('click', '#deleteReplyOKWork', function() {
	var reply_id = $(this).closest('#deleteReplyWork').data('reply-id');
	$('#deleteReplyWork[data-reply-id="' + reply_id + '"]').css('display', 'none');

	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');

	$.ajax({
		url: '/DeleteWorkReply',
		method: 'POST',
		data: {
			reply_id: reply_id,
			feed_id: feed_id
		},
		success: function(response) {
			alert('댓글이 삭제 되었습니다.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('삭제는 본인만 할 수 있습니다.');
			location.reload();
		}
	});
});

// 일정의 댓글 삭제
$(document).on('click', '.scheduleDelete', function() {
	var reply_id = $(this).closest('div').data('reply-id');
	var deleteReplySchedule = $('#deleteReplySchedule[data-reply-id="' + reply_id + '"]');
	deleteReplySchedule.show();
});


$(document).on('click', '#deleteReplyNOPESchedule', function() {
	var reply_id = $(this).closest('#deleteReplySchedule').data('reply-id');
	$('#deleteReplySchedule[data-reply-id="' + reply_id + '"]').css('display', 'none');
});

$(document).on('click', '#deleteReplyOKSchedule', function() {
	var reply_id = $(this).closest('#deleteReplySchedule').data('reply-id');
	$('#deleteReplySchedule[data-reply-id="' + reply_id + '"]').css('display', 'none');

	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');

	$.ajax({
		url: '/DeleteScheduleReply',
		method: 'POST',
		data: {
			reply_id: reply_id,
			feed_id: feed_id
		},
		success: function(response) {
			alert('댓글이 삭제 되었습니다.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('삭제는 본인만 할 수 있습니다.');
			location.reload();
		}
	});
});

// 할일의 댓글 삭제
$(document).on('click', '.todoDelete', function() {
	var reply_id = $(this).closest('div').data('reply-id');
	var deleteReplyTodo = $('#deleteReplyTodo[data-reply-id="' + reply_id + '"]');
	deleteReplyTodo.show();
});


$(document).on('click', '#deleteReplyNOPETodo', function() {
	var reply_id = $(this).closest('#deleteReplyTodo').data('reply-id');
	$('#deleteReplyTodo[data-reply-id="' + reply_id + '"]').css('display', 'none');
});

$(document).on('click', '#deleteReplyOKTodo', function() {
	var reply_id = $(this).closest('#deleteReplyTodo').data('reply-id');
	$('#deleteReplyTodo[data-reply-id="' + reply_id + '"]').css('display', 'none');

	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');

	$.ajax({
		url: '/DeleteTodoReply',
		method: 'POST',
		data: {
			reply_id: reply_id,
			feed_id: feed_id
		},
		success: function(response) {
			alert('댓글이 삭제 되었습니다.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('삭제는 본인만 할 수 있습니다.');
			location.reload();
		}
	});
});


// ==========================================================================================================================================

// =====================================================같은 회사원 프로젝트 초대 기능 구현===========================================================
$(document).on('click', '#inviteBtn', function() {
	$('#inviteWrap').css('display', 'none');
	var member_id = $(this).closest('ul').data('member-id');
	var project_id = $('#inviteBtn').data('project-id');
	
	$.ajax({
		url: '/InsertInvite',
		method: 'POST',
		data: {
			member_id: member_id,
			project_id: project_id
		},
		success: function(response) {
			alert('프로젝트에 초대했습니다.');
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 관리자에게 문의 해주세요.');
			location.reload();
		}
	});
});


// ==========================================================================================================================================

// ===============================================프로필 이미지 클릭 시 프로필 팝업 기능 구현===========================================================
$(document).off('click', '#thisProfile').on('click', '#thisProfile', function(event) {
	event.stopPropagation();
	var member_id = $(this).data('profile-code');
	console.log(member_id);
	$('#openProfilePopup').css('display', 'block');

	var send_json_data = {
		"member_id": member_id,
	};

	var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

	$.ajax({
		url: '/SelectMember',
		dataType: "json",
		contentType: 'application/json; charset=UTF-8',
		method: 'POST',
		data: jsondata,

		success: function(response) {
			$('.profilePopupIMG').attr('src', '/' + response.profile_image + '');

			// 이름 및 회사 정보 추가
			$('.profile-card-name').text(response.member_name);
			$('.profile-card-company').text(response.company_name);
			$('.makeCompany').text(response.company_name);

			// 연락처 정보 추가
			$('#profileEmail span').text(response.email);
			$('#profilePhonNo span').text(response.phone_num);
			$('#profileStatusMSG span').text(response.status_msg);

			// 회사 정보 추가
			$('.info-company-img').next().children('strong').text(response.company_name);
		},
		error: function(xhr, status, error) {
			alert('시스템 오류 관리자에게 문의 해주세요.');
			location.reload();
		}
	});
});

$(document).on('click', '.joiner-profile', function() {
	$('#allSendiencePopup').hide();
});

// ==========================================================================================================================================

// =================================================일정의 go, no, finish 기능 구현==============================================================
// gonothinking 구현

var myGoNoThinkingType;
$('.DHmiwar').on('click', function() {
	myGoNoThinkingType = $(this).data('gonothinking-code');
});

$(document).on('click', '.DHmiwar', function() {
	var feed_id = $(this).closest('.MSpost-card-wrap').data('feed-id');

	$.ajax({
		url: '/AllSchedule',
		method: 'POST',
		data: {
			feed_id: feed_id,
			myGoNoThinkingType: myGoNoThinkingType
		},
		success: function(response) {
			location.reload();
		},
		error: function(xhr, status, error) {
			alert('버튼은 중복으로 누를 수 없습니다.');
			console.log('xhr : ' + xhr + ' status : ' + status + ' error : ' + error);
		}
	});
});

// ==========================================================================================================================================


// =====================================select된 피드 업무의 하위업무 담당자 팝업 기능 구현==============================================================
$('.hostMSsubtask-manager-button').on('click', function() {
	var sub_task_id = $(this).closest('.hostMSsubtask-manager').data('subtask-code');

	var target = $(this).closest('.hostMSsubtask-manager').siblings('.sub-worker-wrap');
	target.show();

	var send_json_data = {
		"sub_task_id": sub_task_id,
	};

	var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

	$.ajax({
		url: '/SelectSubWorker',
		dataType: "json",
		contentType: 'application/json; charset=UTF-8',
		method: 'POST',
		data: jsondata,
		success: function(response) {
			if (response && response.length > 0) {
				$('.content-ul').empty();
				for (var i = 0; i < response.length; i++) {
					var profile_image = response[i].profile_image;
					var member_name = response[i].member_name;
					var company_name = response[i].company_name;

					$('.content-ul').append('<li class="select-subworker">' +
						'<div class="subworkers-section">' +
						'<div class="subworker-profile">' +
						'<img src="/' + profile_image + '" class="subworker-img">' +
						'</div>' +
						'<div class="users-info">' +
						'<div class="users-name">' +
						'<div class="users-worker-name">' +
						'' + member_name + '' +
						'</div>' +
						'</div>' +
						'<div class="users-compnay">' +
						'<div class="users-worker-company">' +
						'' + company_name + '' +
						'</div>' +
						'</div>' +
						'</div>' +
						'</div>' +
						'</li>');
				}
			} else if (response.length == 0) {
				$('.content-ul').empty();
				// 값이 없을 때 실행할 코드 추가
				$('.content-ul').append('<div>담당자가 존재하지 않습니다.</di>');
			}
		},
		error: function(xhr, status, error) {
			alert('nnnnnnnnnnn!' + xhr + 'zzzzz ' + status + 'vvvvvv' + error)
		}
	});

});

// ==========================================================================================================================================

// ===================================-----------=-=프로젝트 참여자 이름 검색 기능 구현======-========================================================
$(document).on('keypress', '#allProjectJoinerSearch', function() {
	var searchMember = $(this).val();

	var send_json_data = {
		"searchMember": searchMember,
	};

	var jsondata = JSON.stringify(send_json_data);  //Json으로 변환

	$.ajax({
		url: '/searchMember',
		method: 'POST',
		dataType: "json",
		contentType: 'application/json; charset=UTF-8',
		data: jsondata,
		success: function(response) {
			$('#joinerUl').empty();

			if (response && response.length == 1) {
				var member_name = response[0].member_name;
				var company_name = response[0].company_name;
				var admin = response[0].admin;
				var profile_image = response[0].profile_image;
				var member_id = response[0].member_id;

				$('#joinerUl').append('<li th:each="admins : ${admins}" class="user-profile">' +
					'<div class="joiner-author">' +
					'<img src="/' + profile_image + '" class="joiner-profile"  ' +
					'id="thisProfile" data-profile-code="' + member_id + '" >' +
					'<dl class="joiner-info">' +
					'<dt>' +
					'<strong id="joineame" class="joiner-name"><a' +
					'>' + member_name + '</a></strong>' +
					'</dt>' +
					'<dd style="display: flex">' +
					'<strong class="joiner-company"><a> ' + company_name + ' </a></strong>' +
					'</dd>' +
					'</dl>' +
					'</div >' +
					'<a style="' + (admin == 1 ? 'display: block;' : 'display: none;') + '" href="#"' +
					'class="manaer-badge">' +
					'<span>관리자</span>' +
					'</a>' +
					'</li > ');

			} else if (response && response.length > 1) {
				for (var i = 0; i < response.length; i++) {
					var member_name = response[i].member_name;
					var company_name = response[i].company_name;
					var admin = response[i].admin;
					var profile_image = response[i].profile_image;
					var member_id = response[i].member_id;

					$('#joinerUl').append('<li th:each="admins : ${admins}" class="user-profile">' +
					'<div class="joiner-author">' +
					'<img src="/' + profile_image + '" class="joiner-profile"  ' +
					'id="thisProfile" data-profile-code="' + member_id + '" >' +
					'<dl class="joiner-info">' +
					'<dt>' +
					'<strong id="joineame" class="joiner-name"><a' +
					'>' + member_name + '</a></strong>' +
					'</dt>' +
					'<dd style="display: flex">' +
					'<strong class="joiner-company"><a> ' + company_name + ' </a></strong>' +
					'</dd>' +
					'</dl>' +
					'</div >' +
					'<a style="' + (admin == 1 ? 'display: block;' : 'display: none;') + '" href="#"' +
					'class="manaer-badge">' +
					'<span>관리자</span>' +
					'</a>' +
					'</li > ');
				}
			}
		},
		error: function(xhr, status, error) {

		}
	});

});

// ==========================================================================================================================================