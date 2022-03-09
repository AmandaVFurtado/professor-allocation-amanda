package com.project.professor.allocation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.service.AllocationService;

@RestController
@RequestMapping
public class AllocationController {
	
	private final AllocationService allocationService;
	
	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}

}
