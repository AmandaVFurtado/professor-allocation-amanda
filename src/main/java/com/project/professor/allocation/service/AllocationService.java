package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	
	}
	
	public List<Allocation> findAll(){
		
		List<Allocation> allocations = allocationRepository.findAll();
		
		return allocations;
	}
	
	public Allocation findById(Long id) {
		
		Allocation allocation = allocationRepository.findById(id).orElse(null);
		
		return allocation;
	}
	
	public List<Allocation> findByProfessor(Long professorId) {
		
		List<Allocation> allocations = allocationRepository.findByProfessorId(professorId);
		
		return allocations;
	}
	
	public List<Allocation> findByCourse(Long courseId) {
		
		List<Allocation> allocations = allocationRepository.findByCourseId(courseId);
		
		return allocations;
	}
	
	public Allocation create(Allocation allocation) {
		
		allocation.setId(null);
		return saveInternal(allocation);
	}
	
	public Allocation update(Allocation allocation) {
		
		Long id = allocation.getId();
		
		if (id != null && allocationRepository.existsById(id)) {
			
			return saveInternal(allocation);
			
		} else {
			
			return null;
		}
	}
		
	public void deleteById(Long id) {
			
			if (allocationRepository.existsById(id)) {
				
				allocationRepository.deleteById(id);
			}
		}
		
	public void deleteAll() {
			
			allocationRepository.deleteAllInBatch();
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Regra de Negócio
	private Allocation saveInternal(Allocation allocation) {
		
		if(hasCollision(allocation)) {
			
			throw new RuntimeException();
			
		}else {
			
			Allocation allocationNew = allocationRepository.save(allocation);
			
			Professor professor = professorService.findById(allocation.getProfessorId());
			allocation.setProfessor(professor);
			
			Course course = courseService.findById(allocation.getCourseId());
			allocation.setCourse(course);
			
			return allocationRepository.save(allocation);
		}
	}
	
	boolean isEndHourGreaterThanStartHour(Allocation allocation) {
		
		return allocation != null && allocation.getStart() != null && allocation.getEnd() != null
	            && allocation.getEnd().compareTo(allocation.getStart()) > 0;
	}
	
	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}

}
