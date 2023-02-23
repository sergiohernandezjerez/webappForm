package org.thefundidors.apiservlet.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/registro")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");
        String idioma = req.getParameter("idioma");
        boolean habilitar = req.getParameter("habilitar") != null &&
                req.getParameter("habilitar").equals("on");
        String secreto = req.getParameter("secreto");

        List<String> errores = new ArrayList<String>();

        if(username == null || username.isBlank()){
            errores.add("El username es requerido");
        }
        if(password == null || password.isBlank()){
            errores.add("El password no puede estar vacío");
        }

        if(email == null || !email.contains("@")){
            errores.add("El email es requerido y debe tener un formato correcto");
        }

        if(pais == null || pais.isBlank()){
            errores.add("El pais es requerido");
        }

        if(lenguajes == null || lenguajes.length == 0){
            errores.add("El lenguaje es requerido");
        }

        if(roles == null || roles.length == 0){
            errores.add("El rol es requerido");
        }

        if(idioma == null){
            errores.add("Debe seleccionar un idioma");
        }

        try(PrintWriter out = resp.getWriter()){
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <meta charset=\"UTF-8\">");
        out.println("        <title>Resultado form</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("    <h1>Resultado form</h1>");
        out.println("    <ul>");
        if(errores.isEmpty()){
            out.println("       <li>Username: " + username + "</li>");
            out.println("       <li>Password: " + password + "</li>");
            out.println("       <li>Email: " + email + "</li>");
            out.println("       <li>Pais: " + pais + "</li>");
            out.println("       <li>Lenguajes: </li>");
            out.println("           <ul>");
            for (String lenguaje: lenguajes
            ) {
                out.println("               <li>" + lenguaje +"</li>");
            }
            out.println("           </ul>");
            out.println("       <li>Roles: </li>");
            out.println("           <ul>");
            for (String rol: roles
            ) {
                out.println("               <li>" + rol +"</li>");
            }
            out.println("           </ul>");
            out.println("       <li>Idioma: " + idioma + "</li>");
            out.println("       <li>Habilitar: " + habilitar + "</li>");
            out.println("       <li>Secreto: " + secreto + "</li>");
        }else{
            errores.forEach(error -> {
                out.println("<li>Error: " + error + "</li>");
            });
            out.println("<p><a href=\"/webapp-form/index.html\">Volver</a></p>");
        }

        out.println("    </ul>");
        out.println("    </body>");
        out.println("</html>");
        }
    }
}
