package ma.eniad.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard") 
public class DashboardServlet extends HttpServlet { 
    @Override 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws IOException, ServletException { 
//    	  ----- TP2 -----
//        HttpSession session = req.getSession(); 
//        String user = (String) session.getAttribute("user"); 
//         
//        if (user == null) { 
//            resp.sendRedirect("/TP2/login"); 
//            return; 
//        } 
//         
//        resp.setContentType("text/html"); 
//        resp.getWriter().println("<h1>Dashboard de " + user + "</h1>");
    	
    	HttpSession session = req.getSession(false);
    	if (session == null) {
    		resp.sendRedirect(req.getContextPath() + "/login");
    		return;
    	}
    	String user = (String) session.getAttribute("user");
    	req.setAttribute("user", user);
    	req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);
    } 
}