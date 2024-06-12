package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    public ResponseEntity<Tarea> getItemById(int id) {
        Optional<Tarea> data = tareaRepository.findById(id);
        return data.map(tarea -> new ResponseEntity<>(tarea, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
        Optional<Tarea> data = tareaRepository.findById(id);
        if (data.isPresent()) {
            Tarea tarea = data.get();
            tarea.setNombre(ta.getNombre());
            tarea.setDescripcion(ta.getDescripcion());
            tarea.setIdUsuario(ta.getIdUsuario());
            tarea.setCompletado(ta.isCompletado());
            return tareaRepository.save(tarea);
        } else {
            return null;
        }
    }

    public List<Tarea> findAllByidUsuario(int idUsuario) {
        return tareaRepository.findAllByidUsuario(idUsuario);
    }
}
