package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.EquipoUsuario;
import com.springboot.MyTodoList.repository.EquipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoUsuarioService {

    @Autowired
    private EquipoUsuarioRepository equipoUsuarioRepository;

    public List<EquipoUsuario> findAll() {
        return equipoUsuarioRepository.findAll();
    }

    public ResponseEntity<EquipoUsuario> getItemById(int id) {
        Optional<EquipoUsuario> data = equipoUsuarioRepository.findById(id);
        return data.map(equipoUsuario -> new ResponseEntity<>(equipoUsuario, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public EquipoUsuario addEquipoUsuario(EquipoUsuario equipoUsuario) {
        return equipoUsuarioRepository.save(equipoUsuario);
    }

    public boolean deleteEquipoUsuario(int id) {
        try {
            equipoUsuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EquipoUsuario updateEquipoUsuario(int id, EquipoUsuario eu) {
        Optional<EquipoUsuario> data = equipoUsuarioRepository.findById(id);
        if (data.isPresent()) {
            EquipoUsuario equipoUsuario = data.get();
            equipoUsuario.setIdTeam(eu.getIdTeam());
            equipoUsuario.setIdUser(eu.getIdUser());
            return equipoUsuarioRepository.save(equipoUsuario);
        } else {
            return null;
        }
    }
}
