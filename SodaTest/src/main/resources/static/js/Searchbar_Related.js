//================================================검색바 js=======================================================

// 검색바 누르면 팝업 뜨게
$('#searchInput').on('click', function(){
	$('#popupMain').show();
});

// 검색팝업 닫을때
$(document).on('click', function (event) {
    var popupMain = $('#popupMain'); //검색바 팝업
    var searchInput = $('#searchInput');

    // 클릭한 요소가 팝업 내부가 아니라면 팝업을 닫음
    if (!popupMain.is(event.target) && popupMain.has(event.target).length === 0 && !searchInput.is(event.target)) {
        popupMain.hide();
    }
    
    $('.cancel-button').on('click', function(){
		$('#popupMain').hide();
	});
    
});

// 검색 팝업 안에 프로젝트, 글, 파일버튼 눌렀을 때 적용되게
$(document).ready(function () {
    $('#cate-project').on('click', function () {
        $('.hiddenPopup').css('display', 'flex');
        $('.category-buttons').css('display', 'none');
        $('#hidden-popup-change').text('프로젝트');
    });

    $('#cate-post').on('click', function () {
        $('.hiddenPopup').css('display', 'flex');
        $('.category-buttons').css('display', 'none');
        $('#hidden-popup-change').text('글');
    });

    $('#cate-file').on('click', function () {
        $('.hiddenPopup').css('display', 'flex');
        $('.category-buttons').css('display', 'none');
        $('#hidden-popup-change').text('파일');
    });

    $('#pointX').on('click', function () {
        $('.hiddenPopup').css('display', 'none');
        $('.category-buttons').css('display', 'flex');
        $('#categoryTitle').text('카테고리');
    });

    $('#pointX').on('mouseover', function () {
        $('#pointX').attr('src', '/images/HoverClose.png');
    });

    $('#pointX').on('mouseout', function () {
        $('#pointX').attr('src', '/images/Close.png');
    });

    $('#popupCloseButton').on('click', function () {
        $('#popupInput').val('');
        $('#popupCloseButton').css('display', 'none');
        
    });
    
    
});

//입력된 내용이 있을 때만 팝업 내의 닫기 이미지를 보이도록 설정
function toggleCloseButton() {
    var popupInput = $('#popupInput');
    var popupCloseButton = $('#popupCloseButton');

    // 입력된 내용이 있을 때만 팝업 내의 닫기 이미지를 보이도록 설정
    if (popupInput.val().trim() !== '') {
        popupCloseButton.show();
    } else {
        popupCloseButton.hide();
    }
}


//=======================================================================================================

