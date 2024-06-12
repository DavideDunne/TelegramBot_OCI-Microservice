package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByUsuarioId(Long usuarioId);

    @Query("SELECT t FROM Tarea t WHERE t.usuario.usuario_id IN " +
            "(SELECT eu.usuario.usuario_id FROM EquipoUsuario eu " +
            "JOIN eu.equipo e " +
            "WHERE e.manager.usuario_id = :managerId)")
    List<Tarea> findByManagerId(@Param("managerId") Long managerId);
}
