package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import com.rays.common.BaseDto;

@Entity
@Table(name = "ST_ROLE")
public class RoleDTO extends BaseDto {
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;

}
