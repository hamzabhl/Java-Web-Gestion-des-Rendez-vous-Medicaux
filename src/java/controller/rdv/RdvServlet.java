package controller.rdv;

import dao.MedecinDao;
import dao.PatientDao;
import dao.RdvDao;
import entities.Medecin;
import entities.Patient;
import entities.Rdv;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/RdvServlet")
public class RdvServlet extends HttpServlet {

    private final RdvDao rdvDao = new RdvDao();
    private final MedecinDao medecinDao = new MedecinDao();
    private final PatientDao patientDao = new PatientDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            String json = jsonBuilder.toString();
            JsonObject obj = Json.createReader(new StringReader(json)).readObject();

            int medecinId = obj.getInt("medecinId");
            int patientId = obj.getInt("patientId");
            String dateStr = obj.getString("date");
            String heureStr = obj.getString("heure");

            Date date = Date.valueOf(dateStr);
            Time heure = Time.valueOf(heureStr);

            Medecin medecin = medecinDao.findById(medecinId);
            Patient patient = patientDao.findById(patientId);

            if (medecin == null || patient == null) {
                out.print("invalid_user");
                return;
            }

            Rdv rdv = new Rdv(date, heure, medecin, patient, "en_attente");
            rdvDao.save(rdv);
            out.print("success");

        } catch (Exception e) {
            e.printStackTrace();
            out.print("error");
        }
    }
} 