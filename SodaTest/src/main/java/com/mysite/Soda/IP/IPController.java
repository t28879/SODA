package com.mysite.Soda.IP;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.Soda.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IPController {
	
	private final IPService ipservice;
	private final HttpSession session;
	@GetMapping("/DownLoadIP")
	   public String GetConnStatistic(Model model) {
	      Object memIdobj = session.getAttribute("memberId");
	      int member_id = (int) memIdobj;
	      
	      List<IPVO> iplist =  ipservice.getIPinfo(member_id);
	      if(iplist.size()>0) {
	         model.addAttribute("iponoff",iplist.get(0).getIp());
	      }
	      else model.addAttribute("iponoff"," ");
	      model.addAttribute("iplist",iplist);
	      
	      List<IPMemberVO> ipmemlist =  ipservice.getIPmeminfo(member_id);
	      if(ipmemlist.size()>0) {
	         model.addAttribute("ipformem",ipmemlist.get(0).getIpformem());
	      }
	      else model.addAttribute("ipformem"," ");
	      model.addAttribute("ipmemlist",ipmemlist);
	      
	      List<MemberVO> companymemberList = ipservice.getMemberInfo(member_id);
	      model.addAttribute("companymemberList",companymemberList);
	      return "DownLoadIP.html";
	   }
	   @GetMapping("/LoginIP")
	   public String LoginIP(Model model) {
	      Object memIdobj = session.getAttribute("memberId");
	      int member_id = (int) memIdobj;
	      List<IPVO> iplist =  ipservice.getIPlogininfo(member_id);
	      if(iplist.size()>0) {
	         model.addAttribute("iponoff",iplist.get(0).getIp());
	      }
	      else model.addAttribute("iponoff"," ");
	      model.addAttribute("iplist",iplist);
	      List<IPMemberVO> ipmemlist =  ipservice.getIPloginmemberinfo(member_id);
	      if(ipmemlist.size()>0) {
	         model.addAttribute("ipformem",ipmemlist.get(0).getIpformem());
	      }
	      else model.addAttribute("ipformem"," ");
	      model.addAttribute("ipmemlist",ipmemlist);//여기까지
	      List<MemberVO> companymemberList = ipservice.getMemberInfo(member_id);
	      model.addAttribute("companymemberList",companymemberList);
	      return "LoginIP.html";
	   }
	@PostMapping("/IpSave")
	@ResponseBody
	public IPafterVO updateIPMem(@RequestParam(name = "ipaddress") String ipaddress, @RequestParam(name = "ipexplain") String ipexplain) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
	    IPafterVO response = ipservice.updateIPaddress(member_id, ipaddress, ipexplain);
	    return response;
	}
	@PostMapping("/IpLoginSave")
	@ResponseBody
	public IPafterVO updateLoginIPMem(@RequestParam(name = "ipaddress") String ipaddress, @RequestParam(name = "ipexplain") String ipexplain) {
		Object memIdobj = session.getAttribute("memberId");
		int member_id = (int) memIdobj;
	    IPafterVO response = ipservice.updateLoginIPaddress(member_id, ipaddress, ipexplain);
	    return response;
	}
}
