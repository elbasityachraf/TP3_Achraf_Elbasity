package ma.eniad.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login") 
public class LoginServlet extends HttpServlet { 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
    		throws IOException, ServletException {
    	req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
	
    @Override 
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws IOException, ServletException { 
        String username = req.getParameter("username"); 
        String password = req.getParameter("password");
        
        if(username.equals("admin") && password.equals("password")) {
        	req.getSession().setAttribute("user", username);
        	resp.sendRedirect(req.getContextPath() + "/dashboard");
        }
        else {
        	req.setAttribute("erreur", "Login ou mot de passe incorrect !");
        	req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }


//        if ("admin".equals(username) && "password".equals(password)) { 
//            HttpSession session = req.getSession(); 
//            session.setAttribute("user", username); 
//            session.setMaxInactiveInterval(30 * 60);  // 30 min 
//            resp.sendRedirect("/TP2/dashboard"); 
//        } else { 
//            resp.sendRedirect("/TP2/login?erreur=1"); 
//        } 
    } 
} 
