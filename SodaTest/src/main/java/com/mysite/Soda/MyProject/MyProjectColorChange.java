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
public class MyProjectColorChange {
	private int selectedColor;
	private List<Integer> selectedDivs;
}
