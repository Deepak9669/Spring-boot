package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.StudentDTO;

public class StudentForm extends BaseForm {

	@NotEmpty(message = "name id required")
	private String name;

	@NotEmpty(message = "course id required")
	private String course;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public BaseDTO getDto() {

		StudentDTO dto = new StudentDTO();

		dto.setName(name);
		dto.setCourse(course);

		return dto;
	}

}
