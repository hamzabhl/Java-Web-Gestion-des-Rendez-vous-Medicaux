<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
<%
    if (session == null || !"patient".equals(session.getAttribute("role"))) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Accueil Patient - MediPlateforme</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <style>
            body { background-color: #f4f9ff; }
            .nav-link.active { font-weight: bold; color: #fff !important; background-color: #003d80; border-left: 4px solid #ffc107; }
            .nav-link.sub { padding-left: 32px; font-size: 0.95rem; }
            .nav-link.sub:hover { background-color: #004b99; }
            .content-section { display: none; }
            .content-section.active { display: block; }
            .sidebar { min-height: 100vh; background-color: #0d6efd; color: white; }
            .sidebar .nav-link { color: white; padding: 12px 16px; cursor: pointer; }
            .sidebar .nav-link:hover { background-color: #0b5ed7; }
            .submenu { display: none; }
            .submenu.show { display: block; }
            .form-control { font-size: 0.85rem; padding: 0.25rem 0.4rem; }
            .forgot-link { font-size: 0.875rem; display: inline-block; margin-top: -10px; margin-bottom: 15px; }
            #statsChart { max-width: 500px; margin: 20px auto; background-color: #fff; padding: 20px; border-radius: 8px; }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <!-- Vertical Navbar -->
                <nav class="col-md-3 col-lg-2 d-md-block sidebar py-4">
                    <h4 class="text-center mb-4">MediPlateforme</h4>
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#" onclick="showSection('accueil', event)">Accueil</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" onclick="showSection('rendezvous', event);">Mes Rendez-vous</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" onclick="toggleSubmenu(event)">Mon Profil</a>
                            <ul class="nav flex-column ms-3 submenu">
                                <li class="nav-item">
                                    <a class="nav-link sub" href="#" onclick="showSection('editInfos', event)">Modifier mes infos</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link sub" href="#" onclick="showSection('changePassword', event)">Changer le mot de passe</a>
                                </li>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link sub" href="<%= request.getContextPath()%>/LogoutServlet">Se déconnecter</a>
                        </li>
                    </ul>
                </nav>

                <!-- Main Content -->
                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">
                    <div id="accueil" class="content-section active">
                        <h2>Bonjour, <%= (user != null) ? user.getPrenom() + " " + user.getNom() : ""%></h2>
                        <h3>Bienvenue sur MediPlateforme</h3>
                        <p>Gérez vos rendez-vous, consultez les médecins, mettez à jour votre profil...</p>
                        <div class="input-group mt-4 mb-3 w-75">
                            <input type="text" class="form-control" id="searchInput" placeholder="Rechercher un médecin ou une spécialité..." aria-label="Rechercher" aria-describedby="search-button">
                            <button class="btn btn-outline-primary" type="button" id="search-button" onclick="performSearch()">Rechercher</button>
                        </div>
                        <div id="searchResults"></div>

                        <!--                        <div id="statsChart">
                                                    <canvas id="userStatsCanvas"></canvas>
                                                </div>-->
                    </div>

                    <div id="rendezvous" class="content-section">
                        <h2>Mes Rendez-vous</h2>
                        <div class="table-responsive">
                            <table class="table table-bordered table-striped">
                                <thead class="table-primary">
                                    <tr>
                                        <th>Médecin</th>
                                        <th>Date</th>
                                        <th>Heure</th>
                                    </tr>
                                </thead>
                                <tbody id="rdvTable"></tbody>
                            </table>
                        </div>
                    </div>
                </main>
            </div>
        </div>

        <script>
                    function showSection(id, event) {
                    event.preventDefault();
                            document.querySelectorAll('.content-section').forEach(section => section.classList.remove('active'));
                            const targetSection = document.getElementById(id);
                            if (targetSection) targetSection.classList.add('active');
                            document.querySelectorAll('.nav-link').forEach(link => link.classList.remove('active'));
                            if (!event.target.classList.contains('nav-link') || event.target.classList.contains('sub')) return;
                            event.target.classList.add('active');
                    }

            function toggleSubmenu(event) {
            event.preventDefault();
                    const submenu = event.target.closest('.nav-item').querySelector('.submenu');
                    submenu.classList.toggle('show');
            }

            function performSearch() {
            const query = document.getElementById("searchInput").value.trim();
                    if (query.length === 0) {
            document.getElementById("searchResults").innerHTML = "";
                    return;
            }

            fetch('<%= request.getContextPath()%>/search/SearchServlet?query=' + encodeURIComponent(query))
                    .then(response => {
                    if (!response.ok) throw new Error("Erreur HTTP: " + response.status);
                            return response.text();
                    })
                    .then(html => {
                    document.getElementById("searchResults").innerHTML = html;
                    })
                    .catch(error => {
                    document.getElementById("searchResults").innerHTML = "<p class='text-danger'>Erreur lors de la recherche.</p>";
                            console.error(error);
                    });
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Modal for Disponibilités -->
        <div class="modal fade" id="rdvModal" tabindex="-1" aria-labelledby="rdvModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="rdvModalLabel">Disponibilités du médecin</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fermer"></button>
                    </div>
                    <div class="modal-body" id="modalContent">
                        Chargement des disponibilités...
                    </div>
                </div>
            </div>
        </div>
        <script>
function openPopup(medecinId) {
    const modal = new bootstrap.Modal(document.getElementById('rdvModal'));
    document.getElementById("modalContent").innerHTML = "Chargement...";

    fetch('<%= request.getContextPath() %>/DisponibilitesServlet?medecinId=' + medecinId)
        .then(response => {
            if (!response.ok) throw new Error("Erreur HTTP: " + response.status);
            return response.text();
        })
        .then(html => {
            document.getElementById("modalContent").innerHTML = html;
        })
        .catch(error => {
            document.getElementById("modalContent").innerHTML = "<p class='text-danger'>Erreur lors du chargement.</p>";
        });

    modal.show();
}
</script>
<script>
function reserver(medecinId, patientId, date, heure) {
    fetch('/JW_RdvMedicaux/RdvServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            medecinId: medecinId,
            patientId: patientId,
            date: date,
            heure: heure
        })
    })
    .then(response => response.text())
    .then(data => {
        if (data.trim() === "success") {
            alert("Rendez-vous pris avec succès !");
            location.reload();
        } else {
            alert("Erreur lors de la prise du rendez-vous : " + data);
        }
    })
    .catch(err => {
        console.error(err);
        alert("Erreur réseau !");
    });
}
</script>
    </body>
</html>