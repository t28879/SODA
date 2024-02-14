package com.mysite.Soda.MyProject;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyProjectFolderChange {
	private List<Integer> selectedFolder;
	private List<Integer> selectedDivs;
}
