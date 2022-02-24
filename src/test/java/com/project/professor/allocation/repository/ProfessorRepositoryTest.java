package com.project.professor.allocation.repository;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;
import com.project.professor.allocation.entity.Course;
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
public class ProfessorRepositoryTest {

	@Autowired
	ProfessorRepository professorRepository;
	
	
	@Test
    public void findAll() {
        // Act
        List<Professor> professor = professorRepository.findAll();

        // Print
        System.out.println(professor);
    }

    @Test
    public void findById() {
        // Arrange
        Professor professor = professorRepository.findById(1L).orElse(null);

        // Act
        

        // Print
        System.out.println(professor);
    }

    @Test
    public void save_create() throws ParseException {
        // Arrange
    	Faker faker = new Faker();
    	
        Professor professor = new Professor();
        professor.setName(faker.harryPotter().character().toString());
        professor.setCpf(String.valueOf(generateId()));
        professor.setDepartmentId(1L);

        // Act
        Professor professors = professorRepository.save(professor);

        // Print
        System.out.println(professors);
    }

    @Test
    public void save_update() throws ParseException {
        // Arrange
    	Professor professor = new Professor();

		professor.setName("Hagrid da Silva Sauro");
		professor.setCpf("99999999999");
		professor.setDepartmentId(1L);
		professor.setId(1L);
		// Act
		Professor professors = professorRepository.save(professor);

		// Print
		System.out.println(professors);
        
    }

    @Test
    public void deleteById() {
        // Arrange
        professorRepository.deleteById(1L);

        // Act
        
    }

    @Test
    public void deleteAll() {
        // Act
        professorRepository.deleteAllInBatch();
    }

    public long generateId() {
    	  ThreadLocalRandom random = ThreadLocalRandom.current();
    	  return random.nextLong(10_000_000_000L, 100_000_000_000L);
    	
    }
  }
