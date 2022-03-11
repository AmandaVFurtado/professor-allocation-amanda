package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.ProfessorRepository;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Department;



@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;
	private final AllocationRepository allocationRepository;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService,
			AllocationRepository allocationRepository) {

		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
		this.allocationRepository = allocationRepository;
	}

	public List<Professor> findAll() {
		return professorRepository.findAll();
		
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id).orElse(null);
	}

	//A IDEIA É RETORNAR UMA LISTA VAZIA SE NAO HOUVER PROFESSOR QUE CONTENHA NAME!
	public List<Professor> findByNameContainingIgnoreCase(String name) {
		return professorRepository.findByNameContainingIgnoreCase(name);
	}

	public Professor findByCpf(String cpf) {
		return professorRepository.findByCpf(cpf).orElse(null);

	}

	public Professor save(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return saveInternal(professor);
		} else {
			return null;
		}
	}


	public void deleteById(Long id) {
		if (professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	
	
	
	
	
	private Professor saveInternal(Professor professor) {
		professor = professorRepository.save(professor);
		
		Department department = departmentService.findById(professor.getDepartmentId());
		professor.setDepart(department);
		
		List<Allocation> allocations = allocationRepository.findByProfessorId(professor.getId());
		professor.setAllocations(allocations);
		
		return professor;
	}
}
