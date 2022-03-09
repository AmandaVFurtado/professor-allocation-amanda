package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.repository.DepartmentRepository;
import com.project.professor.allocation.entity.Department;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;
	
	

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	public List<Department> findAll(String name) {
		if (name == null) {
			return departmentRepository.findAll();
		} else {
			return departmentRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	public Department findByName(String name) {
		return departmentRepository.findByName(name).orElse(null);
		
	}

	public Department save(Department departament) {
		departament.setId(null);
		return saveInternal(departament);
	}

	public Department update(Department departament) {
		
		Long id = departament.getId();
		
		if (id != null && departmentRepository.existsById(id)) {
			return saveInternal(departament);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		
		if (departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}

	
	
	
	
	
	private Department saveInternal(Department department) {
        department = departmentRepository.save(department);

        return department;
    }

}
