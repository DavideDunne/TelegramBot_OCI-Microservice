package com.springboot.MyTodoList.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UsuarioTareaRepositoryTest {
    @Autowired
    private UserAsSingleEntityRepository userRepository;

    @BeforeEach
    void setUp() {
        // Clear existing data
        userRepository.deleteAll();

        // Add test data
//        userRepository.save(new UserAsSingleEntity(9, "John", "A", "Doe", "B", 1));
//        userRepository.save(new UserAsSingleEntity(10, "Jane", "B", "Doe", "C", 1));
//        userRepository.save(new UserAsSingleEntity(11, "Bob", "C", "Smith", "D", 0));

        userRepository.save(new UserAsSingleEntity("fdsfgh", 9L,"John", "A", "Doe", "B", true, 32L,9L,32L, "Equipo 32",9L, 100L,"First task new model", "Description first task", false,9L));
    }

    @Test
    void findByManagerEquals() {
        assertEquals(3, userRepository.findByIsManager(true).size());
    }
}