package ma.eniad;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/inscription") 
public class InscriptionServlet extends HttpServlet { 
     
    @Override 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws IOException { 
        // Afficher le formulaire 
        resp.setContentType("text/html;charset=UTF-8"); 
        resp.getWriter().println("<!-- Form HTML -->");
//        resp.getWriter().println("Hatim");
    } 
     
    @Override 
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws IOException { 
        req.setCharacterEncoding("UTF-8"); 
         
        String nom = req.getParameter("nom");
        String email = req.getParameter("email"); 
        String ageStr = req.getParameter("age"); 
         
        // Validation 
        if (nom == null || nom.isEmpty()) { 
            resp.sendRedirect("?erreur=nom_vide"); 
            return; 
        } 
         
        if (email == null || !email.contains("@")) { 
            resp.sendRedirect("?erreur=email_invalide"); 
            return; 
        } 
         
        int age = 0; 
        try { 
            age = Integer.parseInt(ageStr); 
            if (age < 18) { 
                resp.sendRedirect("?erreur=age_min"); 
                return; 
            } 
        } catch (NumberFormatException e) { 
            resp.sendRedirect("?erreur=age_invalide"); 
            return; 
        } 
         
        // Créer l'utilisateur (fictif) 
        HttpSession session = req.getSession(); 
        session.setAttribute("utilisateur", nom); 
         
        resp.sendRedirect("/TP2/succes"); 
    } 
} 