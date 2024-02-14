package com.mysite.Soda.updateProColor;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateProColorService {
	
	private final UpdateProColorDAO updateprocolordao;

	public String ProColorUpdate(int myProColorTypes, int project_id) {
		updateprocolordao.procolor(myProColorTypes, project_id);
		return updateprocolordao.getColor(myProColorTypes);
	}
}
