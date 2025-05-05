package controller.rdv;

import dao.RdvDao;
import entities.Rdv;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/GetAppointmentsServlet")
public class GetAppointmentsServlet extends HttpServlet {

    private final RdvDao rdvDao = new RdvDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Rdv> rdvs = rdvDao.findAll();
        JSONArray array = new JSONArray();

        for (Rdv r : rdvs) {
            JSONObject obj = new JSONObject();
            obj.put("title", "RDV: " + r.getPatient().getNom() + " avec Dr. " + r.getMedecin().getNom());
            obj.put("start", r.getDate().toString() + "T" + r.getHeure().toString());
            obj.put("allDay", false);
            array.put(obj);
        }

        response.setContentType("application/json");
        response.getWriter().write(array.toString());
    }
}