package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Esto debe estar aquí
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping(value = "/usuario/{idUsuario}/tareas") // Movido aquí
    public ResponseEntity<List<Tarea>> getAllTareasByidUsuario(@PathVariable int idUsuario) {
        List<Tarea> tareas = tareaService.findAllByidUsuario(idUsuario);
        if (tareas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tareas, HttpStatus.OK);
    }
}
