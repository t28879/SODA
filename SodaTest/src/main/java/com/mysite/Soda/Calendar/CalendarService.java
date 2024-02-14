package com.mysite.Soda.Calendar;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarService {
	
	private final CalendarDAO calendardao;
	
	public List<CalendarVO> tt(int member_id) {
		return calendardao.modifymember(member_id);
	}
	public List<IndividualVO> individual(int member_id){
		return calendardao.indischedule(member_id);
	}
	public int updateindischedule(String date,String hour,String min,String title) {
		String dateformate = date+" "+hour+":"+min;
		calendardao.updateschedule(dateformate, title);
		int curval = calendardao.getIndividual();
		return curval;
	}
	public void deleteindischedule(int individualID) {
		calendardao.deleteschedule(individualID);
	}
}
