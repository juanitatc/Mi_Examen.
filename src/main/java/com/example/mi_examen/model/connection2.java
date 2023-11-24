package com.example.mi_examen.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection2 {

    private String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    private String database = "eventos";

    // Host
    private String hostname = "localhost";

    // Puerto
    private String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "";

    public connection2(){

    }

    public Connection getConexion(){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
