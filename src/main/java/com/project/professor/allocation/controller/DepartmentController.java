package com.project.professor.allocation.controller;

import com.project.professor.allocation.service.DepartmentService;

public class DepartmentController {
	
	private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        super();
        this.departmentService = departmentService;
    }

}
