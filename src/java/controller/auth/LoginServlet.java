/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

/**
 *
 * @author hamza
 */
import static com.sun.faces.facelets.util.Path.context;
import dao.UserDao;
import entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {    

    private final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDao.findByEmail(email);

        if (user != null && userDao.checkPassword(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            switch (user.getRole()) {
                case "patient":
                    response.sendRedirect("Patient/Patient.jsp");
                    break;
                case "medecin":
                    response.sendRedirect("Medecin/Medecin.jsp");
                    break;
                case "admin":
                    response.sendRedirect("Admin/Admin.jsp");
                    break;
                default:
                    session.invalidate();
                    response.sendRedirect("login.jsp");
            }
        } else {
            request.setAttribute("error", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}