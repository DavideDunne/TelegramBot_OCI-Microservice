package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> getTasksForDeveloper(Long usuarioId) {
        return tareaRepository.findByUsuarioId(usuarioId);
    }

    public List<Tarea> getTasksForManager(Long managerId) {
        return tareaRepository.findByManagerId(managerId);
    }

    public Tarea createOrUpdateTask(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Additional methods as needed
}
