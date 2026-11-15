package ma.eniad;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cookies") 
public class CookieServlet extends HttpServlet { 
     
    @Override 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws IOException { 
        String action = req.getParameter("action"); 
         
        if ("set".equals(action)) { 
            Cookie cookie = new Cookie("theme", "dark"); 
            cookie.setMaxAge(30 * 24 * 60 * 60);  // 30 jours 
            cookie.setPath("/"); 
            resp.addCookie(cookie); 
        } 
         
        // Afficher tous les cookies 
        resp.setContentType("text/html"); 
        Cookie[] cookies = req.getCookies(); 
        if (cookies != null) { 
            for (Cookie c : cookies) { 
                resp.getWriter().println(c.getName() + ": " + c.getValue()); 
            } 
        } 
    } 
} 