package com.mysite.Soda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.Soda.color.ColorVO;
import com.mysite.Soda.folder.FolderVO;
import com.mysite.Soda.member.MemberVO;
import com.mysite.Soda.projectfeed.ProjectAdminService;
import com.mysite.Soda.projectfeed.ProjectAdminVO;
import com.mysite.Soda.projectfeed.ProjectColorService;
import com.mysite.Soda.projectfeed.ProjectColorVO;
import com.mysite.Soda.projectfeed.ProjectFeedChartService;
import com.mysite.Soda.projectfeed.ProjectFeedMyprofileService;
import com.mysite.Soda.projectfeed.ProjectFeedMyprofileVO;
import com.mysite.Soda.projectfeed.ProjectFolderService;
import com.mysite.Soda.projectfeed.ProjectFolderVO;
import com.mysite.Soda.selectFeed.AllFeedSelectService;
import com.mysite.Soda.selectFeed.AllFeedSelectVO;
import com.mysite.Soda.selectFeed.FeedScheduleService;
import com.mysite.Soda.selectFeed.FeedScheduleVO;
import com.mysite.Soda.selectFeed.FeedTextService;
import com.mysite.Soda.selectFeed.FeedTextVO;
import com.mysite.Soda.selectFeed.FeedTodoService;
import com.mysite.Soda.selectFeed.FeedTodoVO;
import com.mysite.Soda.selectFeed.FeedWorkService;
import com.mysite.Soda.selectFeed.FeedWorkVO;
import com.mysite.Soda.selectFeedReply.FeedScheduleReplyService;
import com.mysite.Soda.selectFeedReply.FeedScheduleReplyVO;
import com.mysite.Soda.selectFeedReply.FeedTextReplyService;
import com.mysite.Soda.selectFeedReply.FeedTextReplyVO;
import com.mysite.Soda.selectFeedReply.FeedTodoReplyService;
import com.mysite.Soda.selectFeedReply.FeedTodoReplyVO;
import com.mysite.Soda.selectFeedReply.FeedWorkReplyService;
import com.mysite.Soda.selectFeedReply.FeedWorkReplyVO;
import com.mysite.Soda.selectInvite.SelectInviteService;
import com.mysite.Soda.selectInvite.SelectInviteVO;
import com.mysite.Soda.selectMembers.SelectMembersDTO;
import com.mysite.Soda.selectMembers.SelectMembersService;
import com.mysite.Soda.selectMembers.SelectMembersVO;
import com.mysite.Soda.selectProfile.SearchMemberDTO;
import com.mysite.Soda.selectProfile.SearchMemberService;
import com.mysite.Soda.selectProfile.SearchMemberVO;
import com.mysite.Soda.selectSearchbar.SearchDTO;
import com.mysite.Soda.selectSearchbar.SearchService;
import com.mysite.Soda.selectSearchbar.SearchVO;
import com.mysite.Soda.selectWorkmanager.ModifyWorkmanagerService;
import com.mysite.Soda.selectWorkmanager.ModifyWorkmanagerVO;
import com.mysite.Soda.selectWorkmanager.SelectFeedSubWorkerDTO;
import com.mysite.Soda.selectWorkmanager.SelectFeedSubWorkerService;
import com.mysite.Soda.selectWorkmanager.SelectFeedSubWorkerVO;
import com.mysite.Soda.selectWorkmanager.SelectSubWorkmanagerService;
import com.mysite.Soda.selectWorkmanager.SelectSubWorkmanagerVO;
import com.mysite.Soda.selectWorkmanager.SelectWorkmanagerService;
import com.mysite.Soda.selectWorkmanager.SelectWorkmanagerVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FeedController {

	private final ProjectColorService projectservice;
	private final ProjectFolderService projectfolderservice;
	private final ProjectFeedChartService projectfeedchartservice;
	private final ProjectAdminService projectadminservice;
	private final ProjectFeedMyprofileService projectfeedmyprofileservice;
	private final FeedTextService feedtextservice;
	private final FeedWorkService feedworkservice;
	private final FeedScheduleService feedscheduleservice;
	private final FeedTodoService feedtodoservice;
	private final FeedTextReplyService feedreplyservice;
	private final FeedWorkReplyService feedworkreplyservice;
	private final FeedScheduleReplyService feedschedulereplyservice;
	private final FeedTodoReplyService feedtodoreplyservice;
	private final SelectInviteService selectinviteservice;
	private final SelectWorkmanagerService selectworkmanagerservice;
	private final ModifyWorkmanagerService modifyworkmanagerservice;
	private final SelectSubWorkmanagerService selectsubworkmanagerservice;
	private final AllFeedSelectService allfeedselectservice;
	private final MainService mainservice;
	private final HttpSession session;
	
	// 프로젝트 ID
	private int pro_id;
	
	@GetMapping("/SODA/Feed/{project_id}")
	public String GetAllById(Model model,@PathVariable("project_id")int project_id) {
		pro_id = project_id;
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		// 피드 페이지에서 해당 프로젝트 색상
		ProjectColorVO projectlists = projectservice.projectID(project_id);
		model.addAttribute("projects", projectlists);

		// 피드 페이지에서 프로젝트 폴더 리스트 나타내기
		List<ProjectFolderVO> folderlists = projectfolderservice.projectID(project_id);
		model.addAttribute("folders", folderlists);

		// 피드에서 업무 통계 리스트 숫자로 뽑기
		List<Integer> chartcount = projectfeedchartservice.projectID(project_id);
		model.addAttribute("chartCount", chartcount.size());
		model.addAttribute("RequestCnt", Collections.frequency(chartcount, 1));
		model.addAttribute("ProgressCnt", Collections.frequency(chartcount, 2));
		model.addAttribute("FeedbackCnt", Collections.frequency(chartcount, 3));
		model.addAttribute("CompletionCnt", Collections.frequency(chartcount, 4));
		model.addAttribute("HoldCnt", Collections.frequency(chartcount, 5));

		// 프로젝트 참여자중 관리자인 사람과 아닌 사람 나타내는 팝업
		List<ProjectAdminVO> adminlists = projectadminservice.projectID(project_id);
		model.addAttribute("projectJoinCnt", adminlists.size());
		model.addAttribute("admins", adminlists);

		// 피드에서 내 정보 누르면 나오는 내 프로필 팝업
		ProjectFeedMyprofileVO profile = projectfeedmyprofileservice.memberID(userNum);
		model.addAttribute("profile", profile);

		// 피드 글 뽑기
		List<FeedTextVO> textlists = feedtextservice.projectID(project_id);
		model.addAttribute("textlists", textlists);

		// 피드 업무 뽑기
		List<FeedWorkVO> worklists = feedworkservice.projectID(project_id);
		model.addAttribute("worklists", worklists);

		// 피드 일정 뽑기
		List<FeedScheduleVO> schedulelists = feedscheduleservice.projectID(project_id);
		model.addAttribute("schedulelists", schedulelists);

		// 피드 할일 뽑기
		List<FeedTodoVO> todolists = feedtodoservice.projectID(project_id);
		model.addAttribute("todolists", todolists);
		
		// 피드 글의 댓글
		List<FeedTextReplyVO> textreplylists = feedreplyservice.projectID(project_id);
		model.addAttribute("textreplylists", textreplylists);
		
		// 피드 업무의 댓글
		List<FeedWorkReplyVO> workreplylists = feedworkreplyservice.projectID(project_id);
		model.addAttribute("workreplylists", workreplylists);

		// 피드 일정의 댓글
		List<FeedScheduleReplyVO> schedulereplylists = feedschedulereplyservice.projectID(project_id);
		model.addAttribute("schedulereplylists", schedulereplylists);

		// 피드 할일의 댓글
		List<FeedTodoReplyVO> todoreplylists = feedtodoreplyservice.projectID(project_id);
		model.addAttribute("todoreplylists", todoreplylists);

		// 프로젝트 초대 SELECT
		List<SelectInviteVO> invitelists = selectinviteservice.projectID(project_id, userNum);
		model.addAttribute("invitelists", invitelists);
		
		// 피드 업무에서 담당자할사람 select
		List<SelectWorkmanagerVO> workmanagers = selectworkmanagerservice.projectID(project_id);
		model.addAttribute("workmanagers", workmanagers);
		
		// 피드 업무 수정시 담당자 할사람 select
		List<ModifyWorkmanagerVO> modifyworkmanagers = modifyworkmanagerservice.projectID(project_id);
		model.addAttribute("modifyworkmanagers", modifyworkmanagers);
		
		// 하위 업무 담당자 select
		List<SelectSubWorkmanagerVO> subworkmanagers = selectsubworkmanagerservice.projectID(project_id);
		model.addAttribute("subworkmanagers", subworkmanagers);
		
		// 피드에서 피드아이디 뽑아서 피드에 추가된 담당자 뽑기
		List<List<FeedWorkVO>> allFeedDetails = new ArrayList<>();
		for (FeedWorkVO feedWork : worklists) {
		    int feed_id = feedWork.getFeed_id();
		    List<FeedWorkVO> feedDetails = feedworkservice.getFeedDetailsById(feed_id);
		    allFeedDetails.add(feedDetails);
		}
		model.addAttribute("allFeedDetails", allFeedDetails);
		
		List<AllFeedSelectVO> selectFeedlists = allfeedselectservice.ProjectID(project_id);
		model.addAttribute("selectFeedlists", selectFeedlists);
		
		model.addAttribute("project_id",pro_id);
		List<ColorVO> color = mainservice.getColor(userNum);
		model.addAttribute("projectWithColor", color);
		
		List<FolderVO> folder = mainservice.getFolder(userNum);
		model.addAttribute("folder", folder);
		
		MemberVO member = mainservice.getMember(userNum); 
		model.addAttribute("member",member);
		
		return "SODA_Feed.html";
	}
	
	
	// 검색 기능
	private final SearchService searchservice;

	@ResponseBody
	@PostMapping("/SODA/Searchbar")
	public List<SearchVO> search(@RequestBody SearchDTO result) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		// searchRequest에서 필요한 데이터 추출
		String mySearchType = result.getMySearchType();
		String searchInput = result.getSearchInput();
		// dataCode에 따라서 서로 다른 검색 메서드 호출
		switch (mySearchType) {
		case "31": // 프로젝트 검색
			return searchservice.searchProjects(searchInput, userNum);
		case "32": // 글 검색
			return searchservice.searchPosts(searchInput, userNum);
		case "33": // 파일 검색
			return searchservice.searchFiles(searchInput, userNum);
		default:
			// 기본적으로 어떤 검색을 할지에 대한 기본 처리
			return List.of();
		}
	}
	
	// member의 프로필
	private final SearchMemberService searchmemberservice;
	
	@ResponseBody
	@PostMapping("/SelectMember")
	public ResponseEntity<SearchMemberVO> selectMember(@RequestBody SearchMemberDTO searchmemberdto) {
	    try {
	        int member_id = searchmemberdto.getMember_id();
	        // member_id 처리
	        SearchMemberVO memberInfo = searchmemberservice.searchMember(member_id);

	        if (memberInfo != null) {
	            // 검색 결과가 있을 경우 클라이언트에게 반환
	            return new ResponseEntity<>(memberInfo, HttpStatus.OK);
	        } else {
	            // 검색 결과가 없을 경우에 대한 처리
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 오류 처리
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	// 피드페이지에서 하위 업무 담당자 노출
	private final SelectFeedSubWorkerService selectfeedsubworkerservice;
	
	@ResponseBody
	@PostMapping("/SelectSubWorker")
	public List<SelectFeedSubWorkerVO> searchSubWorker(@RequestBody SelectFeedSubWorkerDTO selectfeedsubworkerdto){
		int sub_task_id = selectfeedsubworkerdto.getSub_task_id();
		
		return selectfeedsubworkerservice.subworker(sub_task_id);
		
	}
	
	
	// 프로젝트 참여자중 멤버 검색
	private final SelectMembersService selectmembersservice;
	
	@ResponseBody
	@PostMapping("/searchMember")
	public List<SelectMembersVO> searchmember(@RequestBody SelectMembersDTO selectmemberdto) {
		String searchInput = selectmemberdto.getSearchMember();
		
		return selectmembersservice.search(searchInput, pro_id);
	}
}