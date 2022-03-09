package com.project.professor.allocation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.service.ProfessorService;

@RestController
@RequestMapping
public class ProfessorController {
	
	ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;

	}

}
