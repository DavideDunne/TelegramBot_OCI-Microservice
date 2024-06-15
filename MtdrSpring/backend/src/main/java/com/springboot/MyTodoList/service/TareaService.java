package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.model.Equipo;
import com.springboot.MyTodoList.model.Usuario;

import com.springboot.MyTodoList.model.EquipoUsuario;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.springboot.MyTodoList.repository.TareaRepository;
import com.springboot.MyTodoList.repository.EquipoRepository;
import com.springboot.MyTodoList.repository.UsuarioRepository;
import com.springboot.MyTodoList.repository.EquipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.RowMapper;


import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EquipoUsuarioRepository equipoUsuarioRepository;

    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    public Tarea addTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public boolean deleteTarea(int id) {
        try {
            tareaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Tarea updateTarea(int id, Tarea ta) {
        ta.setNombre(ta.getNombre());
        ta.setDescripcion(ta.getDescripcion());
        ta.setIdUsuario(ta.getIdUsuario());
        ta.setCompletado(ta.isCompletado());
        return tareaRepository.save(ta);
    }

    public List<Tarea> findAllByidUsuario(Long idUsuario) {
        return tareaRepository.findAllByidUsuario(idUsuario);
    }

    public Tarea getTareaBynombre(String nombre) {
        return tareaRepository.findBynombre(nombre);
    }

    public Tarea getTareaById(int id) {
        return tareaRepository.findById(id);
    }

    // Añadir el nuevo método
    public List<Tarea> findCompletedTasksByUserId(Long idUsuario) {
        return tareaRepository.findAllByidUsuarioAndCompletado(idUsuario, true);
    }

    // Método para obtener tareas por equipo identificado por el ID del manager


    public List<Tarea> findTareasByManagerId(Long idManager) {
        String sql = "SELECT " +
                     "t.id, " +
                     "t.nombre, " +
                     "t.descripcion, " +
                     "t.completado, " +
                     "t.id_usuario, " +
                     "u.nombre1, " +
                     "u.apellido1, " +
                     "eu.team_id, " +
                     "e.nombre " +
                     "FROM tarea t " +
                     "JOIN usuario u ON t.id_usuario = u.TELEGRAM_USERNAME " +
                     "JOIN equipo_usuario eu ON u.TELEGRAM_USERNAME = eu.user_id " +
                     "JOIN equipo e ON eu.team_id = e.id " +
                     "WHERE e.id = (SELECT team_id FROM equipo WHERE id_manager = ?) " +
                     "ORDER BY eu.team_id, t.id";

        // Use JdbcTemplate to execute the query
        List<Tarea> tareas = jdbcTemplate.query(sql, new Object[]{idManager}, new RowMapper<Tarea>() {
            public Tarea mapRow(ResultSet rs, int rowNum) throws SQLException {
                Tarea tarea = new Tarea();
                tarea.setNombre(rs.getString("nombre"));
                tarea.setCompletado(rs.getBoolean("completado"));
                tarea.setIdUsuario(rs.getLong("id_usuario"));
                // Set other fields as needed
                return tarea;
            }
        });

        return tareas;
    }
    
}

