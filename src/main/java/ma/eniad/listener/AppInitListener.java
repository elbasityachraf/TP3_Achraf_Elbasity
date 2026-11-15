package ma.eniad.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ma.eniad.model.ProduitDAO;

@WebListener 
public class AppInitListener implements ServletContextListener { 
    @Override 
    public void contextInitialized(ServletContextEvent sce) { 
        ProduitDAO dao = new ProduitDAO();
        
        sce.getServletContext()
        	.setAttribute("produitDAO", dao);
    } 
}