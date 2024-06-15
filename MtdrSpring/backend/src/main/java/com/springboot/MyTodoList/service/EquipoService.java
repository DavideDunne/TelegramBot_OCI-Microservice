package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Equipo;
import com.springboot.MyTodoList.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public ResponseEntity<Equipo> getItemById(int id) {
        Optional<Equipo> data = equipoRepository.findById(id);
        return data.map(equipo -> new ResponseEntity<>(equipo, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Equipo addEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public boolean deleteEquipo(int id) {
        try {
            equipoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Equipo updateEquipo(int id, Equipo eq) {
        Optional<Equipo> data = equipoRepository.findById(id);
        if (data.isPresent()) {
            Equipo equipo = data.get();
            equipo.setNombre(eq.getNombre());
            equipo.setIdManager(eq.getIdManager());
            return equipoRepository.save(equipo);
        } else {
            return null;
        }
    }
}
