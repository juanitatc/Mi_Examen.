package com.example.mi_examen.Controller;

import com.example.mi_examen.model.Evento;
import com.example.mi_examen.dto.dtoEvento;
import jdk.jfr.Event;

import java.sql.SQLException;
import java.util.ArrayList;

public class CtrEvento {

    private Evento evento3;

    public CtrEvento(){
        evento3 = new Evento();
    }

    public dtoEvento addEvento(dtoEvento Evento){
        try {
            Evento newEvento = evento3.create(Evento);
            return new dtoEvento(newEvento.getId(), newEvento.getTitulo(), newEvento.getDescripcion(), newEvento.getFecha(), newEvento.getUbicacion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<dtoEvento> getAllEventos() {
        try {
            ArrayList<Evento> eventos = evento3.all(); // Llama al método 'all' del modelo
            ArrayList<dtoEvento> dtoEventos = new ArrayList<>();

            for (Evento evento : eventos) {
                dtoEvento Dtoevento = new dtoEvento(
                        evento.getId(),
                        evento.getTitulo(),
                        evento.getDescripcion(),
                        evento.getFecha(),
                        evento.getUbicacion()
                );
                dtoEventos.add(Dtoevento);
            }

            return dtoEventos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public dtoEvento getEventoById(int EventoId) {
        try {
            Evento evento1 = evento3.findById(EventoId);
            if (evento1 != null) {
                return new dtoEvento(evento1.getId(), evento1.getTitulo(), evento1.getDescripcion(), evento1.getFecha(), evento1.getUbicacion());
            } else {
                throw new RuntimeException("No exiten eventos");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public dtoEvento updateEvento(int EventoId, dtoEvento updateEvento) {
        try {
            Evento evento = new Evento(
                    EventoId,
                    updateEvento.getTitulo(),
                    updateEvento.getDescripcion(),
                    updateEvento.getFecha(),
                    updateEvento.getUbicacion()

            );

            Evento updated = evento.update(evento);
            return new dtoEvento(updated.getId(), updated.getTitulo(), updated.getDescripcion(), updated.getFecha(), updated.getUbicacion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEvento(int EventoId) {
        try {
            evento3.delete(EventoId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
