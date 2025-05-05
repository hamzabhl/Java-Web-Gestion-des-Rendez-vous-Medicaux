// ✅ UPDATED SearchServlet.java with Bootstrap grid layout (3 cards per row and rendez-vous button)
package controller.search;

import dao.MedecinDao;
import entities.Medecin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search/SearchServlet")
public class SearchServlet extends HttpServlet {

    private final MedecinDao medecinDao = new MedecinDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String query = request.getParameter("query");
        PrintWriter out = response.getWriter();

        if (query == null || query.trim().isEmpty()) {
            out.println("<p class='text-warning'>Veuillez entrer une spécialité ou un nom.</p>");
            return;
        }

        try {
            List<Medecin> result = medecinDao.findAll();
            boolean found = false;

            out.println("<div class='row'>"); // Start grid row

            for (Medecin m : result) {
                String nom = m.getNom() != null ? m.getNom().toLowerCase() : "";
                String specialite = m.getSpecialite() != null ? m.getSpecialite().toLowerCase() : "";

                if (nom.contains(query.toLowerCase()) || specialite.contains(query.toLowerCase())) {
                    found = true;
                    out.println(
                        "<div class='col-md-4 mb-4'>" +
                            "<div class='card h-100 shadow-sm'>" +
                                "<div class='card-body text-center'>" +
                                    "<i class='bi bi-person-circle' style='font-size: 3rem; color: #0d6efd;'></i>" +
                                    "<h5 class='card-title mt-2'>Dr. <strong>" + m.getPrenom() + " " + m.getNom() + "</strong></h5>" +
                                    "<p class='card-text'><strong>Email :</strong> " + m.getEmail() + "</p>" +
                                    "<p class='card-text'><small class='text-muted'><strong>Spécialité :</strong> " + m.getSpecialite() + "</small></p>" +
                                    "<button class='btn btn-outline-primary mt-2' onclick='openPopup(" + m.getId() + ")'>Prendre un rendez-vous</button>" +
                                "</div>" +
                            "</div>" +
                        "</div>"
                    );
                }
            }

            out.println("</div>"); // End grid row

            if (!found) {
                out.println("<p class='text-muted'>Aucun médecin trouvé pour : " + query + "</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p class='text-danger'>Erreur lors de la recherche.</p>");
        }
    }
}