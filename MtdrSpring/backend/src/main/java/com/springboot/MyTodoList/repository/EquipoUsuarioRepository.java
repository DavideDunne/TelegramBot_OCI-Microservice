package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.EquipoUsuario;
import com.springboot.MyTodoList.model.EquipoUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipoUsuarioRepository extends JpaRepository<EquipoUsuario, EquipoUsuarioId> {
}
