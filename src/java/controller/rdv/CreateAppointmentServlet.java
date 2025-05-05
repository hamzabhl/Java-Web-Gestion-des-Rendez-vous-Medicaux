package controller.rdv;

import dao.RdvDao;
import entities.Rdv;
import entities.Medecin;
import entities.Patient;
import entities.User;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CreateAppointmentServlet")
public class CreateAppointmentServlet extends HttpServlet {

    private final RdvDao rdvDao = new RdvDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User user = (User) session.getAttribute("user");
        if (!(user instanceof Patient)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("heure");
        String medecinIdStr = request.getParameter("medecinId");

        if (dateStr == null || timeStr == null || medecinIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Date date = Date.valueOf(dateStr);
        Time heure = Time.valueOf(timeStr);
        int medecinId = Integer.parseInt(medecinIdStr);

        Rdv rdv = new Rdv();
        rdv.setDate(date);
        rdv.setHeure(heure);
        rdv.setPatient((Patient) user);

        Medecin medecin = new Medecin();
        medecin.setId(medecinId);
        rdv.setMedecin(medecin);

        rdvDao.create(rdv);
        response.getWriter().write("OK");
    }
}