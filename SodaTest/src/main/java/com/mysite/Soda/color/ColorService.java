package com.mysite.Soda.color;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorService {
	private final ColorDAO colordao;
	
	public List<ColorVO> getColor(int member_id) {
		return colordao.getColor(member_id);
	}
}
