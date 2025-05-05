// ✅ DisponibilitesServlet.java - returns available time slots for a given doctor
package controller.rdv;

import dao.RdvDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/DisponibilitesServlet")
public class DisponibilitesServlet extends HttpServlet {

    private final RdvDao rdvDao = new RdvDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int medecinId;
        try {
            medecinId = Integer.parseInt(request.getParameter("medecinId")); // ✅ or "id" if you kept it
            System.out.println(">> [DisponibilitesServlet] medecinId = " + medecinId); // 🖨️ Console log here
        } catch (Exception e) {
            out.println("<p class='text-danger'>ID médecin invalide</p>");
            return;
        }

        LocalDate today = LocalDate.now();
        int daysToShow = 60;
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(17, 0);
        int stepMinutes = 20;

        out.println("<div class='container'>");

        for (int i = 0; i < daysToShow; i++) {
            LocalDate currentDay = today.plusDays(i);
            out.println("<h5 class='mt-4'>" + currentDay.getDayOfWeek().toString().toLowerCase() + " " + currentDay + "</h5>");
            out.println("<div class='d-flex flex-wrap gap-2'>");

            for (LocalTime time = start; time.isBefore(end); time = time.plusMinutes(stepMinutes)) {
                Date sqlDate = Date.valueOf(currentDay);
                Time sqlTime = Time.valueOf(time);
                boolean exists = rdvDao.existsByDateHeure(medecinId, sqlDate, sqlTime);

                if (!exists) {
                    out.println("<button class='btn btn-success btn-sm' onclick=\"reserver('" + currentDay + "','" + time + "')\">" + time + "</button>");
                }
            }

            out.println("</div>");
        }

        out.println("</div>");
    }
}
