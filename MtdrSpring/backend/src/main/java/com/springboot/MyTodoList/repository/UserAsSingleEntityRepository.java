package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.UserAsSingleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAsSingleEntityRepository extends JpaRepository<UserAsSingleEntity, Long> {
    List<UserAsSingleEntity> findByIsManager(boolean manager);

//    List<UsuarioTarea> findByTitleContaining(String title);
}
