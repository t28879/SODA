document.getElementById("btnTab1").addEventListener("click", function() {
	document.getElementById("video").src = "/video/mainVideo1.mp4";
	document.getElementById("btnTab1").classList.add("btnOn");
	document.getElementById("btnTab2").classList.remove("btnOn");
	document.getElementById("btnTab3").classList.remove("btnOn");
	document.getElementById("btnTab4").classList.remove("btnOn");
	document.getElementById("btnTab5").classList.remove("btnOn");
	const txtComp3 = document.getElementById("txtComp");
	txtComp3.innerHTML = '<li><div class="bubble"><p>😢 사용전</p></div>' +
		'<p class="bbT">메일, 단톡방 소통의 한계 … 잦은 보고와 불필요한 회의만 늘어나요.</p>' +
		'</li><br><li><div class="bubble"><p>😍 사용후</p></div>' +
		'<p class="bbT">프로젝트 별로 모여 쉽게 공유하고 빠르고 효율적으로 협업해요.</p></li>';
});
document.getElementById("btnTab2").addEventListener("click", function() {
	document.getElementById("video").src = "/video/mainVideo2.mp4";
	document.getElementById("btnTab2").classList.add("btnOn");
	document.getElementById("btnTab1").classList.remove("btnOn");
	document.getElementById("btnTab3").classList.remove("btnOn");
	document.getElementById("btnTab4").classList.remove("btnOn");
	document.getElementById("btnTab5").classList.remove("btnOn");
	const txtComp3 = document.getElementById("txtComp");
	txtComp3.innerHTML = '<li><div class="bubble"><p>😢 사용전</p></div>' +
		'<p class="bbT">여기저기 밀려오는 업무 … 정신없는 마감일 관리로 리소스를 뺏겨요.</p>' +
		'</li><br><li><div class="bubble"><p>😍 사용후</p></div>' +
		'<p class="bbT">클릭 한 번에 업무 마감일, 진행률, 우선순위를 자동으로 관리해요.</p></li>';
});
document.getElementById("btnTab3").addEventListener("click", function() {
	document.getElementById("video").src = "/video/mainVideo3.mp4";
	document.getElementById("btnTab3").classList.add("btnOn");
	document.getElementById("btnTab1").classList.remove("btnOn");
	document.getElementById("btnTab2").classList.remove("btnOn");
	document.getElementById("btnTab4").classList.remove("btnOn");
	document.getElementById("btnTab5").classList.remove("btnOn");
	const txtComp3 = document.getElementById("txtComp");
	txtComp3.innerHTML = '<li><div class="bubble"><p>😢 사용전</p></div>' +
		'<p class="bbT">메신저로만 소통해서 중요한 업무 놓치거나 실시간 진행 상황을 알 수 없어요.</p>' +
		'</li><br><li><div class="bubble"><p>😍 사용후</p></div>' +
		'<p class="bbT">프로젝트 + 메신저를 통합하여 실시간 진행 공유와 효율적인 협업을 해요.</p></li>';
});
document.getElementById("btnTab4").addEventListener("click", function() {
	document.getElementById("video").src = "/video/mainVideo4.mp4";
	document.getElementById("btnTab4").classList.add("btnOn");
	document.getElementById("btnTab1").classList.remove("btnOn");
	document.getElementById("btnTab2").classList.remove("btnOn");
	document.getElementById("btnTab3").classList.remove("btnOn");
	document.getElementById("btnTab5").classList.remove("btnOn");
	const txtComp4 = document.getElementById("txtComp");
	txtComp4.innerHTML = '<li><div class="bubble"><p>😢 사용전</p></div>' +
		'<p class="bbT">회사의 자료는 쌓여가는데 체계적인 히스토리 자산화가 어려워요.</p>' +
		'</li><br><li><div class="bubble"><p>😍 사용후</p></div>' +
		'<p class="bbT">프로젝트 · 캠페인 별로 히스토리를 보관하고 무기한 검색이 가능해요.</p></li>';
});
document.getElementById("btnTab5").addEventListener("click", function() {
	document.getElementById("video").src = "/video/mainVideo5.mp4";
	document.getElementById("btnTab5").classList.add("btnOn");
	document.getElementById("btnTab1").classList.remove("btnOn");
	document.getElementById("btnTab2").classList.remove("btnOn");
	document.getElementById("btnTab3").classList.remove("btnOn");
	document.getElementById("btnTab4").classList.remove("btnOn");
	const txtComp5 = document.getElementById("txtComp");
	txtComp5.innerHTML = '<li><div class="bubble"><p>😢 사용전</p></div>' +
		'<p class="bbT">목표관리 체계가 없어서 공정한 업무 평가 기준을 정하기 어려워요.</p>' +
		'</li><br><li><div class="bubble"><p>😍 사용후</p></div>' +
		'<p class="bbT">OKR로 팀·개인 목표를 체계적으로 관리하고 성과 평가 기준을 세워요.</p></li>';
});