package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.repository.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TareasService {

    private final TareasRepository tareasRepository;

    @Autowired
    public TareasService(TareasRepository tareasRepository) {
        this.tareasRepository = tareasRepository;
    }

    public List<Tarea> obtenerTareasPorIdUsuario(int idUsuario) {
        return tareasRepository.findByidUsuario(idUsuario);
    }
}
