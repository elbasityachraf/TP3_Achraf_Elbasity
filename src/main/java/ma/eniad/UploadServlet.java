package ma.eniad;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload") 
@MultipartConfig 
public class UploadServlet extends HttpServlet { 
     
    @Override 
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws IOException, ServletException { 
         
        Part filePart = req.getPart("file"); 
        String fileName = filePart.getSubmittedFileName(); 
         
        String uploadPath = "uploads/" + fileName; 
        filePart.write(uploadPath); 
         
        resp.setContentType("text/html"); 
        resp.getWriter().println("Fichier uploade: " + fileName); 
    } 
} 