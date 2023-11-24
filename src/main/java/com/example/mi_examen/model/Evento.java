package com.example.mi_examen.model;

import com.example.mi_examen.dto.dtoEvento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Evento extends connection2 implements CRUD {

    public int id;

    protected String titulo;

    private String descripcion;

    private String ubicacion;

    private String fecha;

    public Evento(int id, String titulo, String descripcion, String ubicacion, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
    }

    public Evento(String titulo){
        this.titulo = titulo;
    }

    public Evento() {
    }

    public int getId(){
        return this.id;
    }


    private void setId(int id){
        this.id = id;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "El titulo del evento es: " + titulo + '\'' +  ", la descripcion del evento es ='" + descripcion + '\'' +
                ", la ubicacion del evento es ='" + ubicacion + '\'' +
                ", la fecha del evento es: ='" + fecha + '\''
                ;
    }

    @Override
    public Evento create(dtoEvento student2) throws SQLException {
        Connection cnn = this.getConexion();
        if(cnn != null) {
            String sql = "INSERT INTO evento(titulo, descripcion, fecha, ubicacion) VALUES('"+student2.getTitulo()+"', '"+student2.getDescripcion()+"','"+student2.getFecha()+"','"+student2.getUbicacion()+"')";
            this.titulo = student2.getTitulo();
            this.descripcion = student2.getDescripcion();
            this.fecha = student2.getFecha();
            this.ubicacion = student2.getUbicacion();
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                this.id = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                cnn.close();
            }
            return this;
        }
        return null;
    }

    @Override
    public ArrayList<Evento> all() {
        Connection cnn = this.getConexion();
        ArrayList<Evento> eventos = new ArrayList<>();

        if (cnn != null) {
            String sql = "SELECT id,titulo, descripcion, fecha, ubicacion FROM evento";
            try {
                PreparedStatement stmt = cnn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String descripcion = rs.getString("descripcion");
                    String titulo = rs.getString("titulo");
                    String fecha = rs.getString("fecha");
                    String ubicacion = rs.getString("ubicacion");
                    Evento evento = new Evento(id, titulo, descripcion, fecha, ubicacion);
                    eventos.add(evento);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (cnn != null) {
                        cnn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return eventos;
        }
        return null;
    }



    @Override
    public Evento findById(int studentId) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "SELECT id,titulo,descripcion,fecha,ubicacion FROM evento WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, studentId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String titulo = rs.getString("titulo");
                        String descripcion = rs.getString("descripcion");
                        String fecha = rs.getString("fecha");
                        String ubicacion = rs.getString("ubicacion");
                        return new Evento(id, titulo, descripcion, fecha, ubicacion);
                    } else {
                        return null;
                    }
                }
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
        return null;
    }

    @Override
    public Evento update(Evento evento) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "UPDATE evento SET titulo = ?, descripcion = ?, fecha = ?, ubicacion = ? WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setString(1, evento.getTitulo());
                stmt.setString(2, evento.getDescripcion());
                stmt.setString(3, evento.getFecha());
                stmt.setString(4, evento.getUbicacion());
                stmt.setInt(5, evento.getId());
                stmt.executeUpdate();
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
        return evento;
    }

    @Override
    public void delete(int studentId) throws SQLException {
        Connection cnn = getConexion();

        if (cnn != null) {
            String sql = "DELETE FROM evento WHERE id = ?";
            try (PreparedStatement stmt = cnn.prepareStatement(sql)) {
                stmt.setInt(1, studentId);
                stmt.executeUpdate();
            } finally {
                if (cnn != null) {
                    cnn.close();
                }
            }
        }
    }

}
