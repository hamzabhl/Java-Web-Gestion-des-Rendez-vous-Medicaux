package controller.auth;

import dao.MedecinDao;
import dao.PatientDao;
import entities.Medecin;
import entities.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

    private final PatientDao patientDao = new PatientDao();
    private final MedecinDao medecinDao = new MedecinDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String role = request.getParameter("role");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Les mots de passe ne correspondent pas.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        boolean success = false;

        if ("patient".equals(role)) {
            String telephone = request.getParameter("telephone");
            Patient patient = new Patient();
            patient.setNom(nom);
            patient.setPrenom(prenom);
            patient.setEmail(email);
            patient.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            patient.setTelephone(telephone);
            patient.setRole("patient");

            success = patientDao.create(patient);
        } else if ("medecin".equals(role)) {
            String specialite = request.getParameter("specialite");
            Medecin medecin = new Medecin();
            medecin.setNom(nom);
            medecin.setPrenom(prenom);
            medecin.setEmail(email);
            medecin.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            medecin.setSpecialite(specialite);
            medecin.setRole("medecin");

            success = medecinDao.create(medecin);
        }

        if (success) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Une erreur est survenue lors de l'inscription.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}