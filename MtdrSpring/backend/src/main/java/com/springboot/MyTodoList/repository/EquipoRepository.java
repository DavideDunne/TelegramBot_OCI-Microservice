package com.springboot.MyTodoList.repository;


import java.util.List;

import com.springboot.MyTodoList.model.Equipo;
import com.springboot.MyTodoList.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

}
