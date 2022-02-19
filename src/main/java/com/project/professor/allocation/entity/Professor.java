package com.project.professor.allocation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(length = 11, unique = true, nullable = false)
	private String cpf;
	
	@Column(name = "department_id", nullable = false)
	private Long departmentId;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "department_id", updatable = false, insertable = false, nullable = false)
	private Department depart;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Long getDepartmentid() {
		return departmentId;
	}
	public void setDepartmentid(Long departmentid) {
		this.departmentId = departmentid;
	}
}
