package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Usuario;
import com.springboot.MyTodoList.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public ResponseEntity<Usuario> getItemById(int id) {
        Optional<Usuario> data = usuarioRepository.findById(id);
        return data.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean deleteUsuario(int id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario updateUsuario(int id, Usuario us) {
        Optional<Usuario> data = usuarioRepository.findById(id);
        if (data.isPresent()) {
            Usuario usuario = data.get();
            usuario.setNombre1(us.getNombre1());
            usuario.setNombre2(us.getNombre2());
            usuario.setApellido1(us.getApellido1());
            usuario.setApellido2(us.getApellido2());
            usuario.setManager(us.isManager());
            usuario.setTelegramUsername(us.getTelegramUsername());
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }
}
