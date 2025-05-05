package org.apache.jsp.Patient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import entities.User;

public final class Patient_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');

    if (session == null || !"patient".equals(session.getAttribute("role"))) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    User user = (User) session.getAttribute("user");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"fr\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Accueil Patient - MediPlateforme</title>\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n");
      out.write("    <style>\n");
      out.write("        body { background-color: #f4f9ff; }\n");
      out.write("        .nav-link.active { font-weight: bold; color: #fff !important; background-color: #003d80; border-left: 4px solid #ffc107; }\n");
      out.write("        .nav-link.sub { padding-left: 32px; font-size: 0.95rem; }\n");
      out.write("        .nav-link.sub:hover { background-color: #004b99; }\n");
      out.write("        .content-section { display: none; }\n");
      out.write("        .content-section.active { display: block; }\n");
      out.write("        .sidebar { min-height: 100vh; background-color: #0d6efd; color: white; }\n");
      out.write("        .sidebar .nav-link { color: white; padding: 12px 16px; cursor: pointer; }\n");
      out.write("        .sidebar .nav-link:hover { background-color: #0b5ed7; }\n");
      out.write("        .submenu { display: none; }\n");
      out.write("        .submenu.show { display: block; }\n");
      out.write("        .form-control { font-size: 0.85rem; padding: 0.25rem 0.4rem; }\n");
      out.write("        .forgot-link { font-size: 0.875rem; display: inline-block; margin-top: -10px; margin-bottom: 15px; }\n");
      out.write("        #statsChart { max-width: 500px; margin: 20px auto; background-color: #fff; padding: 20px; border-radius: 8px; }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container-fluid\">\n");
      out.write("    <div class=\"row\">\n");
      out.write("        <!-- Vertical Navbar -->\n");
      out.write("        <nav class=\"col-md-3 col-lg-2 d-md-block sidebar py-4\">\n");
      out.write("            <h4 class=\"text-center mb-4\">MediPlateforme</h4>\n");
      out.write("            <ul class=\"nav flex-column\">\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link active\" href=\"#\" onclick=\"showSection('accueil', event)\">Accueil</a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\" href=\"#\" onclick=\"showSection('rendezvous', event);\">Mes Rendez-vous</a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link\" onclick=\"toggleSubmenu(event)\">Mon Profil</a>\n");
      out.write("                    <ul class=\"nav flex-column ms-3 submenu\">\n");
      out.write("                        <li class=\"nav-item\">\n");
      out.write("                            <a class=\"nav-link sub\" href=\"#\" onclick=\"showSection('editInfos', event)\">Modifier mes infos</a>\n");
      out.write("                        </li>\n");
      out.write("                        <li class=\"nav-item\">\n");
      out.write("                            <a class=\"nav-link sub\" href=\"#\" onclick=\"showSection('changePassword', event)\">Changer le mot de passe</a>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link sub\" href=\"");
      out.print( request.getContextPath());
      out.write("/LogoutServlet\">Se déconnecter</a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <!-- Main Content -->\n");
      out.write("        <main class=\"col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4\">\n");
      out.write("            <div id=\"accueil\" class=\"content-section active\">\n");
      out.write("                <h2>Bonjour, ");
      out.print( (user != null) ? user.getPrenom() + " " + user.getNom() : "" );
      out.write("</h2>\n");
      out.write("                <h3>Bienvenue sur MediPlateforme</h3>\n");
      out.write("                <p>Gérez vos rendez-vous, consultez les médecins, mettez à jour votre profil...</p>\n");
      out.write("                <div class=\"input-group mt-4 mb-3 w-75\">\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"searchInput\" placeholder=\"Rechercher un médecin ou une spécialité...\" aria-label=\"Rechercher\" aria-describedby=\"search-button\">\n");
      out.write("                    <button class=\"btn btn-outline-primary\" type=\"button\" id=\"search-button\" onclick=\"performSearch()\">Rechercher</button>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"searchResults\"></div>\n");
      out.write("                <div id=\"statsChart\">\n");
      out.write("                    <canvas id=\"userStatsCanvas\"></canvas>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"rendezvous\" class=\"content-section\">\n");
      out.write("                <h2>Mes Rendez-vous</h2>\n");
      out.write("                <div class=\"table-responsive\">\n");
      out.write("                    <table class=\"table table-bordered table-striped\">\n");
      out.write("                        <thead class=\"table-primary\">\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Médecin</th>\n");
      out.write("                                <th>Date</th>\n");
      out.write("                                <th>Heure</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody id=\"rdvTable\"></tbody>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </main>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("    function showSection(id, event) {\n");
      out.write("        if (event) event.preventDefault();\n");
      out.write("        const sections = document.querySelectorAll('.content-section');\n");
      out.write("        const links = document.querySelectorAll('.nav-link');\n");
      out.write("        sections.forEach(sec => sec.classList.remove('active'));\n");
      out.write("        links.forEach(link => link.classList.remove('active'));\n");
      out.write("        document.getElementById(id).classList.add('active');\n");
      out.write("        event.target.classList.add('active');\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function toggleSubmenu(event) {\n");
      out.write("        event.preventDefault();\n");
      out.write("        const submenu = event.target.nextElementSibling;\n");
      out.write("        submenu.classList.toggle('show');\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function performSearch() {\n");
      out.write("        const query = document.getElementById(\"searchInput\").value.trim();\n");
      out.write("        if (query.length === 0) {\n");
      out.write("            document.getElementById(\"searchResults\").innerHTML = \"\";\n");
      out.write("            return;\n");
      out.write("        }\n");
      out.write("        fetch('");
      out.print( request.getContextPath() );
      out.write("/search/SearchServlet?query=' + encodeURIComponent(query))\n");
      out.write("            .then(response => {\n");
      out.write("                if (!response.ok) throw new Error(\"Erreur HTTP: \" + response.status);\n");
      out.write("                return response.json();\n");
      out.write("            })\n");
      out.write("            .then(data => {\n");
      out.write("                const resultsDiv = document.getElementById(\"searchResults\");\n");
      out.write("                resultsDiv.innerHTML = \"\";\n");
      out.write("                if (data.length === 0) {\n");
      out.write("                    resultsDiv.innerHTML = \"<p class='text-muted'>Aucun résultat trouvé.</p>\";\n");
      out.write("                } else {\n");
      out.write("                    const list = document.createElement(\"ul\");\n");
      out.write("                    list.className = \"list-group mt-2\";\n");
      out.write("                    data.forEach(item => {\n");
      out.write("                        const li = document.createElement(\"li\");\n");
      out.write("                        li.className = \"list-group-item\";\n");
      out.write("                        li.innerHTML = `<strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.nom}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</strong> - ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.specialite || item.email}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("`;\n");
      out.write("                        list.appendChild(li);\n");
      out.write("                    });\n");
      out.write("                    resultsDiv.appendChild(list);\n");
      out.write("                }\n");
      out.write("            })\n");
      out.write("            .catch(error => {\n");
      out.write("                document.getElementById(\"searchResults\").innerHTML = \"<p class='text-danger'>Erreur lors de la recherche.</p>\";\n");
      out.write("                console.error(error);\n");
      out.write("            });\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    document.addEventListener(\"DOMContentLoaded\", function () {\n");
      out.write("        const ctx = document.getElementById(\"userStatsCanvas\").getContext(\"2d\");\n");
      out.write("        fetch('");
      out.print( request.getContextPath() );
      out.write("/UserStatsServlet')\n");
      out.write("            .then(res => res.json())\n");
      out.write("            .then(data => {\n");
      out.write("                new Chart(ctx, {\n");
      out.write("                    type: \"doughnut\",\n");
      out.write("                    data: {\n");
      out.write("                        labels: [\"Médecins\", \"Patients\"],\n");
      out.write("                        datasets: [{\n");
      out.write("                            label: \"Utilisateurs\",\n");
      out.write("                            data: [data.doctors, data.patients],\n");
      out.write("                            backgroundColor: [\"#36A2EB\", \"#FF6384\"],\n");
      out.write("                            borderWidth: 1\n");
      out.write("                        }]\n");
      out.write("                    },\n");
      out.write("                    options: {\n");
      out.write("                        responsive: true,\n");
      out.write("                        plugins: {\n");
      out.write("                            legend: {\n");
      out.write("                                position: 'bottom'\n");
      out.write("                            }\n");
      out.write("                        }\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("    });\n");
      out.write("</script>\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
