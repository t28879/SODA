package com.mysite.Soda.updateFeed;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Soda.selectFeed.FeedWorkVO;
import com.mysite.Soda.table.SelectAllFeedTableDAO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateFeedWorkService {
	
	private final UpdateFeedWorkDAO updatefeedworkdao;
	
	private final SelectAllFeedTableDAO selectallfeedtabledao;
	
	@Transactional
	public void allfeedwork(UpdateFeedWorkDTO updatefeedworkdto, int userNum) {
		// 피드 아이디 꺼내기
		int feed_id = updatefeedworkdto.getFeed_id();
		
		// 원래 있던 값 select 하기 위해 꺼낸 feed_id 보내기
		FeedWorkVO allFeedWork = selectallfeedtabledao.selectAllFeedWork(feed_id);
		
		// 수정할 업무 담당자들 멤버 아이디
		List<Integer> arr = updatefeedworkdto.getModifyMembers();
		int sub_task_id = updatefeedworkdto.getSub_task_id();
		
		// 수정할 하위 업무 담당자들 멤버 아이디
		List<Integer> subarr = updatefeedworkdto.getSubModifyMembers();
		
		// 피드 업무 제목 수정
		if(updatefeedworkdto.getWorkModifyTitle() != null && updatefeedworkdto.getWorkModifyTitle() != "") {
			String assistanceTitle = allFeedWork.getTitle();
			String newTitle = "제목이 " +  "\"" + assistanceTitle + "\" " +" 에서 → " + "\"" + updatefeedworkdto.getWorkModifyTitle() + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyTitle(updatefeedworkdto.getWorkModifyTitle(), feed_id);
			updatefeedworkdao.insertReplyLog(newTitle, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		// 피드 업무 내용 수정
		if(updatefeedworkdto.getWorkModifyContent() != null && updatefeedworkdto.getWorkModifyContent() != "") {
			String assistanceContent = allFeedWork.getContent();
			String newContent = "내용이 " +  "\"" + assistanceContent + "\" " +" 에서 → " + "\"" + updatefeedworkdto.getWorkModifyContent() + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyContent(updatefeedworkdto.getWorkModifyContent(), feed_id);
			updatefeedworkdao.insertReplyLog(newContent, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		// 피드 업무 요청상태 수정
		if(updatefeedworkdto.getMyModiWorkProcessType() != 0) {
			int assistanceProcess = allFeedWork.getProcess();
			String changeProcess = Integer.toString(assistanceProcess);
			String process = null;
			
			switch(assistanceProcess) {
				case 1 : changeProcess = "요청"; break;
				case 2 : changeProcess = "진행"; break;
				case 3 : changeProcess = "피드백"; break;
				case 4 : changeProcess = "완료"; break;
				case 5 : changeProcess = "보류"; break;
				default : changeProcess = "미설정"; break;
			}
			
			switch(updatefeedworkdto.getMyModiWorkProcessType()) {
				case 1 : process = "요청"; break;
				case 2 : process = "진행"; break;
				case 3 : process = "피드백"; break;
				case 4 : process = "완료"; break;
				case 5 : process = "보류"; break;
			}
			
			String assistance = changeProcess;
			String newProcess = "상태가 " +  "\"" + assistance + "\" " +" 에서 → " + "\"" + process + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyProcess(updatefeedworkdto.getMyModiWorkProcessType(), feed_id);
			updatefeedworkdao.insertReplyLog(newProcess, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		// 업무 담당자 수정
		if (arr != null && arr.size() > 0) {
		    updatefeedworkdao.deleteWorkmanager(feed_id);

		    for (int i : arr) {
		        updatefeedworkdao.modifyWorkmanager(i, feed_id);
		    }
		    
		    List<String> modifiedMembersNames = updatefeedworkdao.getMemberNamesByIds(arr);

		    String logMembers;
		    if (modifiedMembersNames.size() == 1) {
		        logMembers = modifiedMembersNames.get(0) + " 님이 업무 담당자로 변경되었습니다.";
		    } else {
		        String modifiedMembers = modifiedMembersNames.get(0) + " 외 " + (modifiedMembersNames.size() - 1) + " 명";
		        logMembers = modifiedMembers + " 님이 업무 담당자로 변경되었습니다.";
		    }
		    
		    
		    updatefeedworkdao.insertReplyLog(logMembers, feed_id, userNum);
		    
		    updatefeedworkdao.replyPlus(feed_id);
		}
		// 업무 시작일
		if(updatefeedworkdto.getWorkModifyStart() != null) {
			Date assistanceStart = allFeedWork.getStart_date();
			String newStart = "시작일이 " +  "\"" + assistanceStart + "\" " +" 에서 → " + "\"" + updatefeedworkdto.getWorkModifyStart() + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyStart(updatefeedworkdto.getWorkModifyStart(), feed_id);
			updatefeedworkdao.insertReplyLog(newStart, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		// 업무 마감일
		if(updatefeedworkdto.getWorkModifyEnd() != null) {
			Date assistanceDeadline = allFeedWork.getDeadline();
			String newDeadline = "마감일이 " +  "\"" + assistanceDeadline + "\" " +" 에서 → " + "\"" + updatefeedworkdto.getWorkModifyEnd() + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyEnd(updatefeedworkdto.getWorkModifyEnd(),feed_id);
			updatefeedworkdao.insertReplyLog(newDeadline, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		if(updatefeedworkdto.getMyModiWorkPriorityType() != 0) {
			int tempPriority = allFeedWork.getPriority();
			String changePriority = Integer.toString(tempPriority);
			String priority = null;
			
			switch(tempPriority) {
			case 1 : changePriority = "낮음"; break;
			case 2 : changePriority = "보통"; break;
			case 3 : changePriority = "높음"; break;
			case 4 : changePriority = "긴급"; break;
			default : changePriority = "미설정"; break;
			}
			
			switch(updatefeedworkdto.getMyModiWorkPriorityType()) {
			case 1 : priority = "낮음"; break;
			case 2 : priority = "보통"; break;
			case 3 : priority = "높음"; break;
			case 4 : priority = "긴급"; break;
			}
			
			String newPriority = "우선순위가 " +  "\"" + changePriority + "\" " +" 에서 → " + "\"" + priority + "\" " +"(으)로 변경되었습니다.";
			
			updatefeedworkdao.modifyPriority(updatefeedworkdto.getMyModiWorkPriorityType(), feed_id);
			updatefeedworkdao.insertReplyLog(newPriority, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		if(updatefeedworkdto.getMyModiWorkProgressType() != 0) {
			int tempProgress = allFeedWork.getProgress();
			String newProgress = "진척도가 " +  "\"" + tempProgress + "\" " +"% 에서 → " + "\"" + updatefeedworkdto.getMyModiWorkProgressType() + "\" " +"% (으)로 변경되었습니다.";
			
			updatefeedworkdao.modifyProgress(updatefeedworkdto.getMyModiWorkProgressType(), feed_id);
			updatefeedworkdao.insertReplyLog(newProgress, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		if(updatefeedworkdto.getMyModiWorkSTProcessType() != 0) {
			int tempSubProcess = allFeedWork.getSub_process();
			String changeSubProcess = Integer.toString(tempSubProcess);
			String subProcess = null;
			
			switch(tempSubProcess) {
			case 1 : changeSubProcess = "요청"; break;
			case 2 : changeSubProcess = "진행"; break;
			case 3 : changeSubProcess = "피드백"; break;
			case 4 : changeSubProcess = "완료"; break;
			case 5 : changeSubProcess = "보류"; break;
			default : changeSubProcess = "미설정"; break;
			}
			
			switch(updatefeedworkdto.getMyModiWorkSTProcessType()) {
			case 1 : subProcess = "요청"; break;
			case 2 : subProcess = "진행"; break;
			case 3 : subProcess = "피드백"; break;
			case 4 : subProcess = "완료"; break;
			case 5 : subProcess = "보류"; break;
			}
			
			String newSubProcess = "하위 업무 상태가 " +  "\"" + changeSubProcess + "\" " +" 에서 → " + "\"" + subProcess + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyStProcess(updatefeedworkdto.getMyModiWorkSTProcessType(), feed_id);
			updatefeedworkdao.insertReplyLog(newSubProcess, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		if(updatefeedworkdto.getWorkModifyTitleST() != null && updatefeedworkdto.getWorkModifyTitleST() != "") {
			String tempSubtaskTitle = allFeedWork.getSub_title();
			String newSubTitle = "제목이 " +  "\"" + tempSubtaskTitle + "\" " +" 에서 → " + "\"" + updatefeedworkdto.getWorkModifyTitleST() + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyStTitle(updatefeedworkdto.getWorkModifyTitleST(), feed_id);
			updatefeedworkdao.insertReplyLog(newSubTitle, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		if(updatefeedworkdto.getWorkModifyEndST() != null) {
			Date tempSubDeadline = allFeedWork.getSub_deadline();
			String newSubDeadline = "하위업무 마감일이 " +  "\"" + tempSubDeadline + "\" " +" 에서 → " + "\"" + updatefeedworkdto.getWorkModifyEndST() + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyStEnd(updatefeedworkdto.getWorkModifyEndST(), feed_id);
			updatefeedworkdao.insertReplyLog(newSubDeadline, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		if(updatefeedworkdto.getMyModiWorkSTPriorityType() != 0) {
			int tempSubPriority = allFeedWork.getSub_priority();
			String assistanceSubPriority = Integer.toString(tempSubPriority);
			String subPriority = null;
			
			switch(tempSubPriority) {
			case 1 : assistanceSubPriority = "낮음"; break;
			case 2 : assistanceSubPriority = "보통"; break;
			case 3 : assistanceSubPriority = "높음"; break;
			case 4 : assistanceSubPriority = "긴급"; break;
			default : assistanceSubPriority = "미설정"; break;
			}
			
			switch(updatefeedworkdto.getMyModiWorkSTPriorityType()) {
			case 1 : subPriority = "낮음"; break;
			case 2 : subPriority = "보통"; break;
			case 3 : subPriority = "높음"; break;
			case 4 : subPriority = "긴급"; break;
			}
			
			String newSubPriority = "하위 업무 우선순위가 " +  "\"" + assistanceSubPriority + "\" " +" 에서 → " + "\"" + subPriority + "\" " +"(으)로 변경되었습니다.";
			updatefeedworkdao.modifyStPriority(updatefeedworkdto.getMyModiWorkSTPriorityType(), feed_id);
			updatefeedworkdao.insertReplyLog(newSubPriority, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		// 하위업무 담당자 수정
		if(subarr != null && subarr.size() > 0) {
			// 원래 있던 하위업무 담당자 지우기
			updatefeedworkdao.deleteSfwmanager(sub_task_id);
			
			// 선택된 하위업무 담당자들 insert
			for(int j : subarr) {
				updatefeedworkdao.modifySubworkmanager(j, sub_task_id);
			}
			
			List<String> modifySubMembers = updatefeedworkdao.getSubMembers(subarr);
			
			String logSubMembers;
			
			if(modifySubMembers.size() == 1) {
				logSubMembers = modifySubMembers.get(0) + "님이 하위 업무 담당자로 변경되었습니다.";
			} else {
				String multipleMember = modifySubMembers.get(0) + " 외 " + (modifySubMembers.size() - 1) + " 명";
				logSubMembers = multipleMember + "님이 하위 업무 담당자로 변경 되었습니다.";
			}
			
			updatefeedworkdao.insertReplyLog(logSubMembers, feed_id, userNum);
			updatefeedworkdao.replyPlus(feed_id);
		}
		
	}
}
