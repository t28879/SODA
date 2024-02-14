package com.mysite.Soda.gonothinking;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AllScheduleService {
	
	private final AllScheduleDAO allscheduledao;
	
	public ResponseEntity<Void> go(int feed_id, int userNum) {
        if (checkCanUpdate(feed_id, userNum)) {
        	allscheduledao.updateGoColumn(feed_id);
        	allscheduledao.addOrUpdateSchedule(feed_id, userNum);
            return ResponseEntity.ok().build();
        } else {
            // 이미 참석한 상태에서 다시 참석을 눌렀으므로 취소 처리
        	allscheduledao.updateGoColumnCancel(feed_id);
        	allscheduledao.deleteSchedule(feed_id, userNum);
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Void> no(int feed_id, int userNum) {
        if (checkCanUpdate(feed_id, userNum)) {
        	allscheduledao.updateNoColumn(feed_id);
        	allscheduledao.addOrUpdateSchedule(feed_id, userNum);
            return ResponseEntity.ok().build();
        } else {
            // 이미 불참한 상태에서 다시 불참을 눌렀으므로 취소 처리
        	allscheduledao.updateNoColumnCancel(feed_id);
        	allscheduledao.deleteSchedule(feed_id, userNum);
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Void> thinking(int feed_id, int userNum) {
        if (checkCanUpdate(feed_id, userNum)) {
        	allscheduledao.updateThinkingColumn(feed_id);
        	allscheduledao.addOrUpdateSchedule(feed_id, userNum);
            return ResponseEntity.ok().build();
        } else {
            // 이미 미정인 상태에서 다시 미정을 눌렀으므로 취소 처리
        	allscheduledao.updateThinkingColumnCancel(feed_id);
        	allscheduledao.deleteSchedule(feed_id, userNum);
            return ResponseEntity.badRequest().build();
        }
    }

    private boolean checkCanUpdate(int feed_id, int userNum) {
        // 조건 1: 이미 gonoschedule에 행이 있는지 확인하는 로직을 구현
        return !allscheduledao.checkIfAlreadyScheduled(feed_id, userNum);
    }
}
