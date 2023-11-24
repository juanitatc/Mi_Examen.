package com.example.mi_examen.Servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;



import com.example.mi_examen.dto.dtoEvento;
import com.example.mi_examen.Controller.CtrEvento;


@WebServlet(name = "helloServlet", value = "/Evento")
public class EventoServlet extends HelloServlet {
    private String message;

    private GsonBuilder gsonBuilder;

    private Gson gson;

    private ArrayList<dtoEvento> eventos;

    CtrEvento ctr = new CtrEvento();

    public void init() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        eventos = new ArrayList<>();

        for (int i = 0; i < eventos.size(); i++)
        {
            System.out.println(eventos.get(i));
        }
        message = "Buenas buenas a los eventos";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        String eventoIdParam = req.getParameter("id");

        if (eventoIdParam != null && !eventoIdParam.isEmpty()) {
            int bookId = Integer.parseInt(eventoIdParam);
            dtoEvento book = ctr.getEventoById(bookId);
            out.print(gson.toJson(book));
        } else {
            ArrayList<dtoEvento> eventos = ctr.getAllEventos();
            out.print(gson.toJson(eventos));
        }
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(req);
        dtoEvento std = new dtoEvento(
                body.get("titulo").getAsString(),
                body.get("descripcion").getAsString(),
                body.get("fecha").getAsString(),
                body.get("ubicacion").getAsString()
        );

        dtoEvento newEvento = ctr.addEvento(std);

        out.print(gson.toJson(newEvento));
        out.flush();


    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject body = gson.fromJson(stringBuilder.toString(), JsonObject.class);
        int eventoId = body.get("EventoId").getAsInt();

        dtoEvento updateEvento = new dtoEvento(
                body.get("titulo").getAsString(),
                body.get("descripcion").getAsString(),
                body.get("fecha").getAsString(),
                body.get("ubicacion").getAsString()
        );

        dtoEvento result = ctr.updateEvento(eventoId, updateEvento);

        out.print(gson.toJson(result));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");

        int eventoId = Integer.parseInt(req.getParameter("eventoId"));

        ctr.deleteEvento(eventoId);

        out.print(gson.toJson("evento eliminado"));
        out.flush();
    }


}