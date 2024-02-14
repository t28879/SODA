package com.mysite.Soda.DTO;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="WIDGET")
public class WidgetDTO implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WIDGET_SEQ")
	@SequenceGenerator(name = "WIDGET_SEQ", sequenceName = "WIDGET_SEQ", allocationSize = 1)
	@Column(name = "WIDGET_ID")
	private Integer widgetId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private JoinMember joinMember;
	
	@Column(name = "WIDGET_NAME")
	private String widgetName;
	
	@Column(name = "ORDER_COLUMN")
	private Integer orderColumn;
	
	@PostLoad
	private void onLoad() {
		this.widgetName = generateWidgetName();
		this.orderColumn = generateOrderColumn();
	}
	
	public String generateWidgetName() {
		
		int widgetNameIndex = this.widgetId%6;
		if(this.widgetId%6==0) {
			widgetNameIndex = 6;
		}
		return "list-group-item"+widgetNameIndex;
	}
	
	public Integer generateOrderColumn() {
		int orderColumnIndex = (this.widgetId%6)-1;
		if(this.widgetId%6==0) {
			orderColumnIndex = 5;
		}
		return orderColumnIndex;
	}
}
