package com.project.professor.allocation.repository;

import com.github.javafaker.Faker;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;
	
	Faker faker = new Faker();

	@Test
	public void findAll() {
		// Arrange
        List<Department> department = departmentRepository.findAll();
		// Act

		// Print
        System.out.println(department);
	}
	@Test
    public void findById() {
        // Arrange
    	Department department = departmentRepository.findById(1L).orElse(null);

        // Act
        

        // Print
        System.out.println(department);
	}

	@Test
	public void save_create() throws ParseException {
		// Arrange
		Department department = new Department();
		department.setName("geladeira");

		// Act
		Department departments = departmentRepository.save(department);

		// Print
		System.out.println(departments);

	}

	@Test
	public void save_update() throws ParseException {
		// Arrange
		Department department = new Department();

		department.setName("4");
		department.setId(1L);
		
		// Act
		Department departments = departmentRepository.save(department);

		// Print
		System.out.println(departments);

	}

	@Test
	public void deleteById() {
		// Arrange
        departmentRepository.deleteById(1L);
		// Act

	}

	@Test
	public void deleteAll() {
		// Act
        departmentRepository.deleteAllInBatch();
	}
}
