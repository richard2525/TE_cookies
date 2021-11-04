package com.emergentes.te_cookies;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = null;
        boolean nuevaVisita = true;
        int contador = 0;
        // obtener el arreglo de cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c: cookies) {
                if ((c.getName().equals("visitante")) && c.getValue().equals("SI")) {
                    nuevaVisita = false;
                    break;
                }
            }
        }
        if (nuevaVisita) {
            Cookie ok = new Cookie("visitante","SI");
            ok.setMaxAge(120);
            ok.setComment("Control de nuevos visitantes");
            response.addCookie(ok);
            mensaje = "Gracias por visitar nuestra pagina";
        }
        else{
             
            contador = contador +1;
            mensaje = "Estamos agradecidos por tenerlo NUEVAMENTE";
        }
        response.setContentType("TEXT/HTML;CHARSET=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<h1>" + mensaje + "</h1>");
        out.print("<h1>Visitas : " + contador +"</h1>");
        out.print("<a href='index.jsp'>ir al inicio</a>");
        
    }
}
