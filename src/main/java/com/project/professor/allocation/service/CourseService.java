package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.repository.CourseRepository;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Allocation;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;
	private final AllocationRepository allocationRepository;

	public CourseService(CourseRepository courseRepository, AllocationRepository allocationRepository) {
		super();
		this.courseRepository = courseRepository;
		this.allocationRepository = allocationRepository;
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
		
	}

	public Course findById(Long id) {
		return courseRepository.findById(id).orElse(null);
	}
	
	//A IDEIA É RETORNAR UMA LISTA VAZIA SE NAO HOUVER COURSE QUE CONTENHA NAME!
	public List<Course> findByNameContainingIgnoreCase(String name) {
		return courseRepository.findByNameContainingIgnoreCase(name);
	}

	public Course save(Course course) {
		course.setId(null);
		return saveInternal(course);
	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id != null && courseRepository.existsById(id)) {
			return saveInternal(course);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		}
	}
                      
	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}


	
	
	
	private Course saveInternal(Course course) {
		course = courseRepository.save(course);
		
		List<Allocation> allocations = allocationRepository.findByCourseId(course.getId());
		course.setAllocations(allocations);

		return course;
	}
	
}
