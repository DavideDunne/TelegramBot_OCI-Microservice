package com.springboot.MyTodoList.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UsuarioTareaRepositoryTest {
    @Autowired
    private UsuarioTareaRepository repository;

    @Test
    void findByManagerEquals() {
        assertEquals(2, repository.findByManagerEquals(1).size());
    }
}