package com.emergentes.te_cookies;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CookieServlet1", urlPatterns = {"/CookieServlet1"})
public class CookieServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = null;
        boolean nuevaVisita = true;
        String cs = "0";
        // obtener el arreglo de cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ((c.getName().equals("visitante"))) {
                    String aux = c.getValue();
                    int aux1 = Integer.parseInt(aux);
                    aux1 = aux1 + 1;
                    cs = Integer.toString(aux1);
                    response.setContentType("TEXT/HTML;CHARSET=utf-8");
                    PrintWriter out = response.getWriter();
                    out.print("<h1> Visitas : " + aux1 + "</h1>");
                    c.setValue(cs);
                    c.setMaxAge(15);
                    response.addCookie(c);
                    nuevaVisita = false;
                    break;
                }
            }
        }
        if (nuevaVisita) {
            Cookie ok = new Cookie("visitante", "0");
            ok.setMaxAge(15);
            ok.setComment("Control de nuevos visitantes");
            response.addCookie(ok);
            mensaje = "Gracias por visitar nuestra pagina";
        } else {
            mensaje = "Estamos agradecidos por tenerlo NUEVAMENTE";
        }
        response.setContentType("TEXT/HTML;CHARSET=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<h1>" + mensaje + "</h1>");
        out.print("<a href='index.jsp'>ir al inicio</a>");

    }

}
