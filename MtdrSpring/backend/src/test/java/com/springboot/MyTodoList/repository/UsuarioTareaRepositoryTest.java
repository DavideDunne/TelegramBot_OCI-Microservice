package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.UsuarioTarea;
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
    private UsuarioTareaRepository usuarioTareaRepository;

    @BeforeEach
    void setUp() {
        // Clear existing data
        usuarioTareaRepository.deleteAll();

        // Add test data
        usuarioTareaRepository.save(new UsuarioTarea(1, "John", "A", "Doe", "B", true));
        usuarioTareaRepository.save(new UsuarioTarea(2, "Jane", "B", "Doe", "C", true));
        usuarioTareaRepository.save(new UsuarioTarea(3, "Bob", "C", "Smith", "D", false));
    }

    @Test
    void findByManagerEquals() {
        assertEquals(2, usuarioTareaRepository.findByIsManager(true).size());
    }
}