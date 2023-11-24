package com.example.mi_examen.model;

import com.example.mi_examen.dto.dtoEvento;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CRUD {
    Evento create(dtoEvento student) throws SQLException;

    public ArrayList<Evento> all();

    public Evento findById(int id) throws SQLException;

    Evento update(Evento student) throws SQLException;

    void delete(int studentId) throws SQLException;

}
