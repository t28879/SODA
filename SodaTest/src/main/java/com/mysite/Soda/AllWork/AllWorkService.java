package com.mysite.Soda.AllWork;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.SearchMemDAO;
import com.mysite.Soda.project.ProjectVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllWorkService {

	private final AllWorkDAO allworkdao;
	private final SearchMemDAO searchmemdao;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<FinalWorkProjectVO> getworklist(int member_id) {
		List<FinalWorkProjectVO> all = new ArrayList<FinalWorkProjectVO>();
		List<ProjectVO> projectlist = allworkdao.getmyprojectinfo(member_id);
		for (ProjectVO p : projectlist) {
			FinalWorkProjectVO exfw = new FinalWorkProjectVO();
			exfw.setProject_name(p.getName());
			List<FeedWorkVO> fwlist = allworkdao.getfeedwork(p.getProject_id());
			List<FinalWorkFeedWorkVO> realfwlist = new ArrayList<FinalWorkFeedWorkVO>();
			
			for (FeedWorkVO fw : fwlist) {
				FinalWorkFeedWorkVO feedwork = new FinalWorkFeedWorkVO();
				feedwork.setTitle(fw.getTitle());
				
				String processimg = "";
				int process = fw.getProcess();
				switch(process) {
				case 1: processimg = "<img id = \"processimg\" src=\"images/requestDH.png\">";break;
				case 2: processimg = "<img id = \"processimg\" src=\"images/continueDH.png\">";break;
				case 3: processimg = "<img id = \"processimg\" src=\"images/feedbackDH.png\">";break;
				case 4: processimg = "<img id = \"processimg\" src=\"images/doneDH.png\">";break;
				case 5: processimg = "<img id = \"processimg\" src=\"images/holdDH.png\">";break;
				}
				feedwork.setProcessimg(processimg);
				feedwork.setProcess(fw.getProcess());
				
				String priorityimg = "";
				int priority= fw.getPriority();
				switch(priority) {
				case 1: priorityimg = "<img id = \"priorityimg\" src=\"images/lowDH.png\">";break;
				case 2: priorityimg = "<img id = \"priorityimg\" src=\"images/normalDH.png\">";break;
				case 3: priorityimg = "<img id = \"priorityimg\" src=\"images/highDH.png\">";break;
				case 4: priorityimg = "<img id = \"priorityimg\" src=\"images/urgentDH.png\">";break;
				case 5: priorityimg = "<img id = \"priorityimg\" src=\"images/nopriorityDH.png\">";break;
				}
				feedwork.setPriorityimg(priorityimg);
				feedwork.setPriority(fw.getPriority());
				List<FeedWorkManagerVO> fwmanagerlist = allworkdao.getworkmanagerlist(fw.getFeed_id());
				feedwork.setFwmanagerlist(fwmanagerlist);
				if(fwmanagerlist.size() > 1) {
					feedwork.setWorkmanagername(fwmanagerlist.get(0).getWorlmanagername()+" 외"+(fwmanagerlist.size()-1)+"명");
				}
				else if(fwmanagerlist.size() == 1) {
					feedwork.setWorkmanagername(fwmanagerlist.get(0).getWorlmanagername());
				}
				else feedwork.setWorkmanagername("없음");
				feedwork.setStart_date(dateFormat.format(fw.getStart_date()));
				feedwork.setDeadline(dateFormat.format(fw.getDeadline()));
				feedwork.setRegist_date(dateFormat.format(fw.getRegist_date()));
				feedwork.setFeed_id(fw.getFeed_id());
				feedwork.setProgress(fw.getProgress());
				String progressgraphcolor = "";
				if(fw.getProgress()<=40) {
					progressgraphcolor = "#FF7171";
				}
				else if(fw.getProgress()>=41 && fw.getProgress()<=80) {
					progressgraphcolor = "#86E57F";
				}
				else if(fw.getProgress()>=81 && fw.getProgress()<=100){
					progressgraphcolor = "#6799FF";
				}
				feedwork.setProgressgraphcolor(progressgraphcolor);
				List<SubWorkVO> sublist = allworkdao.getsubwork(fw.getFeed_id());
				List<FinalWorkSubWorkVO> realsublist = new ArrayList<FinalWorkSubWorkVO>();
				for(SubWorkVO sw : sublist) {
					FinalWorkSubWorkVO realsubVO = new FinalWorkSubWorkVO();
					realsubVO.setFeed_id(sw.getFeed_id());
					realsubVO.setSub_task_id(sw.getSub_task_id());
					realsubVO.setName(sw.getName());
					realsubVO.setSub_process(sw.getSub_process());
					String subprocessimg = "";
					int subprocess = sw.getSub_process();
					switch(subprocess) {
					case 1: subprocessimg = "<img id = \"processimg\" src=\"images/requestDH.png\">";break;
					case 2: subprocessimg = "<img id = \"processimg\" src=\"images/continueDH.png\">";break;
					case 3: subprocessimg = "<img id = \"processimg\" src=\"images/feedbackDH.png\">";break;
					case 4: subprocessimg = "<img id = \"processimg\" src=\"images/doneDH.png\">";break;
					case 5: subprocessimg = "<img id = \"processimg\" src=\"images/holdDH.png\">";break;
					}
					realsubVO.setProcessimg(subprocessimg);
					
					String subpriorityimg = "";
					int subpriority= sw.getSub_priority();
					switch(subpriority) {
					case 1: subpriorityimg = "<img id = \"priorityimg\" src=\"images/lowDH.png\">";break;
					case 2: subpriorityimg = "<img id = \"priorityimg\" src=\"images/normalDH.png\">";break;
					case 3: subpriorityimg = "<img id = \"priorityimg\" src=\"images/highDH.png\">";break;
					case 4: subpriorityimg = "<img id = \"priorityimg\" src=\"images/urgentDH.png\">";break;
					case 5: subpriorityimg = "<img id = \"priorityimg\" src=\"images/nopriorityDH.png\">";break;
					}
					realsubVO.setPriorityimg(subpriorityimg);
					
					String subprogressgraphcolor = "";
					if(sw.getProgress()<=40) {
						subprogressgraphcolor = "#FF7171";
					}
					else if(sw.getProgress()>=41 && sw.getProgress()<=80) {
						subprogressgraphcolor = "#86E57F";
					}
					else if(sw.getProgress()>=81 && sw.getProgress()<=100){
						subprogressgraphcolor = "#6799FF";
					}
					realsubVO.setProgress(sw.getProgress());
					realsubVO.setProgressgraphcolor(subprogressgraphcolor);
					realsubVO.setStart_date(dateFormat.format(sw.getStart_date()));
					realsubVO.setDeadline(dateFormat.format(sw.getDeadline()));
					List<FeedWorkManagerVO> managerlist = allworkdao.getsubworkmanagerlist(sw.getSub_task_id());
					realsubVO.setManagerlist(managerlist);
					if(managerlist.size() > 1) {
						realsubVO.setWorkmanagername(managerlist.get(0).getWorlmanagername()+" 외"+(managerlist.size()-1)+"명");
					}
					else if(managerlist.size() == 1) {
						realsubVO.setWorkmanagername(managerlist.get(0).getWorlmanagername());
					}
					else realsubVO.setWorkmanagername("없음");
					realsublist.add(realsubVO);
				}
				feedwork.setSubworklist(realsublist);
				realfwlist.add(feedwork);
			}
			exfw.setFeedworklist(realfwlist);
			all.add(exfw);
		}
		return all;
	}
	public String updateprocess(int feed_id,int process,int type,int member_id) {
		String img = "";
		String oriprocessString = "";
		String changedString = getprocess(process);
		String content = "";
		
		if(type == 1) {
			int originalprocess = allworkdao.getfeedworkinfo(feed_id).getProcess();
			oriprocessString = getprocess(originalprocess);
			content = "상태가"+"\"" +oriprocessString+"\" " +  "에서 → "+ "\"" +changedString+"\" " +"(으)로 변경되었습니다." ;
			allworkdao.updatefeedprocess(feed_id, process);
			allworkdao.updatereply(feed_id, member_id, content);
		}
		else if(type == 2) {
			int originalprocess = allworkdao.getsubworkinfo(feed_id).getSub_process();
			int subsfeedid = allworkdao.getsubworkinfo(feed_id).getFeed_id();
			oriprocessString = getprocess(originalprocess);
			content = "하위업무 상태가 " + "\"" +oriprocessString+"\" " +  "에서 → "+ "\"" + changedString+"\" " +"(으)로 변경되었습니다." ;
			allworkdao.updatereply(subsfeedid, member_id, content);
			allworkdao.updatesubfeedprocess(feed_id, process);
		}
		switch(process){
		case 1: img = "images/requestDH.png";break;
		case 2: img = "images/continueDH.png";break;
		case 3: img = "images/feedbackDH.png";break;
		case 4: img = "images/doneDH.png";break;
		case 5: img = "images/holdDH.png";break;
		default : img = "";
		}
		return img;
	}
	public String updatepriority(int feed_id,int priority,int type,int member_id) {
		String img = "";
		String oripriorityString = "";
		String changedString = getpriority(priority);
		String content = "";
		if(type == 1) {
			int originalpriority = allworkdao.getfeedworkinfo(feed_id).getPriority();
			oripriorityString = getpriority(originalpriority);
			content = "우선순위가"+"\"" +oripriorityString+"\" " +  "에서 → "+ "\"" +changedString+"\" " +"(으)로 변경되었습니다." ;
			allworkdao.updatefeedpriority(feed_id, priority);
			allworkdao.updatereply(feed_id, member_id, content);
		}
		else if(type == 2){
			int originalpriority = allworkdao.getsubworkinfo(feed_id).getSub_priority();
			int subsfeedid = allworkdao.getsubworkinfo(feed_id).getFeed_id();
			oripriorityString = getpriority(originalpriority);
			content = "하위업무 우선순위가 " + "\"" +oripriorityString+"\" " +  "에서 → "+ "\"" + changedString+"\" " +"(으)로 변경되었습니다." ;
			allworkdao.updatereply(subsfeedid, member_id, content);
			allworkdao.updatesubfeedpriority(feed_id, priority);
		}
		switch(priority){
		case 1: img = "images/lowDH.png";break;
		case 2: img = "images/normalDH.png";break;
		case 3: img = "images/highDH.png";break;
		case 4: img = "images/urgentDH.png";break;
		case 5: img = "images/nopriorityDH.png";break;
		default : img = "";
		}
		return img;
	}
	public void updatefeeddate(int feedorsub,String selectedDate,int selectid,int type,int member_id) {
		String content = "";
		if(feedorsub == 1) {
			if(type == 1) {
				Date originalstartdate = allworkdao.getfeedworkinfo(selectid).getStart_date();
				content = "업무 시작일이 "+originalstartdate+" 에서 "+selectedDate+" 으로 변경되었습니다.";
				allworkdao.updateFeedSDate(selectedDate, selectid);
				allworkdao.updatereply(selectid, member_id, content);
			}
			else if(type == 2) {
				Date originalstartdate = allworkdao.getfeedworkinfo(selectid).getDeadline();
				content = "업무 마감일이 "+originalstartdate+" 에서 "+selectedDate+" 으로 변경되었습니다.";
				allworkdao.updateFeedDDate(selectedDate, selectid);
				allworkdao.updatereply(selectid, member_id, content);
			}
			else return;
		}
		else if(feedorsub == 2) {
			if(type == 1) {
				Date originalstartdate = allworkdao.getsubworkinfo(selectid).getStart_date();
				content = "하위업무 시작일이 "+originalstartdate+" 에서 "+selectedDate+" 으로 변경되었습니다.";
				allworkdao.updateSubFeedSDate(selectedDate, selectid);
				int subsfeedid = allworkdao.getsubworkinfo(selectid).getFeed_id();
				allworkdao.updatereply(subsfeedid, member_id, content);
			}
			else if(type == 2) {
				Date originalstartdate = allworkdao.getsubworkinfo(selectid).getDeadline();
				content = "하위업무 마감일이 "+originalstartdate+" 에서 "+selectedDate+" 으로 변경되었습니다.";
				allworkdao.updateSubFeedDDate(selectedDate, selectid);
				int subsfeedid = allworkdao.getsubworkinfo(selectid).getFeed_id();
				allworkdao.updatereply(subsfeedid, member_id, content);
			}
			else return;
		}
	}
	public String updatefeedprogress(int selectfeedid,int percentage,int type,int member_id) {
		String color;
		String content;
		if(type == 1) {
			int originalprogress = allworkdao.getfeedworkinfo(selectfeedid).getProgress();
			content = "업무 진척도가 "+originalprogress+"% 에서 "+percentage+"% 로 변경되었습니다.";
			allworkdao.updateFeedProgress(selectfeedid, percentage);
			allworkdao.updatereply(selectfeedid, member_id, content);
		}
		else if(type == 2) {
			int originalprogress = allworkdao.getsubworkinfo(selectfeedid).getProgress();
			content = "하위업무 진척도가 "+originalprogress+"% 에서 "+percentage+"% 로 변경되었습니다.";
			allworkdao.updateSubFeedProgress(selectfeedid, percentage);
			int subsfeedid = allworkdao.getsubworkinfo(selectfeedid).getFeed_id();
			allworkdao.updatereply(subsfeedid, member_id, content);
		}
		
		if(percentage<=40) {
			color = "#FF7171";
		}
		else if(percentage>=41 && percentage<=80) {
			color = "#86E57F";
		}
		else if(percentage>=81 && percentage<=100){
			color = "#6799FF";
		}
		else color = "";
		return color;
	}
	public List<FeedWorkManagerVO> getmanagerlist(int feedorsubID,int type) {
		if(type == 1) {
			return allworkdao.getworkmanagerdetail(feedorsubID);
		}
		else {
			return allworkdao.getsubworkmanagerdetail(feedorsubID);
		}
		
	}
	public List<FeedWorkManagerVO> getpremanagerlist(int feedorsubID,int type) {
		if(type == 1) {
			return allworkdao.getpreworkmanagerdetail(feedorsubID);
		}
		else {
			return allworkdao.getpresubworkmanagerdetail(feedorsubID);
		}
		
	}
	public void updatewm(ManagerUpdateVO ManagerUpdateVO,int member_id) {
		String PreviousWorkMem = "업무 담당자가 ";
		String PreviousSubWorkMem = "하위 업무 담당자가 ";
		String AfterWorkMem = "";
		int type = ManagerUpdateVO.getType();
		int feedorsubID = ManagerUpdateVO.getFeedorsubID();
		List<Integer> memberlist = ManagerUpdateVO.getManagerIDlist();
		int memsize = memberlist.size();
		
		for(int i = 0; i < memsize; i++) {
			AfterWorkMem += searchmemdao.searchmeminfo(memberlist.get(i)).getName();
		    if(i < memsize - 1) {
		    	AfterWorkMem += ",";
		    } else {
		    	AfterWorkMem += "님으로 변경되었습니다";
		    }
		}
		
		if(type == 1) {
			List<FeedWorkManagerVO> workmem = allworkdao.getworkmanagerdetail(feedorsubID);
			int size = workmem.size();
			for(int i = 0; i < size; i++) {
				PreviousWorkMem += workmem.get(i).getWorlmanagername();
			    if(i < size - 1) {
			    	PreviousWorkMem += ",";
			    } else {
			    	PreviousWorkMem += "님에서 ";
			    }
			}
			allworkdao.deletefeedmanager(feedorsubID);
			for(int i : memberlist) {
				allworkdao.updatefeedmanager(feedorsubID, i);
			}
			String reply = PreviousWorkMem+AfterWorkMem;
			allworkdao.updatereply(feedorsubID, member_id, reply);
		}
		else if(type == 2) {
			List<FeedWorkManagerVO>  workmem = allworkdao.getsubworkmanagerdetail(feedorsubID);
			int size = workmem.size();
			for(int i = 0; i < size; i++) {
				PreviousSubWorkMem += workmem.get(i).getWorlmanagername();
			    if(i < size - 1) {
			    	PreviousSubWorkMem += ",";
			    } else {
			    	PreviousSubWorkMem += "님에서 ";
			    }
			}
			allworkdao.deletesubfeedmanager(feedorsubID);
			for(int i : memberlist) {
				allworkdao.updatesubfeedmanager(feedorsubID, i);
			}
			String reply = PreviousSubWorkMem+AfterWorkMem;
			int subsfeedid = allworkdao.getsubworkinfo(feedorsubID).getFeed_id();
			allworkdao.updatereply(subsfeedid, member_id, reply);
		}
	}
	public String getprocess(int process) {
		String changedString;
		switch(process){
		case 1: changedString = "요청";break;
		case 2: changedString = "진행";break;
		case 3: changedString = "피드백";break;
		case 4: changedString = "완료";break;
		case 5: changedString = "보류";break;
		default : changedString = "";
		}
		return changedString;
	}
	public String getpriority(int priority) {
		String changedString;
		switch(priority){
		case 1: changedString = "낮음";break;
		case 2: changedString = "보통";break;
		case 3: changedString = "높음";break;
		case 4: changedString = "긴급";break;
		case 5: changedString = "상관없음";break;
		default : changedString = "";
		}
		return changedString;
	}
}
