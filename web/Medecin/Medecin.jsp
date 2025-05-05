<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entities.User" %>
<%
    if (session == null || !"medecin".equals(session.getAttribute("role"))) {
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
    <title>Accueil Médecin - MediPlateforme</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
                    <a class="nav-link" href="#" onclick="showSection('rendezvous', event); loadAppointments();">Mes Rendez-vous</a>
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
                <h2>Bonjour, <%= (user != null) ? user.getPrenom() + " " + user.getNom() : "" %></h2>
                <h3>Bienvenue sur MediPlateforme</h3>
                <p>Gérez vos rendez-vous, mettez à jour votre profil...</p>
                <div class="input-group mt-4 mb-3 w-75">
                    <input type="text" class="form-control" placeholder="Rechercher un médecin ou une spécialité..." aria-label="Rechercher" aria-describedby="search-button">
                    <button class="btn btn-outline-primary" type="button" id="search-button">Rechercher</button>
                </div>
            </div>

            <div id="rendezvous" class="content-section">
                <h2>Mes Rendez-vous En Attente</h2>
                <div class="table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead class="table-primary">
                            <tr>
                                <th>Patient</th>
                                <th>Date</th>
                                <th>Heure</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody id="rdvTable">
                            <!-- Rows loaded via JS -->
                        </tbody>
                    </table>
                </div>
            </div>

            <div id="profil" class="content-section">
                <h2>Mon Profil</h2>
                <p>Choisissez une option dans le menu pour modifier vos informations ou changer votre mot de passe.</p>
            </div>

            <div id="editInfos" class="content-section">
                <h2>Modifier mes informations</h2>
                <form id="profileForm" class="mt-4" method="post" action="UpdatePatientServlet">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="nom" class="form-label">Nom</label>
                            <input type="text" class="form-control" id="nom" name="nom" required>
                        </div>
                        <div class="col-md-6">
                            <label for="prenom" class="form-label">Prénom</label>
                            <input type="text" class="form-control" id="prenom" name="prenom" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="col-md-6">
                            <label for="telephone" class="form-label">Téléphone</label>
                            <input type="text" class="form-control" id="telephone" name="telephone" required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Mettre à jour</button>
                </form>
            </div>

            <div id="changePassword" class="content-section">
                <h2>Changer le mot de passe</h2>
                <form method="post" action="ChangePasswordServlet">
                    <div class="mb-3">
                        <label for="currentPassword" class="form-label">Mot de passe actuel</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                        <a href="forgot-password.jsp" class="forgot-link text-primary">Mot de passe oublié ?</a>
                    </div>
                    <div class="mb-3">
                        <label for="newPassword" class="form-label">Nouveau mot de passe</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirmNewPassword" class="form-label">Confirmer le nouveau mot de passe</label>
                        <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Changer le mot de passe</button>
                </form>
            </div>
        </main>
    </div>
</div>

<script>
    function showSection(id, event) {
        if (event) event.preventDefault();
        const sections = document.querySelectorAll('.content-section');
        const links = document.querySelectorAll('.nav-link');
        sections.forEach(sec => sec.classList.remove('active'));
        links.forEach(link => link.classList.remove('active'));
        document.getElementById(id).classList.add('active');
        event.target.classList.add('active');
    }

    function toggleSubmenu(event) {
        event.preventDefault();
        const submenu = event.target.nextElementSibling;
        submenu.classList.toggle('show');
    }

    function loadAppointments() {
        fetch('<%= request.getContextPath() %>/GetAppointmentsServlet')
            .then(res => res.json())
            .then(data => {
                const table = document.getElementById('rdvTable');
                table.innerHTML = '';
                data.forEach(rdv => {
                    if (rdv.status === 'pending') {
                        const row = document.createElement('tr');
                        row.innerHTML = `
                            <td>${rdv.patient}</td>
                            <td>${rdv.date}</td>
                            <td>${rdv.heure}</td>
                            <td>
                                <button class="btn btn-success btn-sm" onclick="updateRdv(${rdv.id}, 'accept')">Accepter</button>
                                <button class="btn btn-danger btn-sm" onclick="updateRdv(${rdv.id}, 'refuse')">Refuser</button>
                            </td>
                        `;
                        table.appendChild(row);
                    }
                });
            });
    }

    function updateRdv(id, action) {
        fetch('<%= request.getContextPath() %>/UpdateAppointmentStatusServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: `id=${id}&action=${action}`
        })
        .then(() => loadAppointments());
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
