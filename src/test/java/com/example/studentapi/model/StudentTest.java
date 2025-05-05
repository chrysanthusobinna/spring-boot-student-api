package com.example.studentapi.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidStudent() {
        Student student = new Student("John", "Doe", "john.doe@example.com");
        assertTrue(validator.validate(student).isEmpty());
    }

    @Test
    void testInvalidFirstName() {
        Student student = new Student("J", "Doe", "john.doe@example.com");
        assertFalse(validator.validate(student).isEmpty());
    }

    @Test
    void testInvalidLastName() {
        Student student = new Student("John", "D", "john.doe@example.com");
        assertFalse(validator.validate(student).isEmpty());
    }

    @Test
    void testInvalidEmail() {
        Student student = new Student("John", "Doe", "invalid-email");
        assertFalse(validator.validate(student).isEmpty());
    }

    @Test
    void testEmptyFirstName() {
        Student student = new Student("", "Doe", "john.doe@example.com");
        assertFalse(validator.validate(student).isEmpty());
    }

    @Test
    void testEmptyLastName() {
        Student student = new Student("John", "", "john.doe@example.com");
        assertFalse(validator.validate(student).isEmpty());
    }

    @Test
    void testEmptyEmail() {
        Student student = new Student("John", "Doe", "");
        assertFalse(validator.validate(student).isEmpty());
    }

    @Test
    void testGettersAndSetters() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");

        assertEquals(1L, student.getId());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals("john.doe@example.com", student.getEmail());
    }
} 