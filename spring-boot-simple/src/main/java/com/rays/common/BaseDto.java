package com.rays.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseDTO {
	@Id
	@GeneratedValue(generator = "ncsPk")
	@GenericGenerator(name = "ncsPk", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)
	protected Long id;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "MODIFIED_B")
	private String modifiedBy;

	@Column(name = "CREATED_DATE_TIME")
	private Timestamp createdDateTime;

	@Column(name = "MODIFIED_DATE_TIME")
	private Timestamp modifiedDateTime;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
