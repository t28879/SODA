$(document).on('click', '#revealbtn1', function() {
	$('#modifycmpname').css('display', 'block');
	$('#subbuttonDiv1').css('display', 'block');
	$('#revealbtn1').hide();
});
$(document).on('click', '#revealbtn2', function() {
	$('#modifycmpurl').css('display', 'block');
	$('#subbuttonDiv2').css('display', 'block');
	$('#revealbtn2').hide();
});
$(document).on('click', '#cncl1', function() {
	$('#modifycmpname').css('display', 'none');
	$('#subbuttonDiv1').css('display', 'none');
	$('#revealbtn1').css('display', 'block');
});
$(document).on('click', '#cncl2', function() {
	$('#modifycmpurl').css('display', 'none');
	$('#subbuttonDiv2').css('display', 'none');
	$('#revealbtn2').css('display', 'block');
});
$(document).on('click', '#comNamebtn', function() {
	var userNum = 1;
	$('#modifycmpname').css('display', 'none');
	$('#subbuttonDiv1').css('display', 'none');
	$('#revealbtn1').css('display', 'block');
	var newcompanyname = $('#modifycmpname').val();
	$.ajax({
		type: "GET",
		url: "/updateCompanyName",
		data: { value: newcompanyname, userNum: userNum },
		success: function() {
			$('#comnameDH').text(newcompanyname);
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '#comUrlbtn', function() {
	var userNum = 1;
	$('#modifycmpurl').css('display', 'none');
	$('#subbuttonDiv2').css('display', 'none');
	$('#revealbtn2').css('display', 'block');
	var newcompanyurl = $('#modifycmpurl').val();
	$.ajax({
		type: "GET",
		url: "/updateCompanyUrl",
		data: { value: newcompanyurl, userNum: userNum },
		success: function() {
			$('#comurl').text(newcompanyurl);
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '#test_btn1', function() {
	$('tr#BannedRow').hide();
	$('tr#NormalRow').show();
});
$(document).on('click', '#test_btn2', function() {
	$('tr#NormalRow').hide();
	$('tr#BannedRow').show();
});
$(document).on('click', '#cancelchDH', function() {
	$('#li2pop').hide();
});
$(document).on('click', '#memtDHTable tbody tr td:nth-child(-n+5)', function() {
	var name = $(this).closest('tr').find('td:first-child span').text();
	var dept_name = $(this).closest('tr').find('td:nth-child(2) span').text();
	var job_name = $(this).closest('tr').find('td:nth-child(3) span').text();
	var email = $(this).closest('tr').find('td:nth-child(4) span').text();
	var phone_num = $(this).closest('tr').find('td:nth-child(5) span').text();
	$('#memnamDH').attr('placeholder', name);
	$('#memdepDH').attr('placeholder', dept_name);
	$('#memjobnamDH').attr('placeholder', job_name);
	$('#memphoDH').attr('placeholder', phone_num);
	$('#mememailDH').text(email);
	$('#li2pop').show();
});
$(document).on('click', '#savechDH', function() {
	var user_email = $('#mememailDH').text();
	var targetdiv = $('.targetdiv:contains("' + user_email + '")').closest('tr');
	var name = $('#memnamDH').val();
	var dept_name = $('#memdepDH').val();
	var job_name = $('#memjobnamDH').val();
	var phone_num = $('#memphoDH').val();
	if (name == '') name = $('#memnamDH').attr('placeholder');
	if (dept_name == '') dept_name = $('#memdepDH').attr('placeholder');
	if (job_name == '') job_name = $('#memjobnamDH').attr('placeholder');
	if (phone_num == '') phone_num = $('#memphoDH').attr('placeholder');
	$.ajax({
		type: "GET",
		url: "/WorkerManage/modify",
		data: { user_email: user_email, name: name, dept_name: dept_name, job_name: job_name, phone_num: phone_num },
		success: function() {
			targetdiv.find('td:eq(0) span').text(name);
			targetdiv.find('td:eq(1) span').text(dept_name);
			targetdiv.find('td:eq(2) span').text(job_name);
			targetdiv.find('td:eq(4) span').text(phone_num);
			$('#memnamDH').val('');
			$('#memdepDH').val('');
			$('#memjobnamDH').val('');
			$('#memphoDH').val('');
			$('#li2pop').hide();
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '#usagediv', function() {
	$('#surecommit').show();
	var member_id = $(this).closest('tr').data('member-id');
	var Banor = $(this).data('ban-or');
	var targetTr = $('tr[data-member-id="' + member_id + '"]');
	var usagediv = targetTr.find('#usagediv');

	$(document).on('click', '#submitDH', function() {
		$.ajax({
			type: "GET",
			url: "/WorkerManage/banor",
			data: { member_id: member_id, Banor: Banor },
			success: function() {
				if (Banor == 1) {
					targetTr.find('td:eq(6) a').text('정상');
					usagediv.text('[이용중지]').data('ban-or', 0);
				}
				else if (Banor == 0) {
					targetTr.find('td:eq(6) a').text('이용중지');
					usagediv.text('[이용재개]').data('ban-or', 1);
				}
				$('#surecommit').hide();

			},
			error: function(error) {
				console.error("업데이트 실패", error);
			}
		});
	});

});

$(document).on('click', '#YNdiv', function() {
	$('#admincommit').show();
	var member_id = $(this).closest('tr').data('member-id');
	var YorN = $(this).data('manager');
	var targetTr = $('tr[data-member-id="' + member_id + '"]');
	var YNdiv = targetTr.find('#YNdiv');
	$(document).on('click', '#submitDH2', function() {
		$.ajax({
			type: "GET",
			url: "/WorkerManage/modifymanager",
			data: { member_id: member_id, YorN: YorN },
			success: function() {
				if (YorN == 1) {
					targetTr.find('td:eq(7) a').text('Y');
					YNdiv.text('[삭제]').data('manager', 0);
				}
				else if (YorN == 0) {
					targetTr.find('td:eq(7) a').text('N');
					YNdiv.text('[추가]').data('manager', 1);
				}
				$('#admincommit').hide();

			},
			error: function(error) {
				console.error("업데이트 실패", error);
			}
		});
	});

});
$(document).on('click', '#cancelDH', function() {
	$('#surecommit').hide();
	$('#admincommit').hide();
});
function getCategoryIndex(selectedtype) {
	switch (selectedtype) {
		case '이름':
			return 0;
		case '부서':
			return 1;
		case '직책':
			return 2;
		case '이메일':
			return 3;
		case '휴대폰':
			return 4;
		default:
			return -1;
	}
}
$(document).on('click', '#memtDHsubmit', function() {
	var selectedtype = $('#searchfDH').val();
	var searchText = $('#memtDHsearch').val();
	$('#memtDHTable tbody tr').hide();

	$('#memtDHTable tbody tr').each(function() {
		var tdText = $(this).find('td:eq(' + getCategoryIndex(selectedtype) + ')').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});
});
$(document).on('click', '.tablet1DH tbody tr', function() {
	var project_id = $(this).data('project-id');
	$('#moreadmin').removeData('project');
	$.ajax({
		url: "/getprojectadmin",
		method: "GET",
		data: { project_id: project_id },
		dataType: "json",
		success: function(response) {
			$('#projectadminSpace').empty();
			if (response.length == 0) {
				$.ajax({
					url: "/getprojectinfo",
					method: "GET",
					data: { project_id: project_id },
					dataType: "json",
					success: function(info) {
						projectInfo = info;
						$('#getproname').attr('placeholder', projectInfo.name);
						$('#moreadmin').attr('data-project', projectInfo.project_id);
					},
					error: function(error) {
						console.error("프로젝트 정보 불러오기 실패", error);
					}
				});
			}
			else {
				$('#getproname').attr('placeholder', response[0].projectname);
				$('#moreadmin').attr('data-project', response[0].project_id);
				for (var i = 0; i < response.length; i++) {
					var admin = response[i];
					$('#projectadminSpace').append(
						'<tr data-member-id = "' + admin.member_id + '">' +
						'<td>' + admin.name + '</td>' +
						'<td>' + admin.deptname + '</td>' +
						'<td>' + admin.email + '</td>' +
						'<td id ="fireadmin">관리자 해제</td>' +
						'</tr>'
					);
				}
			}
			$('#projectedit').show();
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '#cancelprobtn', function() {
	$('#projectedit').hide();
	$('#moreadmin').removeData('project');
});

$(document).on('click', '#NOCancel,#OKsubmit', function() {
	$('#preadminlist').hide();
	$('#memchDH').val('');
});
$(document).on('click', '#moreadmin', function() {
	var project_id = $(this).data('project');
	$.ajax({
		type: "GET",
		url: "/getpremanagerlist",
		data: { project_id: project_id },
		dataType: "json",
		success: function(response) {
			$('#projectadminchooseSpace').empty();
			for (var i = 0; i < response.length; i++) {
				$('#projectadminchooseSpace').append(

					'<tr data-member = "' + response[i].member_id + '">' +
					'<td>' + response[i].name + '</td>' +
					'<td>' + response[i].deptname + '</td>' +
					'<td>' + response[i].email + '</td>' +
					'<td id ="hireadmin">관리자 추가</td>' +
					'</tr>'
				);
			}
			$('#preadminlist').show();
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
	$('#preadminlist').show();
});
$(document).on('click', '#submitanyDH', function() {
	var selectedtype = $('#memchoice').val();
	var searchText = $('#memchDH').val();
	$('#choosewhoDH tbody tr').hide();

	$('#choosewhoDH tbody tr').each(function() {
		var tdText = $(this).find('td:eq(' + getCateIndex(selectedtype) + ')').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});

});

$(document).on('click', '#hireadmin', function() {
	var member_id = $(this).closest('tr').data('member');
	var name = $(this).closest('tr').find('td:eq(0)').text();
	var deptname = $(this).closest('tr').find('td:eq(1)').text();
	var email = $(this).closest('tr').find('td:eq(2)').text();
	var check = 0;
	$('#projectadminSpace tr').each(function() {
		var tdText = $(this).find('td:eq(2)').text();
		if (tdText === email) {
			check = 1;

		}
	});
	if (check == 1) {
		$('#alreadyexist').css('display', 'flex');
		setTimeout(function() {
			$('#alreadyexist').fadeOut(1500);
		}, 2000);
	}
	else if (check == 0) {
		$('#appendcom').css('display', 'flex');
		setTimeout(function() {
			$('#appendcom').fadeOut(1500);
		}, 2000);
		$('#projectadminSpace').append(
			'<tr data-member-id = "' + member_id + '">' +
			'<td>' + name + '</td>' +
			'<td>' + deptname + '</td>' +
			'<td>' + email + '</td>' +
			'<td id ="fireadmin">관리자 해제</td>' +
			'</tr>'
		);
	}
});
function getCateIndex(selectedtype) {
	switch (selectedtype) {
		case '이름':
			return 0;
		case '이메일':
			return 2;
		default:
			return -1;
	}
}
$(document).on('click', '#fireadmin', function() {
	$(this).closest('tr').remove();
});
$(document).on('click', '#saveprobtn', function() {
	var nameList = [];
	var showadmins = "";
	var project_id = $('#moreadmin').data('project');
	var projectname = $('#getproname').val();
	if (projectname == '') projectname = $('#getproname').attr('placeholder');
	var LastList = {
		projectname: projectname,
		adList: [],
		project_id: project_id
	};
	$('#projectadminSpace tr').each(function() {
		if ($(this).data('member-id')) {
			var member_id = $(this).data('member-id');
			LastList.adList.push(member_id);
			var tdText = $(this).find('td:eq(0)').text();
			nameList.push(tdText);
		}
	});
	if (nameList.length > 1) {
		showadmins = nameList[0] + '외' + (nameList.length - 1) + '명';
	}
	else if (nameList.length == 1) {
		showadmins = nameList[0];
	}
	else {
		showadmins = '없음';
	}
	$.ajax({
		url: '/UpdateAdmin',
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(LastList),
		success: function() {
			$('tr[data-project-id="' + project_id + '"]:eq(0)').find('td:eq(0)').text(projectname);
			$('tr[data-project-id="' + project_id + '"]:eq(0)').find('td:eq(1)').text(showadmins);
			$('#projectedit').hide();
			$('#getproname').val('');
		},
		error: function(error) {
			console.error('전송 실패', error);
		}
	});
});
$(document).on('click', '.findPRO', function() {
	var searchText = $('#selectPro').val();
	$('.tablet1DH tbody tr').hide();

	$('.tablet1DH tbody tr').each(function() {
		var tdText = $(this).find('td:eq(0)').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});
});
$(document).on('click', '#searchstDH', function() {
	var selectedtype = $('#statisticoption').val();
	var searchText = $('#statisticDH').val();
	$('#tableforstatic tbody tr').hide();

	$('#tableforstatic tbody tr').each(function() {
		var tdText = $(this).find('td:eq(' + getstatisticIndex(selectedtype) + ')').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});

});
$(document).on('click', '#searchstDH2', function() {
	var selectedtype = $('#statisticoption2').val();
	var searchText = $('#statisticDH2').val();
	$('#tableforstatic2 tbody tr').hide();

	$('#tableforstatic2 tbody tr').each(function() {
		var tdText = $(this).find('td:eq(' + getstatisticIndex(selectedtype) + ')').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});

});
function getstatisticIndex(selectedtype) {
	switch (selectedtype) {
		case '이름':
			return 0;
		case '부서':
			return 1;
		case '직책':
			return 2;
		default:
			return -1;
	}
}
$(document).on('click', '#addIP', function() {
	$('#IpAddDiv').show();
});
$(document).on('click', '#cancleSaveBtn', function() {
	$('#IpAddDiv').hide();
});
$(document).on('click', '#saveIPBtn', function() {
	var ipexplain;
	var ipaddress = $('#IProom').val();
	if ($('#IPexplain').val()) {
		ipexplain = $('#IPexplain').val();
	}
	else {
		ipexplain = ' ';
	}
	$.ajax({
		url: '/IpSave',
		method: 'POST',
		data: {
			ipaddress: ipaddress,
			ipexplain: ipexplain
		},
		dataType: 'json',
		success: function(response) {
			if (response.ipaddress === null) {
				$('#IPexist').css('display', 'flex');
				setTimeout(function() {
					$('#IPexist').fadeOut(1500);
				}, 2000);
			}
			else {
				$('#t1ofDH tbody').append(
					'<tr data-company=' + response.company_id + '>'
					+ '<td>' + response.ipaddress + '</td>'
					+ '<td>' + ipexplain + '</td>'
					+ '<td>' + response.up_date + '</td>'
					+ '<td>' + response.email + '<img class = "DelPng" id = "DelPng" src="images/trashcan.png"></td>'
					+ '</tr>'
				);
				ipaddress = '';
				ipexplain = '';
				$('#IProom').val('');
				$('#IpAddDiv').hide();

				$('#IPsuccess').css('display', 'flex');
				setTimeout(function() {
					$('#IPsuccess').fadeOut(1500);
				}, 2000);

			}
		},
		error: function(error) {
			console.error('서블릿 호출 중 오류 발생:', error);
		}
	});
});
$(document).on('click', '#saveIPBtnLOG', function() {
	var ipexplain;
	var ipaddress = $('#IProomLOG').val();
	if ($('#IPexplainLOG').val()) {
		ipexplain = $('#IPexplainLOG').val();
	}
	else {
		ipexplain = ' ';
	}
	$.ajax({
		url: '/IpLoginSave',
		method: 'POST',
		data: {
			ipaddress: ipaddress,
			ipexplain: ipexplain
		},
		dataType: 'json',
		success: function(response) {
			if (response.ipaddress === null) {
				$('#IPexist').css('display', 'flex');
				setTimeout(function() {
					$('#IPexist').fadeOut(1500);
				}, 2000);
			}
			else {
				$('#t1ofDHLOG tbody').append(
					'<tr data-company=' + response.company_id + '>'
					+ '<td>' + response.ipaddress + '</td>'
					+ '<td>' + ipexplain + '</td>'
					+ '<td>' + response.up_date + '</td>'
					+ '<td>' + response.email + '<img class = "DelPng" id = "DelLOGPng" src="images/trashcan.png"></td>'
					+ '</tr>'
				);
				ipaddress = '';
				ipexplain = '';
				$('#IProomLOG').val('');
				$('#IPexplainLOG').val('');

				$('#IpAddDiv').hide();

				$('#IPsuccess').css('display', 'flex');
				setTimeout(function() {
					$('#IPsuccess').fadeOut(1500);
				}, 2000);

			}
		},
		error: function(error) {
			console.error('서블릿 호출 중 오류 발생:', error);
		}
	});
});
$(document).on('click', '#companyIPONOFF,#exceptionONOFF', function() {
	var member_id = 1;
	var type;
	var onoff;
	if ($(this).is('#companyIPONOFF')) {
		type = 1;
	} else if ($(this).is('#exceptionONOFF')) {
		type = 2;
	}

	if ($(this).text().trim() === '활성화') {
		onoff = 1;
	}
	else if ($(this).text().trim() === '비활성화') {
		onoff = 0;
	}
	$.ajax({
		url: '/DownloadIPOnOff',
		method: 'POST',
		data: { member_id: member_id, type: type, onoff: onoff },
		success: function() {
			if (type == 1) {
				if (onoff == 1) {
					$('#companyIPONOFF').css('background-color', 'pink');
					$('#companyIPONOFF').text('비활성화');
				}
				else {
					$('#companyIPONOFF').css('background-color', 'lightblue');
					$('#companyIPONOFF').text('활성화');
				}
			}
			else if (type == 2) {
				if (onoff == 1) {
					$('#exceptionONOFF').css('background-color', 'pink');
					$('#exceptionONOFF').text('비활성화');
				}
				else {
					$('#exceptionONOFF').css('background-color', 'lightblue');
					$('#exceptionONOFF').text('활성화');
				}
			}
		},
		error: function(error) {
			console.error('전송 실패', error);
		}
	});
});
$(document).on('click', '#companyLOGIPONOFF,#LOGexceptionONOFF', function() {
	var member_id = 1;
	var type;
	var onoff;
	if ($(this).is('#companyLOGIPONOFF')) {
		type = 1;
	} else if ($(this).is('#LOGexceptionONOFF')) {
		type = 2;
	}

	if ($(this).text().trim() === '활성화') {
		onoff = 1;
	}
	else if ($(this).text().trim() === '비활성화') {
		onoff = 0;
	}
	$.ajax({
		url: '/LoginIPOnOff',
		method: 'POST',
		data: { member_id: member_id, type: type, onoff: onoff },
		success: function() {
			if (type == 1) {
				if (onoff == 1) {
					$('#companyLOGIPONOFF').css('background-color', 'pink');
					$('#companyLOGIPONOFF').text('비활성화');
				}
				else {
					$('#companyLOGIPONOFF').css('background-color', 'lightblue');
					$('#companyLOGIPONOFF').text('활성화');
				}
			}
			else if (type == 2) {
				if (onoff == 1) {
					$('#LOGexceptionONOFF').css('background-color', 'pink');
					$('#LOGexceptionONOFF').text('비활성화');
				}
				else {
					$('#LOGexceptionONOFF').css('background-color', 'lightblue');
					$('#LOGexceptionONOFF').text('활성화');
				}
			}
		},
		error: function(error) {
			console.error('전송 실패', error);
		}
	});
});
$(document).on('click', '#searchExceBtn', function() {
	var selectedtype = $('#searchExcepMem').val();
	var searchText = $('#searchExceText').val();
	$('.wthat').hide();
	if (selectedtype == '이름') {
		$('.watchname span').each(function() {
			var tdText = $(this).text();
			if (tdText.includes(searchText)) {
				$(this).closest('.wthat').show();
			}
		});
	}
	else if (selectedtype == '부서') {
		$('.watchdept').each(function() {
			var tdText = $(this).text();
			if (tdText.includes(searchText)) {
				$(this).closest('.wthat').show();
			}
		});
	}
	else if (selectedtype == '이메일') {
		$('.watchmail span').each(function() {
			var tdText = $(this).text();
			if (tdText.includes(searchText)) {
				$(this).closest('.wthat').show();
			}
		});
	}
	$('#tooSEE tbody tr').each(function() {
		var tdText = $(this).find('td:eq(' + getstatisticIndex(selectedtype) + ')').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});

});
var selMems = [];
var selMemId = [];
$(document).on('click', '#ADDIP', function() {
	$('#GetSomeMem').show();
});
$(document).on('click', '#More', function() {
	var company_id = $('.wthat').data('cmp');
	for (var i = 0; i <= selMemId.length - 1; i++) {
		var specificDiv = $('.wthat[data-member-id="' + selMemId[i] + '"]');
		var name = specificDiv.find('.watchname span').text();
		var email = specificDiv.find('.watchmail span').text();
		var currentDate = new Date();
		var formattedDate = currentDate.toLocaleString().slice(0, 16).replace(', ', 'T');
		$('.checktable tbody').append('<tr data-company=' + company_id + '>' +
			'<td>' + name + '</td>' +
			'<td>' + email + '</td>' +
			'<td>' + formattedDate + '<img class = "DelPng2" id = "DelPng2" src="images/trashcan.png"></td>' +
			'</tr>');
	}
	$.ajax({
		url: '/UpdateIPMem',
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(selMemId),
		success: function() {
			$('.wthat').css('background-color', 'white');
			$('input[type="checkbox"]').prop('checked', false);
			$('input[type="text"]').val('');
			$('#ForAllMem').empty();
			$('#tooSEE').show();
			$('#GetSomeMem').hide();
			selMems = [];
			selMemId = [];
			$('#memsuccess').css('display', 'flex');
			setTimeout(function() {
				$('#memsuccess').fadeOut(1500);
			}, 2000);
		},
		error: function(error) {
			console.error('서블릿 호출 중 오류 발생:', error);
		}
	});
});
$(document).on('click', '#watchcheck', function() {
	console.log(selMemId);
	var value = $(this).siblings('.watchname').find('span').text();
	var email = $(this).siblings('.watchmail').find('span').text();
	var forbreak = 0;
	var meD = $(this).closest('.wthat').data('member-id');
	if ($(this).prop('checked')) {
		$('.checktable tbody tr').each(function() {
			var tdText = $(this).find('td:eq(1)').text();
			if (tdText.includes(email)) {
				forbreak = 1;
			}
		});
		if (forbreak == 1) {
			$('#memexist').css('display', 'flex');
			setTimeout(function() {
				$('#memexist').fadeOut(1500);
			}, 2000);
		}
		else if (forbreak == 0) {
			$(this).closest('#watchthis').css('background-color', 'lightblue');
			if (selMems.indexOf(value) === -1) {
				selMems.push(value);
				selMemId.push(meD);
			}
		}
	} else {
		$(this).closest('#watchthis').css('background-color', 'white');
		var index = selMems.indexOf(value);
		if (index !== -1) {
			selMems.splice(index, 1);
			selMemId.splice(index, 1);
		}
	}
	$('#ForAllMem').empty(); // 기존에 표시된 내용 비우기
	selMems.forEach(function(value) {
		$('#ForAllMem').append(value + ', ');
	});

});
$(document).on('click', '#MoreLOG', function() {
	var company_id = $('.wthat').data('cmp');
	for (var i = 0; i <= selMemId.length - 1; i++) {
		var specificDiv = $('.wthat[data-member-id="' + selMemId[i] + '"]');
		var name = specificDiv.find('.watchname span').text();
		var email = specificDiv.find('.watchmail span').text();
		var currentDate = new Date();
		var formattedDate = currentDate.toLocaleString().slice(0, 16).replace(', ', 'T');
		$('.checktable tbody').append('<tr data-company=' + company_id + '>' +
			'<td>' + name + '</td>' +
			'<td>' + email + '</td>' +
			'<td>' + formattedDate + '<img class = "DelPng2" id = "DelLOGPng2" src="images/trashcan.png"></td>' +
			'</tr>');
	}
	$.ajax({
		url: '/UpdateLoginIPMem',
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(selMemId),
		success: function() {
			$('.wthat').css('background-color', 'white');
			$('input[type="checkbox"]').prop('checked', false);
			$('input[type="text"]').val('');
			$('#ForAllMem').empty();
			$('#tooSEE').show();
			$('#GetSomeMem').hide();
			selMems = [];
			selMemId = [];
			$('#memsuccess').css('display', 'flex');
			setTimeout(function() {
				$('#memsuccess').fadeOut(1500);
			}, 2000);
		},
		error: function(error) {
			console.error('서블릿 호출 중 오류 발생:', error);
		}
	});
});
$(document).on('click', '#cancelMore', function() {
	$('.wthat').css('background-color', 'white');
	$('input[type="checkbox"]').prop('checked', false);
	$('input[type="text"]').val('');
	$('#ForAllMem').empty();
	$('#GetSomeMem').hide();
	$('.wthat').show();
});

$(document).on('click', '#DelPng,#DelPng2', function() {
	var company_id = $(this).closest('tr').data('company');
	var iptext;
	var type;
	if ($(this).is('#DelPng')) {
		type = 1;
		iptext = $(this).closest('tr').find('td:eq(0)').text();
	}
	else if ($(this).is('#DelPng2')) {
		type = 2;
		iptext = $(this).closest('tr').find('td:eq(1)').text();
	}
	$.ajax({
		url: '/DeleteIP',
		method: 'POST',
		data: { company_id: company_id, iptext: iptext, type: type },
		success: function() {
			if (type == 1) {
				$('#t1ofDH tbody tr').each(function() {
					var tdipaddress = $(this).find('td:eq(0)').text();
					if (tdipaddress.includes(iptext)) {
						$(this).remove();
					}
				});
			}
			else if (type == 2) {
				$('.checktable tbody tr').each(function() {
					var tdipaddress = $(this).find('td:eq(1)').text();
					if (tdipaddress.includes(iptext)) {
						$(this).remove();
					}
				});
			}

			$('#IPsuccess').text('삭제되었습니다');
			$('#IPsuccess').css('display', 'flex');
			setTimeout(function() {
				$('#IPsuccess').fadeOut(1500);
			}, 2000);
		},
		error: function(error) {
			console.error('서블릿 호출 중 오류 발생:', error);
		}
	});
});
$(document).on('click', '#DelLOGPng,#DelLOGPng2', function() {
	var company_id = $(this).closest('tr').data('company');
	var iptext;
	var type;
	if ($(this).is('#DelLOGPng')) {
		type = 1;
		iptext = $(this).closest('tr').find('td:eq(0)').text();
	}
	else if ($(this).is('#DelLOGPng2')) {
		type = 2;
		iptext = $(this).closest('tr').find('td:eq(1)').text();
	}
	$.ajax({
		url: '/DeleteLoginIP',
		method: 'POST',
		data: { company_id: company_id, iptext: iptext, type: type },
		success: function() {
			if (type == 1) {
				$('#t1ofDHLOG tbody tr').each(function() {
					var tdipaddress = $(this).find('td:eq(0)').text();
					if (tdipaddress.includes(iptext)) {
						$(this).remove();
					}
				});
			}
			else if (type == 2) {
				$('.checktable tbody tr').each(function() {
					var tdipaddress = $(this).find('td:eq(1)').text();
					if (tdipaddress.includes(iptext)) {
						$(this).remove();
					}
				});
			}

			$('#IPsuccess').text('삭제되었습니다');
			$('#IPsuccess').css('display', 'flex');
			setTimeout(function() {
				$('#IPsuccess').fadeOut(1500);
			}, 2000);
		},
		error: function(error) {
			console.error('서블릿 호출 중 오류 발생:', error);
		}
	});
});
$(document).on('click', '#companyWaterONOFF', function() {
	var member_id = 1;
	var innertext = $(this).text();
	var WaterMark;
	if (innertext === '비활성화') {
		WaterMark = 0;
	}
	else if (innertext === '활성화') {
		WaterMark = 1;
	}
	$.ajax({
		url: '/AlterWaterMark',
		method: 'POST',
		data: { WaterMark: WaterMark, member_id: member_id },
		success: function() {
			if (WaterMark == 0) {
				$('#companyWaterONOFF').css('background-color', 'lightblue');
				$('#companyWaterONOFF').text('활성화');
			}
			else if (WaterMark == 1) {
				$('#companyWaterONOFF').css('background-color', 'pink');
				$('#companyWaterONOFF').text('비활성화');
			}
		},
		error: function(error) {
			console.error('서블릿 호출 중 오류 발생:', error);
		}
	});
});
$(document).on('click', '#DownSearch', function() {
	var selectedtype = $('#SearchWhoD').val();
	var searchText = $('#SearchNameDown').val();
	$('#DownLOG tbody tr').hide();

	$('#DownLOG tbody tr').each(function() {
		var tdText = $(this).find('td:eq(' + getlogIndex(selectedtype) + ')').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});

});
function getlogIndex(selectedtype) {
	switch (selectedtype) {
		case '파일명':
			return 1;
		case '이름':
			return 3;
		case '이메일':
			return 3;
		default:
			return -1;
	}
}
$(document).on('click', '#Changsubmit', function() {
	var selectedtype = $('#searchChangeLog').val();
	var searchText = $('#SearchChange').val();
	$('#Changetable tbody tr').hide();

	$('#Changetable tbody tr').each(function() {
		var tdText = $(this).find('td:eq(' + getAdminIndex(selectedtype) + ')').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});

});
function getAdminIndex(selectedtype) {
	switch (selectedtype) {
		case '변경자':
			return 0;
		case 'ID':
			return 0;
		case '메뉴':
			return 1;
		case '기능':
			return 2;
		default:
			return -1;
	}
}
$(document).on('click', '#tobni', function() {
	$("#optionborder").show();
	$('input[type="checkbox"]').show();
});
$(document).on('click', '#closebtndh', function() {
	$("#optionborder").hide();
	$('input[type="checkbox"]').css('display', 'none');
	$('input[type="checkbox"]').css('background-color', 'none');
});
$(document).ready(function() {
	$('#choosecolo1').hover(function() {
		// 마우스를 올린 경우 호출되는 함수
		$(this).attr('src', 'images/choosecolorhover.png')
		$('#forbordoptions1').css('background-color', 'white');
	}, function() {
		// 마우스를 벗어난 경우 호출되는 함수
		$(this).attr('src', 'images/choosecolor.png')
		$('#forbordoptions1').css('background-color', '#6449fc');
	});
	/* 색상 설정 버튼 클릭 기능 */
	$('#choosecolo1').click(function() {
		var selectedProjects = $('input[name="project_check"]:checked');
		if (selectedProjects.length === 0) {
			$('#popup').show();
			setTimeout(function() {
				$('#popup').fadeOut(1500);
			}, 2000);
			return false;
		}

		if (selectedProjects.length >= 1) {
			$('#masterpalate').show();
			$('#wholebody').show();
		}

	});
	/* 프로젝트 폴더 설정 버튼 클릭 기능 */
	$('#choosecolo2').click(function() {
		var selectedProjects = $('input[name="project_check"]:checked');
		if (selectedProjects.length === 0) {
			$('#popup').show();
			setTimeout(function() {
				$('#popup').fadeOut(1500);
			}, 2000);
			return false;
		}

		if (selectedProjects.length >= 1) {
			$('#masterfolder').show();
			$('#wholebody').show();
		}

	});

	$('#choosecolo2').hover(function() {
		// 마우스를 올린 경우 호출되는 함수
		$(this).attr('src', 'images/projectfolderhover.png')
		$('#forbordoptions2').css('background-color', 'white');
	}, function() {
		// 마우스를 벗어난 경우 호출되는 함수
		$(this).attr('src', 'images/projectfolder.png')
		$('#forbordoptions2').css('background-color', '#6449fc');
	});

	$('.datepicker').change(function() {
		var feedorsub;
		var selectedDate = $(this).val();
		var selectid;
		var type = $(this).data('type');
		if ($(this).closest('tr').data('feed-id')) {
			feedorsub = 1;
			selectid = $(this).closest('tr').data('feed-id');
		}
		else {
			feedorsub = 2;
			selectid = $(this).closest('tr').data('sub-id');
		}
		$.ajax({
			type: "POST",
			url: "/UpdateFeedDate",
			data: { feedorsub: feedorsub, selectedDate: selectedDate, selectid: selectid, type: type },
			success: function() {
			},
			error: function(error) {
				console.error("업데이트 실패", error);
			}
		});


	});
	$('#hour, #minute').on('input', function() {
		var value = parseInt($(this).val());
		var min = parseInt($(this).attr('min'));
		var max = parseInt($(this).attr('max'));
		if (value < min) {
			$(this).val(min);
		}
		else if (value > max) {
			$(this).val(max);
		}
	});

});
$(document).on('click', '#cancelbtn', function() {
	$("#optionborder").hide();
	$('input[type="checkbox"]').css('display', 'none');
	$('input[type="checkbox"]').css('background-color', 'none');
	$('#masterpalate').hide();
	$('#wholebody').hide();
});
$(document).on('click', '#cancelbtn2', function() {
	$("#optionborder").hide();
	$('input[type="checkbox"]').css('display', 'none');
	$('input[type="checkbox"]').css('background-color', 'none');
	$('#masterfolder').hide();
	$('#wholebody').hide();
});
$(document).on('click', '#submitBtn', function(e) {
	e.preventDefault();
	var selectedDivs = [];
	var selectedColor = $("input[name='color_id']:checked").val();
	$('input[name="project_check"]:checked').each(function() {
		var projectId = $(this).closest('.flex-item').data('project_id');
		selectedDivs.push(projectId);
	});
	var color = $("input[name='color_id']:checked").closest('div').css("background-color");
	var ProjectList = {
		selectedColor: selectedColor,
		selectedDivs: selectedDivs
	};
	$.ajax({
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(ProjectList),
		url: "/UpdateProjectColor",
		success: function() {
			for (var i = 0; i <= selectedDivs.length - 1; i++) {
				$("div[data-project_id=" + selectedDivs[i] + "]").css('background', 'linear-gradient(to right, ' + color + ' 3%, transparent 5%)');
			}
		},
		error: function(error) {
			console.log("Error:", error);
		}

	});
	$("input[name='project_check']:checked").prop("checked", false);
	$("#optionborder").hide();
	$('input[type="checkbox"]').css('display', 'none');
	$('input[type="checkbox"]').css('background-color', 'none');
	$('#masterpalate').hide();
	$('#wholebody').hide();
});
$(document).on('click', '#submitBtn2', function(e) {
	e.preventDefault();
	var selectedDivs = [];
	var selectedFolder = [];
	$('input[name="project_check"]:checked').each(function() {
		var projectId = $(this).closest('.flex-item').data('project_id');
		selectedDivs.push(projectId);
	});
	$('input[name="ppbox"]:checked').each(function() {
		var folder_id = $(this).closest('.projectdumm').data('folder-id');
		selectedFolder.push(folder_id);
	});
	var ProjectList = {
		selectedFolder: selectedFolder,
		selectedDivs: selectedDivs
	};
	$.ajax({
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(ProjectList),
		url: "/UpdateProjectFolder",
		success: function() {


			/*for(var i = 0; i <= selectedDivs.length - 1; i++){
			$("div[data-project_id="+selectedDivs[i]+"]").css('background','linear-gradient(to right, '+color+' 3%, transparent 5%)'); 
		}*/
		},
		error: function(error) {
			console.log("Error:", error);
		}

	});
	$("input[name='project_check']:checked").prop("checked", false);
	$("#optionborder").hide();
	$('input[type="checkbox"]').css('display', 'none');
	$('input[type="checkbox"]').css('background-color', 'none');
	$('#masterfolder').hide();
	$('#wholebody').hide();
});
$(document).on('click', '.custom-select', function() {
	var customSelect = $(this);
	var dropdown = customSelect.find('.custom-dropdown');
	dropdown.toggle();
});
$(document).on('click', '.custom-dropdown-item', function() {
	var selectedimg = $(this).find('#processimg').data('img-num');
	var selectfeedid;
	var type;
	if ($(this).closest('tr').data('feed-id')) {
		selectfeedid = $(this).closest('tr').data('feed-id');
		type = 1;
	}
	else {
		selectfeedid = $(this).closest('tr').data('sub-id');
		type = 2;
	}
	var target = $(this).closest('.custom-select').find('.selected-item img');
	$.ajax({
		type: "POST",
		url: "/UpdateFeedProcess",
		data: { selectfeedid: selectfeedid, selectedimg: selectedimg, type: type },
		success: function(response) {
			target.attr('src', response);
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '.custom-select2', function() {
	var customSelect = $(this);
	var dropdown = customSelect.find('.custom-dropdown2');
	dropdown.toggle();
});
$(document).on('click', '.custom-dropdown-item2', function() {
	var selectedimg = $(this).find('#priorityimg').data('img-num');
	var selectfeedid;
	var type;
	if ($(this).closest('tr').data('feed-id')) {
		selectfeedid = $(this).closest('tr').data('feed-id');
		type = 1;
	}
	else if ($(this).closest('tr').data('sub-id')) {
		selectfeedid = $(this).closest('tr').data('sub-id');
		type = 2;
	}
	var target = $(this).closest('.custom-select2').find('.selected-item2 img');
	$.ajax({
		type: "POST",
		url: "/UpdateFeedPriority",
		data: { selectfeedid: selectfeedid, selectedimg: selectedimg, type: type },
		success: function(response) {
			target.attr('src', response);
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '#progressbar', function() {
	var dropdown = $(this).find('.custom-dropdown4');
	dropdown.toggle();
});
$(document).on('click', '.custom-dropdown4 div', function() {
	var selectfeedid;
	var text = $(this).text();
	var percentage = text.replace('%', '');
	var type;
	var target = $(this).closest('#wholeprogress');
	var targetspan = target.find('#testspan div');
	var targetp = target.find('#feed_progress_num span');
	if ($(this).closest('tr').data('feed-id')) {
		selectfeedid = $(this).closest('tr').data('feed-id');
		type = 1;
	}
	else {
		selectfeedid = $(this).closest('tr').data('sub-id');
		type = 2;
	}
	$.ajax({
		type: "POST",
		url: "/UpdateFeedProgress",
		data: { selectfeedid: selectfeedid, percentage: percentage, type: type },
		success: function(response) {
			targetspan.css('background-color', response);
			targetspan.css('width', percentage + '%');
			targetp.text(percentage);
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
var type;
var feedorsubID;
$(document).on('click', '.allworktable tbody tr td:nth-child(4)', function() {

	if ($(this).closest('tr').data('feed-id')) {
		feedorsubID = $(this).closest('tr').data('feed-id');
		type = 1;
	}
	else {
		feedorsubID = $(this).closest('tr').data('sub-id');
		type = 2;
	}
	$.ajax({
		url: "/GetWorkManager",
		method: "GET",
		data: { type: type, feedorsubID: feedorsubID },
		dataType: "json",
		success: function(response) {
			$('#ManagerSpace').empty();
			if (response.length > 0) {
				for (var i = 0; i < response.length; i++) {
					var admin = response[i];
					$('#ManagerSpace').append(
						'<tr data-member-id = "' + admin.worlmanagerid + '">' +
						'<td>' + admin.worlmanagername + '</td>' +
						'<td>' + admin.deptname + '</td>' +
						'<td>' + admin.email + '</td>' +
						'<td id ="firemanager">담당자 해제</td>' +
						'</tr>'
					);
				}
			}
			$('#adminsearchform').show();
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '#moremanager', function() {
	$.ajax({
		type: "GET",
		url: "/GetPreWorkManager",
		data: { type: type, feedorsubID: feedorsubID },
		dataType: "json",
		success: function(response) {
			$('#managerchooseSpace').empty();
			for (var i = 0; i < response.length; i++) {
				$('#managerchooseSpace').append(

					'<tr data-member = "' + response[i].worlmanagerid + '">' +
					'<td>' + response[i].worlmanagername + '</td>' +
					'<td>' + response[i].deptname + '</td>' +
					'<td>' + response[i].email + '</td>' +
					'<td id ="hiremanager">담당자 추가</td>' +
					'</tr>'
				);
			}
			$('#premanagerlist').show();
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});

$(document).on('click', '#cancelmanagerbtn', function() {
	$('#adminsearchform').hide();
	$('#premanagerlist').hide();
});
$(document).on('click', '#nomanager,#yesmanager', function() {
	$('#premanagerlist').hide();
});
$(document).on('click', '#getmanager', function() {
	var searchText = $('#writemanager').val();
	$('#premanagert tbody tr').hide();

	$('#premanagert tbody tr').each(function() {
		var tdText = $(this).find('td:eq(0)').text();
		if (tdText.includes(searchText)) {
			$(this).show();
		}
	});
});
$(document).on('click', '#hiremanager', function() {
	var member_id = $(this).closest('tr').data('member');
	var name = $(this).closest('tr').find('td:eq(0)').text();
	var deptname = $(this).closest('tr').find('td:eq(1)').text();
	var email = $(this).closest('tr').find('td:eq(2)').text();
	var check = 0;
	$('#ManagerSpace tr').each(function() {
		var tdText = $(this).find('td:eq(2)').text();
		if (tdText === email) {
			check = 1;

		}
	});
	if (check == 1) {
		$('#alreadyexist').css('display', 'flex');
		setTimeout(function() {
			$('#alreadyexist').fadeOut(1500);
		}, 2000);
	}
	else if (check == 0) {
		$('#appendcom').css('display', 'flex');
		setTimeout(function() {
			$('#appendcom').fadeOut(1500);
		}, 2000);
		$('#ManagerSpace').append(
			'<tr data-member-id = "' + member_id + '">' +
			'<td>' + name + '</td>' +
			'<td>' + deptname + '</td>' +
			'<td>' + email + '</td>' +
			'<td id ="firemanager">담당자 해제</td>' +
			'</tr>'
		);
	}
});
$(document).on('click', '#firemanager', function() {
	$(this).closest('tr').remove();
	$('#erasemanager').css('display', 'flex');
	setTimeout(function() {
		$('#erasemanager').fadeOut(1500);
	}, 2000);
});
$(document).on('click', '#savemangerbtn', function() {
	var managerlist = [];
	var managerIDlist = [];
	var fronttext;
	$('#ManagerSpace tr').each(function() {
		var tdText = $(this).find('td:eq(0)').text();
		var member_id = $(this).data('member-id');
		managerlist.push(tdText);
		managerIDlist.push(member_id);
	});
	if (type == 1) {
		if (managerlist.length > 1) {
			fronttext = managerlist[0] + '외' + (managerlist.length - 1) + '명';
		}
		else if (managerlist.length == 1) {
			fronttext = managerlist[0];
		}
		else { fronttext = '없음'; }
		var $tr = $('tr[data-feed-id="' + feedorsubID + '"]');
		var $td = $tr.find('td:eq(3) span');
		$td.text(fronttext);
	}
	else {
		if (managerlist.length > 1) {
			fronttext = managerlist[0] + '외' + (managerlist.length - 1) + '명';
		}
		else if (managerlist.length == 1) {
			fronttext = managerlist[0];
		}
		else fronttext = '없음';
		var $tr = $('tr[data-sub-id="' + feedorsubID + '"]');
		var $td = $tr.find('td:eq(3) span');
		$td.text(fronttext);
	}
	var allinfo = { feedorsubID: feedorsubID, type: type, managerIDlist: managerIDlist };

	$.ajax({
		url: '/UpdateWorkManager',
		method: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(allinfo),
		success: function() {
			$('#adminsearchform').hide();
			$('#premanagerlist').hide();
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '#cancel-button', function() {
	$('.schedule-form').hide();
	$('#schedule-title').val('');
	$('#minute').val('00');
	$('#hour').val('00');
});
function updateindi(date, hour, min, title, callback) {
	$.ajax({
		url: '/UpdateIndividualSchedule',
		method: 'POST',
		data: { date: date, hour: hour, min: min, title: title },
		success: function(response) {
			callback(response);
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
}
function deleteindi(individualID) {
	$.ajax({
		url: '/DeleteIndividualSchedule',
		method: 'POST',
		data: { individualID: individualID },
		success: function() {
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
}
const delete_elements = document.getElementsByClassName("deleteanswer");
Array.from(delete_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		if (confirm("정말로 삭제하시겠습니까?")) {
			location.href = this.dataset.uri;
		};
	});
});
$(document).on('click', '.answermodibtn', function() {
	var closediv = $(this).closest('.allanswerrapper');
	closediv.find('.answercontent').hide();
	$(this).closest('.deleteormodify').find('a').hide();
	$(this).closest('.deleteormodify').find('.answersubmitbtn').show();
	$(this).closest('.deleteormodify').find('.cancelsubmitbtn').show();
	closediv.find('textarea').show();
});
$(document).on('click', '.cancelsubmitbtn', function() {
	var closediv = $(this).closest('.allanswerrapper');
	closediv.find('.answercontent').show();
	$(this).closest('.deleteormodify').find('a').hide();
	$(this).closest('.deleteormodify').find('.answermodibtn').show();
	$(this).closest('.deleteormodify').find('.deleteanswer').show();
	closediv.find('textarea').hide();
});

$(document).on('click', '.answersubmitbtn', function() {
	var self = $(this);
	var closediv = self.closest('.allanswerrapper');
	var answer_id = self.data('answer-id');
	var content = self.closest('.allanswerrapper').find('textarea').val();
	if (content == '') {
		$('#nocontent').css('display', 'flex');
		setTimeout(function() {
			$('#nocontent').fadeOut(1500);
		}, 2000);
		return;
	}
	$.ajax({
		url: '/answer/modifyanswer',
		method: 'POST',
		data: { answer_id: answer_id, content: content },
		success: function() {
			closediv.find('.answercontent').text(content);
			closediv.find('.answercontent').show();
			closediv.find('.deleteormodify').find('a').hide();
			closediv.find('.deleteormodify').find('.answermodibtn').show();
			closediv.find('.deleteormodify').find('.deleteanswer').show();
			closediv.find('textarea').hide();
			self.closest('.allanswerrapper').find('textarea').val('');
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});
$(document).on('click', '.questionmodibtn', function() {
	$(this).closest('.deleteormodify').find('a').hide();
	$(this).siblings('.questionsubmitbtn').show();
	$(this).siblings('.cancelquestionbtn').show();
	$(this).closest('.forsib').siblings('textarea').show();
	$(this).closest('.forsib').siblings('.questioncontent').hide();
});
$(document).on('click', '.cancelquestionbtn', function() {
	$(this).closest('.deleteormodify').find('a').hide();
	$(this).siblings('.questionmodibtn').show();
	$(this).siblings('.deleteanswer').show();
	$(this).closest('.forsib').siblings('textarea').hide();
	$(this).closest('.forsib').siblings('.questioncontent').show();
});

$(document).on('click', '.questionsubmitbtn', function() {
	var self = $(this);
	var content = self.closest('.forsib').siblings('textarea').val();
	var question_id = self.data('question-id');
	if (content == '') {
		$('#nocontent').css('display', 'flex');
		setTimeout(function() {
			$('#nocontent').fadeOut(1500);
		}, 2000);
		return;
	}
	$.ajax({
		url: '/questionmodify',
		method: 'POST',
		data: { question_id: question_id, content: content },
		success: function() {
			self.closest('.deleteormodify').find('a').hide();
			self.siblings('.questionmodibtn').show();
			self.siblings('.deleteanswer').show();
			self.closest('.forsib').siblings('textarea').hide();
			self.closest('.forsib').siblings('.questioncontent').show();
			self.closest('.forsib').siblings('.questioncontent').text(content);
			self.closest('.forsib').siblings('textarea').val('');
		},
		error: function(error) {
			console.error("업데이트 실패", error);
		}
	});
});