package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	
	private final AllocationRepository allocationRepository;

	public AllocationService(AllocationRepository allocationRepository) {
		super();
		this.allocationRepository = allocationRepository;
	}
	
	public List<Allocation> findAll(){
		
		List<Allocation> allocations = allocationRepository.findAll();
		
		return allocations;
	}
	
	public Allocation findById(Long id) {
		
		Allocation allocation = allocationRepository.findById(id).orElse(null);
		
		return allocation;
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
	
	private Allocation saveInternal(Allocation allocation) {
		
		if(hasCollision(allocation)) {
			
			throw new RuntimeException();
			
		}else {
		
			return allocationRepository.save(allocation);
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
