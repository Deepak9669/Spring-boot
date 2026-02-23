package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_COLLEGE")
public class CollegeDTO extends BaseDTO {

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "STATE")
	private String state;

	@Column(name = "CITY")
	private String city;

	@Column(name = "PHONE_NO")
	private String phoneNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}
