package com.mysite.Soda;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.Soda.allsetting.AllsettingVO;
import com.mysite.Soda.chatting.InviteMemberVO;
import com.mysite.Soda.color.ColorVO;
import com.mysite.Soda.companyMem.CompanyMemVO;
import com.mysite.Soda.feedType.FinalFeedTypeVO;
import com.mysite.Soda.feedwork.DelayFwVO;
import com.mysite.Soda.feedwork.DelayFwcountVO;
import com.mysite.Soda.feedwork.ExpectedFwVO;
import com.mysite.Soda.feedwork.ExpectedFwcountVO;
import com.mysite.Soda.folder.FolderVO;
import com.mysite.Soda.member.MemberVO;
import com.mysite.Soda.myCompanyMem.MyCompanyMemVO;
import com.mysite.Soda.myOtherMem.MyOtherMemVO;
import com.mysite.Soda.myPeople.MyPeopleVO;
import com.mysite.Soda.project.ProjectVO;
import com.mysite.Soda.schedule.ScheduleVO;
import com.mysite.Soda.selectProjectName.SelectProjectNameVO;
import com.mysite.Soda.widget.WidgetVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainService mainservice;
	private final HttpSession session;
		
	@GetMapping("/")
	public String root() {
		return "redirect:/SODA/Home";
	}
	
	@GetMapping("/SODA_DASHBOARD")
	public String GetAllById(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		System.out.println("MainCon:"+userNum);
		if(userNum != 0) {
			MemberVO member = mainservice.getMember(userNum); 
			model.addAttribute("member",member);
			
			List<ProjectVO> project = mainservice.getProject(userNum);
			model.addAttribute("project", project);
			
			List<MyPeopleVO> myPeople = mainservice.getMyPeople(userNum);
			model.addAttribute("myPeople", myPeople);
			
			List<MyCompanyMemVO> myCompanyMem = mainservice.getMyCompanyMem(userNum);
			model.addAttribute("myCompanyMem", myCompanyMem);
			
			List<MyOtherMemVO> myOtherMem = mainservice.getMyOtherMem(userNum);
			model.addAttribute("myOtherMem", myOtherMem);
			
			List<ColorVO> color = mainservice.getColor(userNum);
			model.addAttribute("projectWithColor", color);
			
			List<FolderVO> folder = mainservice.getFolder(userNum);
			model.addAttribute("folder", folder);
			
			List<CompanyMemVO> companyMem = mainservice.getCompanyMem(userNum);
			model.addAttribute("companyMem", companyMem);
			
			AllsettingVO allsetting = mainservice.getAllsetting(userNum);
			model.addAttribute("allsetting", allsetting);
			
			ExpectedFwcountVO expectedFwcount = mainservice.getExpectedFwcount(userNum);
			model.addAttribute("expectedFwcount", expectedFwcount);
			
			DelayFwcountVO delayFwcount = mainservice.getDelayFwcount(userNum);
			model.addAttribute("delayFwcount", delayFwcount);
			
			List<ExpectedFwVO> expectedFw = mainservice.getExpectedFw(userNum);
			model.addAttribute("expectedFw", expectedFw);
			
			List<DelayFwVO> delayFw = mainservice.getDelayFw(userNum);
			model.addAttribute("delayFw", delayFw);
			
			List<WidgetVO> widget = mainservice.getWidget(userNum);
			model.addAttribute("widget", widget);
			
			
			return "SODA_DASHBOARD";
		} else {
			return "SODA_Home";
		}
	}
	
	@PostMapping("/insertfoldername")
	public ResponseEntity<List<FolderVO>> insertFolderName(@RequestParam(name = "folderName") String folderName, Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		mainservice.getInsertFolderName(userNum, folderName);
		List<FolderVO> folders = mainservice.getFolder(userNum);
		return ResponseEntity.ok().body(folders);	}
	
	@PostMapping("/insertProject")
	public String insertProject(@RequestParam(name = "creProjectName") String projectName, Model model) {
		System.out.println("mainCon:"+projectName);
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		System.out.println("mainCon:"+userNum);
		mainservice.getInsertProject(userNum, projectName);
		return "redirect:/SODA_DASHBOARD";}
	
	@PostMapping("/updateStatus")
	public ResponseEntity<Void> updateStatus(@RequestParam(name = "statusValue") String statusValue, Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		mainservice.getUpdateStatus(userNum, statusValue);
		return ResponseEntity.ok().build();	}
	
	@PostMapping("/updateBackground")
	public ResponseEntity<Void> updateBackground(@RequestParam(name = "imagePath") String imagePath, Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		mainservice.getUpdateBackground(userNum, imagePath);
		return ResponseEntity.ok().build();	}
	
	@PostMapping("/updateMemo")
	public ResponseEntity<Void> updateMemo(@RequestParam(name = "memo") String memo, Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		mainservice.getUpdateMemo(userNum, memo);
		return ResponseEntity.ok().build();	}
	
	@PostMapping("/UpdateWidget")
	public ResponseEntity<Void> UpdateWidget(@RequestParam(name = "order") String order, Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		String[] orderArray = order.replaceAll("[\"\\[\\]]", "").split(",");
		mainservice.getUpdateWidget(userNum, orderArray);
		return ResponseEntity.ok().build();	}
	
	@PostMapping("/findFeedType")
	@ResponseBody
    public List<FinalFeedTypeVO> feedType(@RequestParam(name = "clickedId") int clickedId, Model model) {
        return mainservice.getFeedType(clickedId);
    }
	
	@PostMapping("/selectProjectName")
	@ResponseBody
	public SelectProjectNameVO selectProjectName(@RequestParam(name = "clickedId") int clickedId, Model model) {
		return mainservice.getSelectProjectName(clickedId);
	}
	
	@PostMapping("/schedule")
	@ResponseBody
    public List<ScheduleVO> schedule(@RequestParam(name = "clickedDate") String clickedDate, Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
        return mainservice.getSchedule(userNum, clickedDate);
    }
	
	@GetMapping("/SODA_CreateProject")
	public String createProject(Model model) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		model.addAttribute("memberId", userNum);
		return "SODA_CreateProject";
	}
	
	@PostMapping("/openChatting")
	@ResponseBody
	public ResponseEntity<List<InviteMemberVO>> openChatting(@RequestBody List<String> selectedItems, Model model) {
	    List<InviteMemberVO> result = mainservice.inviteMember(selectedItems.toArray(new String[0]));
	    model.addAttribute("inviteMembers", result);
	    return ResponseEntity.ok(result);
	}
	
	@PostMapping("/ok")
	   public ResponseEntity<Void> updateOk(@RequestParam(name = "result") int result, Model model) {
	      Object memIdobj = session.getAttribute("memberId");
	      int userNum = (int) memIdobj;
	      mainservice.ok(userNum, result);
	      return ResponseEntity.ok().build();   }
}
