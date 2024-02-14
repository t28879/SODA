package com.mysite.Soda;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.Soda.AllLikey.ToggleLikeyService;
import com.mysite.Soda.ToggleBookmark.ToggleBookmarkService;
import com.mysite.Soda.deleteFeed.DeleteFeedScheduleService;
import com.mysite.Soda.deleteFeed.DeleteFeedTextService;
import com.mysite.Soda.deleteFeed.DeleteFeedTodoService;
import com.mysite.Soda.deleteFeed.DeleteFeedWorkService;
import com.mysite.Soda.deleteFeedPro.DeleteFeedProService;
import com.mysite.Soda.deleteProjectMember.DeleteProjectMemberService;
import com.mysite.Soda.deleteReply.DeleteScheduleReplyService;
import com.mysite.Soda.deleteReply.DeleteTextReplyService;
import com.mysite.Soda.deleteReply.DeleteTodoReplyService;
import com.mysite.Soda.deleteReply.DeleteWorkReplyService;
import com.mysite.Soda.gonothinking.AllScheduleService;
import com.mysite.Soda.insertFeed.FeedDTO;
import com.mysite.Soda.insertFeed.FeedService;
import com.mysite.Soda.insertInvite.InsertInviteService;
import com.mysite.Soda.insertProFeedFolder.InsertFolderService;
import com.mysite.Soda.insertReply.ReplyService;
import com.mysite.Soda.updateFeed.UpdateFeedScheduleDTO;
import com.mysite.Soda.updateFeed.UpdateFeedScheduleService;
import com.mysite.Soda.updateFeed.UpdateFeedTextService;
import com.mysite.Soda.updateFeed.UpdateFeedTodoDTO;
import com.mysite.Soda.updateFeed.UpdateFeedTodoService;
import com.mysite.Soda.updateFeed.UpdateFeedWorkDTO;
import com.mysite.Soda.updateFeed.UpdateFeedWorkService;
import com.mysite.Soda.updateFeed.UpdateTodoCheckService;
import com.mysite.Soda.updateFeedReply.UpdateScheduleReplyService;
import com.mysite.Soda.updateFeedReply.UpdateTextReplyService;
import com.mysite.Soda.updateFeedReply.UpdateTodoReplyService;
import com.mysite.Soda.updateFeedReply.UpdateWorkReplyService;
import com.mysite.Soda.updateProColor.UpdateProColorService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FeedRestController {

	// 피드 생성 INSERT
	private final FeedService feedservice;
	private final HttpSession session;

	@PostMapping("/CreateFeed")
	public ResponseEntity<Void> insertFeed(@RequestBody FeedDTO feedDTO) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		feedservice.feedInsert(feedDTO, userNum);

		return ResponseEntity.ok().build();
	}

	// 각 피드의 댓글 INSERT
	private final ReplyService replyservice;

	@PostMapping("/CreateReply")
	public ResponseEntity<Void> insertReply(@RequestParam(name = "feed_id") int feed_id,
											@RequestParam(name = "commentText") String commentText) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		replyservice.replyInsert(feed_id, userNum, commentText);
		return ResponseEntity.ok().build();
	}

	// 피드에서 프로젝트 색상 변경 UPDATE
	private final UpdateProColorService updateprocolorservice;

	@PostMapping("/UpdateProColor")
	@ResponseBody
	public String updateProColor(@RequestParam(name = "myProColorTypes") int myProColorTypes,
			@RequestParam(name = "project_id") int project_id) {
		String color = updateprocolorservice.ProColorUpdate(myProColorTypes, project_id);

		return color;
	}

	// 피드에서 프로젝트 폴더 INSERT
	private final InsertFolderService insertfolderservice;

	@PostMapping("/InsertProFeedFolder")
	public ResponseEntity<Void> insertProFeedFolder(@RequestParam(name = "folderName") String folderName,
			@RequestParam(name = "project_id") int project_id) {
		insertfolderservice.proFeedFoler(folderName, project_id);
		return ResponseEntity.ok().build();
	}

	// 피드에서 프로젝트 나가기 DELETE
	private final DeleteProjectMemberService deleteprojectmemberservice;

	@PostMapping("/DeleteProjectMember")
	public ResponseEntity<Void> deleteProjectMember(@RequestParam(name = "project_id") int project_id) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;

		boolean isAdmin = deleteprojectmemberservice.isAdmin(userNum, project_id);
		if (isAdmin) {
			System.out.println(isAdmin);
			// 어드민이면 넘어왔음
			boolean adminTF = deleteprojectmemberservice.getAdmin(project_id);
			if (adminTF) {
				System.out.println(adminTF);
				deleteprojectmemberservice.deleteProjectMember(project_id, userNum);
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			deleteprojectmemberservice.deleteProjectMember(project_id, userNum);
			return ResponseEntity.ok().build();
		}
	}

	// 피드에서 프로젝트 삭제 DELETE
	private final DeleteFeedProService deletefeedproservice;

	@PostMapping("/deleteProjectInFeed")
	public ResponseEntity<Void> deletePro(@RequestParam(name = "project_id") int project_id) {
		// 임시값
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;

		boolean thisAdmin = deletefeedproservice.thisAdmin(project_id, userNum);
		if (thisAdmin) {
			deletefeedproservice.deleteFeedProInAdmin(project_id, userNum);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	// 피드 글 수정 UPDATE
	private final UpdateFeedTextService updatefeedtextservice;

	@PostMapping("/UpdateFeedText")
	public ResponseEntity<Void> updateText(@RequestParam(name = "feedModifyTitle") String feedModifyTitle,
			@RequestParam(name = "feedModifyContent") String feedModifyContent,
			@RequestParam(name = "feed_id") int feed_id) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		updatefeedtextservice.updateText(feedModifyTitle, feedModifyContent, feed_id, userNum);

		return ResponseEntity.ok().build();
	}

	// 피드 업무 수정 UPDATE
	private final UpdateFeedWorkService updatefeedworkservice;

	@PostMapping("/UpdateFeedWork")
	public ResponseEntity<Void> updateWork(@RequestBody UpdateFeedWorkDTO updatefeedworkdto) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		
		updatefeedworkservice.allfeedwork(updatefeedworkdto, userNum);
		
		return ResponseEntity.ok().build();
	}

	// 피드 일정 수정 UPDATE
	private final UpdateFeedScheduleService updatefeedscheduleservice;

	@PostMapping("/UpdateFeedSchedule")
	public ResponseEntity<Void> updateSchedule(@RequestBody UpdateFeedScheduleDTO updatefeedscheduledto) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		updatefeedscheduleservice.updateschedule(updatefeedscheduledto, userNum);

		return ResponseEntity.ok().build();
	}

	// 피드 할일 수정 UPDATE
	private final UpdateFeedTodoService updatefeedtodoservice;

	@PostMapping("/UpdateFeedTodo")
	public ResponseEntity<Void> updateTodo(@RequestBody UpdateFeedTodoDTO updatefeedtododto) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		updatefeedtodoservice.updatetodo(updatefeedtododto, userNum);
		return ResponseEntity.ok().build();
	}

	// 피드 할일 체크박스 수정 UPDATE
	private final UpdateTodoCheckService updatetodocheckservice;

	@PostMapping("/UpdateTodoChek")
	@ResponseBody
	public int updateTodoCheck(@RequestParam(name = "feed_id") int feed_id,
			@RequestParam(name = "isChecked") int isChecked) {
		int checkbox = updatetodocheckservice.updatecheck(feed_id, isChecked);
		return checkbox;
	}

	// 피드 글 삭제 DELETE
	private final DeleteFeedTextService deletefeedtextservice;

	@PostMapping("/DeleteFeedText")
	public ResponseEntity<Void> deletefeedtext(@RequestParam(name = "feed_id") int feed_id) {
		deletefeedtextservice.deletefeed(feed_id);
		return ResponseEntity.ok().build();
	}

	// 피드 업무 삭제 DELETE
	private final DeleteFeedWorkService deletefeedworkservice;

	@PostMapping("/DeleteFeedWork")
	public ResponseEntity<Void> deletefeedwork(@RequestParam(name = "feed_id") int feed_id) {
		deletefeedworkservice.deletefeed(feed_id);
		return ResponseEntity.ok().build();
	}

	// 피드 일정 삭제 DELTE
	private final DeleteFeedScheduleService deletefeedscheduleservice;

	@PostMapping("/DeleteFeedSchedule")
	public ResponseEntity<Void> deletefeedschedule(@RequestParam(name = "feed_id") int feed_id) {
		deletefeedscheduleservice.deletefeed(feed_id);
		return ResponseEntity.ok().build();
	}

	// 피드 할일 삭제 DELETE
	private final DeleteFeedTodoService deletefeedtodoservice;

	@PostMapping("/DeleteFeedTodo")
	public ResponseEntity<Void> deletefeedtodo(@RequestParam(name = "feed_id") int feed_id) {
		deletefeedtodoservice.deletefeed(feed_id);
		return ResponseEntity.ok().build();
	}

	// 피드 글 댓글 수정 UPDATE
	private final UpdateTextReplyService updatetextreplyservice;

	@PostMapping("/UpdateTextReply")
	public ResponseEntity<Void> updatetextreply(@RequestParam(name = "reply_id") int reply_id,
			@RequestParam(name = "modifyReplyText") String modifyInput) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		int thisMember = updatetextreplyservice.thisMember(reply_id, userNum);
		if (thisMember == userNum) {
			updatetextreplyservice.updatereply(reply_id, modifyInput, userNum);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	// 피드 업무 댓글 수정 UPDATE
	private final UpdateWorkReplyService updateworkreplyservice;

	@PostMapping("/UpdateWorkReply")
	public ResponseEntity<Void> updateworkreply(@RequestParam(name = "reply_id") int reply_id,
			@RequestParam(name = "modifyReplyWork") String workVal) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		int thisMember = updateworkreplyservice.thisMember(reply_id, userNum);
		if (thisMember == userNum) {
			updateworkreplyservice.updatereply(reply_id, workVal, userNum);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// 피드 일정 댓글 수정 UPDATE
	private final UpdateScheduleReplyService updateschedulereplyservice;

	@PostMapping("/UpdateScheduleReply")
	public ResponseEntity<Void> updateschedulereply(@RequestParam(name = "reply_id") int reply_id,
			@RequestParam(name = "modifyReplySchedule") String scheduleVal) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;

		int thisMember = updateschedulereplyservice.thisMember(reply_id, userNum);
		if (thisMember == userNum) {
			updateschedulereplyservice.updatereply(reply_id, scheduleVal, userNum);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// 피드 할일 댓글 수정 UPDATE
	private final UpdateTodoReplyService updatetodoreplyservice;

	@PostMapping("/UpdateTodoReply")
	public ResponseEntity<Void> updatetodoreply(@RequestParam(name = "reply_id") int reply_id,
			@RequestParam(name = "modifyReplyTodo") String todoVal) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;

		int thisMember = updatetodoreplyservice.thisMember(reply_id, userNum);
		if (thisMember == userNum) {
			updatetodoreplyservice.updatereply(reply_id, todoVal, userNum);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// 피드 글 댓글 삭제 DELETE
	private final DeleteTextReplyService deletetextreplyservice;

	@PostMapping("/DeleteTextReply")
	public ResponseEntity<Void> deletetextreply(@RequestParam(name = "reply_id") int reply_id, @RequestParam(name="feed_id") int feed_id) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;

		int thisMember = deletetextreplyservice.thisMember(reply_id, userNum);
		if (thisMember == userNum) {
			deletetextreplyservice.deletereply(reply_id, userNum, feed_id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// 피드 업무 댓글 삭제 DELETE
	private final DeleteWorkReplyService deleteworkreplyservice;

	@PostMapping("/DeleteWorkReply")
	public ResponseEntity<Void> deleteworkreply(@RequestParam(name = "reply_id") int reply_id, @RequestParam(name="feed_id")int feed_id) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;

		int thisMember = deleteworkreplyservice.thisMember(reply_id, userNum);
		if (thisMember == userNum) {
			deleteworkreplyservice.deletereply(reply_id, userNum, feed_id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// 피드 일정 댓글 삭제 DELETE
	private final DeleteScheduleReplyService deleteschedulereplyservice;

	@PostMapping("/DeleteScheduleReply")
	public ResponseEntity<Void> deleteschedulereply(@RequestParam(name = "reply_id") int reply_id, @RequestParam(name="feed_id") int feed_id) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;

		int thisMember = deleteschedulereplyservice.thisMember(reply_id, userNum);
		if (thisMember == userNum) {
			deleteschedulereplyservice.deletereply(reply_id, userNum, feed_id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// 피드 할일 댓글 삭제 DELETE
	private final DeleteTodoReplyService deletetodoreplyservice;

	@PostMapping("/DeleteTodoReply")
	public ResponseEntity<Void> deletetodoreply(@RequestParam(name = "reply_id") int reply_id, @RequestParam(name="feed_id") int feed_id) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;

		int thisMember = deletetodoreplyservice.thisMember(reply_id, userNum);
		if (thisMember == userNum) {
			deletetodoreplyservice.deletereply(reply_id, userNum, feed_id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// 피드 모든 피드 좋아요 INSERT, DELETE
	private final ToggleLikeyService togglelikeyservice;

	@PostMapping("/ToggleLikey")
	public ResponseEntity<Void> toggleLike(@RequestParam(name = "feed_id") int feed_id) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		togglelikeyservice.togglelikey(feed_id, userNum);

		return ResponseEntity.ok().build();
	}

	// 피드 북마크 설정 INSERT, DELETE
	private final ToggleBookmarkService togglebookmarkservice;

	@PostMapping("/ToggleBookmark")
	public ResponseEntity<Void> toggleBookmark(@RequestParam(name = "feed_id") int feed_id) {
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		togglebookmarkservice.togglebookmark(feed_id, userNum);

		return ResponseEntity.ok().build();
	}

	// 프로젝트 초대하기 INSERT
	private final InsertInviteService insertinviteservice;

	@PostMapping("/InsertInvite")
	public ResponseEntity<Void> insertmember(@RequestParam(name = "member_id") int userNum, @RequestParam(name="project_id") int project_id) {

		insertinviteservice.insertmember(userNum, project_id);
		return ResponseEntity.ok().build();
	}
	
	// 피드 일정의 참석, 불참, 미정에 대한 CRUD
	private final AllScheduleService allscheduleservice;
	
	@PostMapping("/AllSchedule")
	public ResponseEntity<Void> allschedule(@RequestParam(name="feed_id")int feed_id, @RequestParam(name="myGoNoThinkingType")int scheduleType){
		Object memIdobj = session.getAttribute("memberId");
		int userNum = (int) memIdobj;
		switch (scheduleType) {
			case 1 : 
				return allscheduleservice.go(feed_id, userNum);
			case 2 : 
				return allscheduleservice.no(feed_id, userNum);
			case 3 :
				return allscheduleservice.thinking(feed_id, userNum);
			default:
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
