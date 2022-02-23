package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Allocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

    @Autowired
    AllocationRepository allocationRepository;

    @Test
    public void findAll() {
        // Act
        List<Allocation> allocation = allocationRepository.findAll();

        // Print
        System.out.println(allocation);
    }

    @Test
    public void findById() {
        // Arrange
        Allocation allocation = allocationRepository.findById(1L).orElse(null);

        // Act
        

        // Print
        System.out.println(allocation);
    }

    @Test
    public void findByProfessorId() {
        // Arrange
        

        // Act
        

        // Print
        
    }

    @Test
    public void findByCourseId() {
        // Arrange
        

        // Act
        

        // Print
        
    }

    @Test
    public void save_create() throws ParseException {
        // Arrange
        Allocation allocation = new Allocation();
        allocation.setCourseId(1L);
        allocation.setProfessorId(1L);
        allocation.setDay(DayOfWeek.FRIDAY);
        allocation.setStart(sdf.parse("10:30-0000"));
        allocation.setEnd(sdf.parse("11:30-0000"));

        // Act
        Allocation allocations = allocationRepository.save(allocation);

        // Print
        System.out.println(allocations);
    }

    @Test
    public void save_update() throws ParseException {
        // Arrange
    	Allocation allocation = new Allocation();
        allocation.setCourseId(1L);
        allocation.setProfessorId(1L);
        allocation.setDay(DayOfWeek.MONDAY);
        allocation.setStart(sdf.parse("12:00-0000"));
        allocation.setEnd(sdf.parse("13:00-0000"));
        allocation.setId(1L);

        // Act
        Allocation allocations = allocationRepository.save(allocation);

        // Print
        System.out.println(allocations);
        
    }

    @Test
    public void deleteById() {
        // Arrange
        allocationRepository.deleteById(1L);

        // Act
        
    }

    @Test
    public void deleteAll() {
        // Act
        allocationRepository.deleteAllInBatch();
    }
}
