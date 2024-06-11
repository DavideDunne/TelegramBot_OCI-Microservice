package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.UsuarioTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioTareaRepository extends JpaRepository<UsuarioTarea, Long> {
    List<UsuarioTarea> findByIsManager(boolean manager);

//    List<UsuarioTarea> findByTitleContaining(String title);
}
