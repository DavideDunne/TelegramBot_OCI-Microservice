package com.springboot.MyTodoList.model;


import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@SecondaryTable(name = "equipo_usuario", pkJoinColumns = @PrimaryKeyJoinColumn(name = "usuario_id"))
@SecondaryTable(name = "equipos", pkJoinColumns = @PrimaryKeyJoinColumn(name = "equipo_id"))
@SecondaryTable(name = "tareas", pkJoinColumns = @PrimaryKeyJoinColumn(name = "usuario_id"))
public class UserAsSingleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    Long usuarioId;

    @Column(name = "telegram_username")
    String telegramUser;

    @Column(name = "nombre1")
    String nombre1;

    @Column(name = "nombre2")
    String nombre2;

    @Column(name = "apellido1")
    String apellido1;

    @Column(name = "apellido2")
    String apellido2;

    @Column(name = "isManager")
    boolean isManager;

    @Column(name = "team_id", table = "equipo_usuario")
    Long teamId;

    @JoinColumn(name = "user_id", table = "equipo_usuario")
    Long userId;

    @JoinColumn(name = "equipo_id", table = "equipos")
    Long equipoId;

    @Column(name = "nombre_equipo", table = "equipos")
    String nombreEquipo;

    @Column(name = "manager_id", table = "equipos")
    Long managerId;

    @Column(name = "tarea_id", table = "tareas")
    Long tareaId;

    @Column(name = "titulo", table = "tareas")
    String titulo;

    @Column(name = "descripcion", table = "tareas")
    String descripcion;

    @Column(name = "isdone", table = "tareas")
    boolean isdone;

    @JoinColumn(name = "usuario_id", table = "tareas")
    Long usuario_id;

    // standard getters and setters

    public UserAsSingleEntity(){
    }


    public UserAsSingleEntity(String telegramUser, Long usuarioId, String nombre1, String nombre2, String apellido1, String apellido2, boolean isManager, Long teamId, Long userId, Long equipoId, String nombreEquipo, Long managerId, Long tareaId, String titulo, String descripcion, boolean isdone, Long usuario_id) {
        this.telegramUser = telegramUser;
        this.usuarioId = usuarioId;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.isManager = isManager;
        this.teamId = teamId;
        this.userId = userId;
        this.equipoId = equipoId;
        this.nombreEquipo = nombreEquipo;
        this.managerId = managerId;
        this.tareaId = tareaId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.isdone = isdone;
        this.usuario_id = usuario_id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTelegramUser() {
        return telegramUser;
    }

    public void setTelegramUser(String telegramUser) {
        this.telegramUser = telegramUser;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Long equipoId) {
        this.equipoId = equipoId;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getTareaId() {
        return tareaId;
    }

    public void setTareaId(Long tareaId) {
        this.tareaId = tareaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isIsdone() {
        return isdone;
    }

    public void setIsdone(boolean isdone) {
        this.isdone = isdone;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
}