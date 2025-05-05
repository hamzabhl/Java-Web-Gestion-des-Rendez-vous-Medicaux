// ===== FILE: UpdateAppointmentStatusServlet.java =====
package controller.rdv;

import dao.RdvDao;
import entities.Rdv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateAppointmentStatusServlet")
public class UpdateAppointmentStatusServlet extends HttpServlet {

    private final RdvDao rdvDao = new RdvDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int rdvId = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action"); // accept or refuse

        Rdv rdv = rdvDao.findById(rdvId);
        if (rdv != null) {
            // You may need to add a 'status' field to Rdv entity
            if ("accept".equalsIgnoreCase(action)) {
                // Set status to accepted
            } else if ("refuse".equalsIgnoreCase(action)) {
                // Set status to refused
            }
            rdvDao.update(rdv);
            response.getWriter().write("updated");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}