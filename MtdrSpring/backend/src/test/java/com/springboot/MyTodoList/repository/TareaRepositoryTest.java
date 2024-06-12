package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.Equipo;
import com.springboot.MyTodoList.model.EquipoUsuario;
import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TareaRepositoryTest {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private EquipoUsuarioRepository equipoUsuarioRepository;

    @BeforeEach
    void setUp() {
        // Clear existing data
        tareaRepository.deleteAll();
        equipoUsuarioRepository.deleteAll();
        equipoRepository.deleteAll();
        usuarioRepository.deleteAll();

        // Add test data
        Usuario manager = new Usuario("John", "A", "Doe", "B", true);
        usuarioRepository.save(manager);

        Usuario developer1 = new Usuario("Jane", "B", "Doe", "C", false);
        usuarioRepository.save(developer1);

        Usuario developer2 = new Usuario("Bob", "C", "Smith", "D", false);
        usuarioRepository.save(developer2);

        Equipo equipo = new Equipo("Team A", manager);
        equipoRepository.save(equipo);

        EquipoUsuario equipoUsuario1 = new EquipoUsuario(equipo, developer1);
        equipoUsuarioRepository.save(equipoUsuario1);

        EquipoUsuario equipoUsuario2 = new EquipoUsuario(equipo, developer2);
        equipoUsuarioRepository.save(equipoUsuario2);

        Tarea tarea1 = new Tarea("Task 1", "Description 1", false, developer1);
        tareaRepository.save(tarea1);

        Tarea tarea2 = new Tarea("Task 2", "Description 2", false, developer1);
        tareaRepository.save(tarea2);

        Tarea tarea3 = new Tarea("Task 3", "Description 3", false, developer2);
        tareaRepository.save(tarea3);
    }

    @Test
    void findByManagerEquals() {
        assertEquals(3, tareaRepository.findByManagerId(1L).size());
    }
}
