package ma.eniad.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import ma.eniad.model.Produit;
import ma.eniad.model.ProduitDAO;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private ProduitDAO dao;

    @Override
    public void init() {

        dao = (ProduitDAO) getServletContext()
                .getAttribute("produitDAO");

        if (dao == null) {
            throw new IllegalStateException(
                "ProduitDAO non initialisé"
            );
        }
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action == null || action.isEmpty()) {

            req.setAttribute("produits", dao.findAll());

            req.getRequestDispatcher(
                "/WEB-INF/views/produits/list.jsp"
            ).forward(req, resp);

        }
        else if ("new".equals(action)) {

            req.setAttribute("produit", new Produit());

            req.getRequestDispatcher(
                "/WEB-INF/views/produits/form.jsp"
            ).forward(req, resp);

        }
        else if ("edit".equals(action)) {

            Long id = parseId(req.getParameter("id"));

            if (id == null) {
                resp.sendError(
                    HttpServletResponse.SC_NOT_FOUND
                );
                return;
            }

            Produit produit = dao.findById(id);

            if (produit == null) {
                resp.sendError(
                    HttpServletResponse.SC_NOT_FOUND
                );
                return;
            }

            req.setAttribute("produit", produit);

            req.getRequestDispatcher(
                "/WEB-INF/views/produits/form.jsp"
            ).forward(req, resp);

        } else {

            resp.sendError(
                HttpServletResponse.SC_NOT_FOUND
            );
        }
    }


    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        // Suppression
        if ("delete".equals(action)) {

            Long id = parseId(req.getParameter("id"));

            if (id == null || dao.findById(id) == null) {
                resp.sendError(
                    HttpServletResponse.SC_NOT_FOUND
                );
                return;
            }

            dao.delete(id);

            HttpSession session = req.getSession();
            session.setAttribute(
                "flash",
                "Produit supprimé avec succès."
            );

            resp.sendRedirect(
                req.getContextPath() + "/produits"
            );

            return;
        }

        String idValue = req.getParameter("id");
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String prixValue = req.getParameter("prix");
        String stockValue = req.getParameter("stock");

        nom = nom == null ? "" : nom.trim();

        description = description == null
                ? "" : description.trim();

        prixValue = prixValue == null
                ? "" : prixValue.trim();

        stockValue = stockValue == null
                ? "" : stockValue.trim();

        Produit produit = new Produit();

        produit.setNom(nom);
        produit.setDescription(description);

        Map<String, String> erreurs = new HashMap<>();

        Long id = null;

        if (idValue != null && !idValue.isBlank()) {

            id = parseId(idValue);

            if (id == null || dao.findById(id) == null) {
                resp.sendError(
                    HttpServletResponse.SC_NOT_FOUND
                );
                return;
            }

            produit.setId(id);
        }

        if (nom.isEmpty()) {

            erreurs.put("nom", "Le nom est obligatoire.");

        } else if (nom.length() < 2 || nom.length() > 50) {

            erreurs.put(
                "nom",
                "Le nom doit contenir entre 2 et 50 caractères."
            );
        }

        if (description.length() > 255) {

            erreurs.put(
                "description",
                "La description ne doit pas dépasser 255 caractères."
            );
        }

        BigDecimal prix = null;

        try {

            prix = new BigDecimal(prixValue);

            if (prix.compareTo(BigDecimal.ZERO) <= 0) {

                erreurs.put(
                    "prix",
                    "Le prix doit être strictement positif."
                );
            }

        } catch (NumberFormatException e) {

            erreurs.put(
                "prix",
                "Veuillez saisir un prix valide."
            );
        }

        int stock = 0;

        if (!stockValue.isEmpty()) {

            try {

                stock = Integer.parseInt(stockValue);

                if (stock < 0) {

                    erreurs.put(
                        "stock",
                        "Le stock doit être supérieur ou égal à zéro."
                    );
                }

            } catch (NumberFormatException e) {

                erreurs.put(
                    "stock",
                    "Le stock doit être un entier valide."
                );
            }
        }

        if (prix != null &&
            prix.compareTo(BigDecimal.ZERO) > 0) {

            produit.setPrix(prix);
        }

        produit.setStock(stock);

        if (!erreurs.isEmpty()) {

            req.setAttribute("erreurs", erreurs);
            req.setAttribute("produit", produit);

            req.getRequestDispatcher(
                "/WEB-INF/views/produits/form.jsp"
            ).forward(req, resp);

            return;
        }

        dao.save(produit);

        HttpSession session = req.getSession();

        if (id == null) {

            session.setAttribute(
                "flash",
                "Produit " + nom + " ajouté avec succès."
            );

        } else {

            session.setAttribute(
                "flash",
                "Produit " + nom + " modifié avec succès."
            );
        }

        resp.sendRedirect(
            req.getContextPath() + "/produits"
        );
    }

    private Long parseId(String value) {

        try {

            if (value == null || value.isBlank()) {
                return null;
            }

            long id = Long.parseLong(value);

            return id > 0 ? id : null;

        } catch (NumberFormatException e) {

            return null;
        }
    }
}