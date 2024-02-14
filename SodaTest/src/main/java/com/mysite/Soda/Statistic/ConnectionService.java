package com.mysite.Soda.Statistic;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConnectionService {
	
	private final ConnectionDAO connectiondao;
	
	public List<ConnectionVO> getconnectionstatistic(int member_id){
		
		return connectiondao.getconstatisticinfo(member_id);
	}
	public List<ConnectionVO> getuseagestatistic(int member_id){
		
		return connectiondao.getusestatisticinfo(member_id);
	}
}
