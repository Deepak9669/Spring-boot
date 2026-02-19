package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseForm;


public class UserForm extends BaseForm {

	@NotEmpty(message = "first name is required")
	private String firstName;

	@NotEmpty(message = "first name is required")
	private String lastName;

	@NotEmpty(message = "first name is required")
	private String loginId;

	@NotEmpty(message = "first name is required")
	private String password;

	@NotNull(message = "first name is required")
	private Date dob;

	@NotNull(message = "first name is required")
	private Long roleId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}




}