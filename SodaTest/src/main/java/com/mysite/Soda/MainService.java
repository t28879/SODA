package com.mysite.Soda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.allsetting.AllsettingDAO;
import com.mysite.Soda.allsetting.AllsettingVO;
import com.mysite.Soda.chatting.InviteMemberDAO;
import com.mysite.Soda.chatting.InviteMemberVO;
import com.mysite.Soda.color.ColorDAO;
import com.mysite.Soda.color.ColorVO;
import com.mysite.Soda.companyMem.CompanyMemDAO;
import com.mysite.Soda.companyMem.CompanyMemVO;
import com.mysite.Soda.crud.InsertFolderNameDAO;
import com.mysite.Soda.crud.UpdateBackgroundDAO;
import com.mysite.Soda.crud.UpdateMemoDAO;
import com.mysite.Soda.crud.UpdateOkDAO;
import com.mysite.Soda.crud.UpdateStatusDAO;
import com.mysite.Soda.crud.UpdateWidgetDAO;
import com.mysite.Soda.feedType.FeedTypeDAO;
import com.mysite.Soda.feedType.FeedTypeVO;
import com.mysite.Soda.feedType.FinalFeedTypeVO;
import com.mysite.Soda.feedwork.DelayFwDAO;
import com.mysite.Soda.feedwork.DelayFwVO;
import com.mysite.Soda.feedwork.DelayFwcountDAO;
import com.mysite.Soda.feedwork.DelayFwcountVO;
import com.mysite.Soda.feedwork.ExpectedFwDAO;
import com.mysite.Soda.feedwork.ExpectedFwVO;
import com.mysite.Soda.feedwork.ExpectedFwcountDAO;
import com.mysite.Soda.feedwork.ExpectedFwcountVO;
import com.mysite.Soda.folder.FolderDAO;
import com.mysite.Soda.folder.FolderVO;
import com.mysite.Soda.member.MemberDAO;
import com.mysite.Soda.member.MemberVO;
import com.mysite.Soda.myCompanyMem.MyCompanyMemDAO;
import com.mysite.Soda.myCompanyMem.MyCompanyMemVO;
import com.mysite.Soda.myOtherMem.MyOtherMemDAO;
import com.mysite.Soda.myOtherMem.MyOtherMemVO;
import com.mysite.Soda.myPeople.MyPeopleDAO;
import com.mysite.Soda.myPeople.MyPeopleVO;
import com.mysite.Soda.project.ProjectDAO;
import com.mysite.Soda.project.ProjectVO;
import com.mysite.Soda.schedule.ScheduleDAO;
import com.mysite.Soda.schedule.ScheduleVO;
import com.mysite.Soda.selectProjectName.SelectProjectNameDAO;
import com.mysite.Soda.selectProjectName.SelectProjectNameVO;
import com.mysite.Soda.widget.WidgetDAO;
import com.mysite.Soda.widget.WidgetVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
	private final AllsettingDAO allsettingdao;

	public AllsettingVO getAllsetting(int member_id) {
		return allsettingdao.getAllsetting(member_id);
	}

	private final MemberDAO memberdao;

	public MemberVO getMember(int member_id) {
		return memberdao.getMember(member_id);
	}

	private final ProjectDAO projectdao;

	public List<ProjectVO> getProject(int member_id) {
		return projectdao.getProject(member_id);
	}
	
	public void getInsertProject(int member_id, String projectName) {
		projectdao.insertProject(member_id, projectName);
		projectdao.insertProjectMember(member_id, projectdao.getProjectId());
	}

	private final MyPeopleDAO mypeopledao;

	public List<MyPeopleVO> getMyPeople(int member_id) {
		return mypeopledao.getMyPeople(member_id);
	}

	private final MyCompanyMemDAO mycompanymemdao;

	public List<MyCompanyMemVO> getMyCompanyMem(int member_id) {
		return mycompanymemdao.getMyCompanyMem(member_id);
	}

	private final MyOtherMemDAO myothermemdao;

	public List<MyOtherMemVO> getMyOtherMem(int member_id) {
		return myothermemdao.getMyOtherMem(member_id);
	}

	private final FolderDAO folderdao;

	public List<FolderVO> getFolder(int member_id) {
		return folderdao.getFolder(member_id);
	}

	private final ExpectedFwDAO expectedfwdao;

	public List<ExpectedFwVO> getExpectedFw(int member_id) {
		return expectedfwdao.getExpectedFw(member_id);
	}

	private final ExpectedFwcountDAO expectedfwcountdao;

	public ExpectedFwcountVO getExpectedFwcount(int member_id) {
		return expectedfwcountdao.getExpectedFwcount(member_id);
	}

	private final DelayFwDAO delayfwdao;

	public List<DelayFwVO> getDelayFw(int member_id) {
		return delayfwdao.getDelayFw(member_id);
	}

	private final DelayFwcountDAO delayfwcountdao;

	public DelayFwcountVO getDelayFwcount(int member_id) {
		return delayfwcountdao.getDelayFwcount(member_id);
	}

	private final CompanyMemDAO companyMemdao;

	public List<CompanyMemVO> getCompanyMem(int member_id) {
		return companyMemdao.getCompanyMem(member_id);
	}

	private final ColorDAO colordao;

	public List<ColorVO> getColor(int member_id) {
		return colordao.getColor(member_id);
	}

	private final InsertFolderNameDAO insertfoldernamedao;

	public int getInsertFolderName(int member_id, String folderName) {
		return insertfoldernamedao.insertFolderName(member_id, folderName);
	}
	

	private final UpdateStatusDAO updatestatusdao;

	public int getUpdateStatus(int member_id, String statusValue) {
		return updatestatusdao.updateStatus(member_id, statusValue);
	}

	private final UpdateBackgroundDAO updatebackgrounddao;

	public int getUpdateBackground(int member_id, String imagePath) {
		return updatebackgrounddao.updateBackground(member_id, imagePath);
	}

	private final UpdateMemoDAO updatememodao;

	public int getUpdateMemo(int member_id, String memo) {
		return updatememodao.updateMemo(member_id, memo);
	}

	private final UpdateWidgetDAO updatewidgetdao;

	public int getUpdateWidget(int member_id, String[] orderArray) {
		return updatewidgetdao.updateWidget(member_id, orderArray);
	}

	private final WidgetDAO widgetdao;

	public List<WidgetVO> getWidget(int member_id) {
		return widgetdao.getWidget(member_id);
	}

	private final FeedTypeDAO feedtypedao;

	public List<FinalFeedTypeVO> getFeedType(int clickedId) {
		List<FinalFeedTypeVO> all = new ArrayList<FinalFeedTypeVO>();
		List<FeedTypeVO> arr = feedtypedao.getFeedType(clickedId);
		for (FeedTypeVO ftv : arr) {
			FinalFeedTypeVO mm = new FinalFeedTypeVO();
			switch (ftv.getType()) {
			case 1:
				mm.setTitle(feedtypedao.getFeedTextType(ftv.getFeed_Id()));
				mm.setType(1);
				break;
			case 2:
				mm.setTitle(feedtypedao.getFeedWorkType(ftv.getFeed_Id()));
				mm.setType(2);
				break;
			case 3:
				mm.setTitle(feedtypedao.getFeedScheduleType(ftv.getFeed_Id()));
				mm.setType(3);
				break;
			case 4:
				mm.setTitle(feedtypedao.getFeedTodoType(ftv.getFeed_Id()));
				mm.setType(4);
				break;
			default:
				break;
			}
			all.add(mm);
		}
		return all;
	}

	private final ScheduleDAO scheduledao;

	public List<ScheduleVO> getSchedule(int member_id, String clickedDate) {
		return scheduledao.getSchedule(member_id, clickedDate);
	}

	private final SelectProjectNameDAO selectprojectdao;

	public SelectProjectNameVO getSelectProjectName(int clickedId) {
		return selectprojectdao.getSelectProjectName(clickedId);
	}

	private final InviteMemberDAO invitememberdao;
	
	public List<InviteMemberVO> inviteMember(String[] selectedItems) {
		return invitememberdao.inviteMember(selectedItems);
	}
	
	private final UpdateOkDAO updateokdao;
	   
	   public int ok(int member_id, int result) {
	      return updateokdao.ok(member_id, result);
	   }
}
