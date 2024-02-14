package com.mysite.Soda.chatting;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChattService {
	private final InviteMemberDAO invitememberdao;

	public List<InviteMemberVO> inviteMember(String[] selectedItem) {
		return invitememberdao.inviteMember(selectedItem);
	}
}
