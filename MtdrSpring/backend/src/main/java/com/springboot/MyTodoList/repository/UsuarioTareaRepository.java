package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.UsuarioTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@EnableTransactionManagement
public interface UsuarioTareaRepository extends JpaRepository<UsuarioTarea, Long> {
    List<UsuarioTarea> findByManagerEquals(int manager);

//    List<UsuarioTarea> findByTitleContaining(String title);
}
