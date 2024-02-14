package com.mysite.Soda;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.Soda.AllWork.AllWorkService;
import com.mysite.Soda.AllWork.ManagerUpdateVO;
import com.mysite.Soda.IP.IPDAO;
import com.mysite.Soda.IP.IPService;
import com.mysite.Soda.MyProject.MyProjectColorChange;
import com.mysite.Soda.MyProject.MyProjectFolderChange;
import com.mysite.Soda.MyProject.MyProjectService;
import com.mysite.Soda.WaterMark.WaterMarkDAO;
import com.mysite.Soda.project.AdminList;
import com.mysite.Soda.project.ProjectService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AllRestController {
	private final ProjectService projectservice;
	private final MyProjectService myprojectservice;
	private final AllWorkService allworkservice;
	private final IPService ipservice;
	private final WaterMarkDAO watermarkdao;
	private final IPDAO ipdao;
	private final HttpSession session;
	
	@PostMapping("/UpdateIPMem")
	public void UpdateIPMem(@RequestBody List<Long> ipmemlist) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		ipservice.updateipmem(member_id,ipmemlist);
	}
	@PostMapping("/UpdateLoginIPMem")
	public void UpdateLoginIPMem(@RequestBody List<Long> ipmemlist) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		ipservice.updateloginipmem(member_id,ipmemlist);
	}
	@PostMapping("/UpdateAdmin")
	public void UpdateAdmin(@RequestBody AdminList AdminList) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		projectservice.updateAdmin(member_id,AdminList);
	}
	@PostMapping("/DownloadIPOnOff")
	public ResponseEntity<Void> DownloadIPOnOff(
			@RequestParam(name = "member_id")int member_id,
			@RequestParam(name = "type")int type,
			@RequestParam(name = "onoff")int onoff ) {
		ipservice.updateIPonoff(member_id, type, onoff);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/LoginIPOnOff")
	public ResponseEntity<Void> LoginIPOnOff(
			@RequestParam(name = "member_id")int member_id,
			@RequestParam(name = "type")int type,
			@RequestParam(name = "onoff")int onoff ) {
		ipservice.updateLoginIPonoff(member_id, type, onoff);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/DeleteIP")
	public ResponseEntity<Void> DeleteIP(
			@RequestParam(name = "company_id")int company_id,
			@RequestParam(name = "iptext")String iptext,
			@RequestParam(name = "type")int type) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		ipservice.deleteIP(member_id,company_id, iptext,type);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/DeleteLoginIP")
	public ResponseEntity<Void> DeleteLoginIP(
			@RequestParam(name = "company_id")int company_id,
			@RequestParam(name = "iptext")String iptext,
			@RequestParam(name = "type")int type) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		ipservice.deleteLoginIP(member_id,company_id, iptext,type);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/AlterWaterMark")
	public ResponseEntity<Void> AlterWaterMark(
			@RequestParam(name = "member_id")int member_id,
			@RequestParam(name = "WaterMark")int WaterMark) {
		String target1 = "파일 문서 뷰어 워터마크 설정";
		String target2 = "파일 문서 뷰어 워터마크 설정 변경";
		String change = "ON → OFF";
		String object = " ";
		if(WaterMark == 1) {
			change = "OFF → ON";
		}
		ipdao.AdminChangeLog(member_id, target1, target2, object, change);
		watermarkdao.alterwm(member_id, WaterMark);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/UpdateProjectColor")
	public ResponseEntity<Void> UpdateProjectColor(@RequestBody MyProjectColorChange MyProjectColorChange) {
		myprojectservice.updatemyproeject(MyProjectColorChange); 
		return ResponseEntity.ok().build();
	}
	@PostMapping("/UpdateProjectFolder")
	public ResponseEntity<Void> UpdateProjectFolder(@RequestBody MyProjectFolderChange MyProjectFolderChange) {
		myprojectservice.updatemyproejectfolder(MyProjectFolderChange);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/UpdateFeedDate")
	public ResponseEntity<Void> UpdateFeedDate(
			@RequestParam(name = "feedorsub")int feedorsub,
			@RequestParam(name = "selectedDate")String selectedDate,
			@RequestParam(name = "selectid")int selectid,
			@RequestParam(name = "type")int type) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		allworkservice.updatefeeddate(feedorsub, selectedDate, selectid, type,member_id);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/UpdateWorkManager")
	public ResponseEntity<Void> UpdateWorkManager(@RequestBody ManagerUpdateVO ManagerUpdateVO) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
		allworkservice.updatewm(ManagerUpdateVO,member_id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getIP")
    public String getClientIP(HttpServletRequest request) {
        String clientIP =  ClientUtils.getRemoteIP(request);
        System.out.println(clientIP);
        return "Client IP Address: " + clientIP;
    }
}
