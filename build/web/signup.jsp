<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inscription - MediPlateforme</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #e6f3ff;
                min-height: fit-content;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            .main-container {
                display: flex;
                justify-content: center;
                align-items: flex-start;
                margin: 2.5rem auto;
                gap: 2rem;
                flex-wrap: wrap;
            }

            .container-box,
            .form-box {
                flex: 1;
                max-width: 450px;
                height: 400px;
                background: #fff;
                border-radius: 12px;
                padding: 3rem;
                box-shadow: 0 4px 12px rgba(0,0,0,0.1);
                display: flex;
                flex-direction: column;
            }

            .container-box {
                justify-content: space-between;
            }

            .form-box {
                justify-content: flex-start;
            }

            .title {
                font-size: 1.5rem;
                font-weight: bold;
                color: #003366;
                text-align: center;
            }

            .subtitle {
                color: #6c757d;
                text-align: center;
                margin-bottom: 1rem;
                font-size: 0.85rem;
            }

            .role-card {
                border: 1px solid #cce5ff;
                border-radius: 10px;
                padding: 0.5rem;
                margin-bottom: 0.7rem;
                background-color: #f8fbff;
                cursor: pointer;
                transition: all 0.3s;
                font-size: 0.75rem;
            }

            .role-card:hover {
                background-color: #e0f0ff;
            }

            .role-card img {
                width: 60px;
                height: 60px;
                border-radius: 50%;
                margin-right: 0.8rem;
            }

            .role-card h5 {
                margin: 0;
                color: #0056b3;
                font-weight: bold;
                font-size: 1rem;
            }

            .role-card p {
                margin: 0;
                color: #6c757d;
                font-size: 0.65rem;
            }

            .footer-link {
                text-align: center;
                font-size: 0.7rem;
            }

            .footer-link a {
                color: #0056b3;
                text-decoration: none;
                font-size: 0.85rem;
            }

            .form-section {
                display: none;
                font-size: 0.75rem;
            }

            .form-section.active {
                display: block;
            }

            .form-control:invalid:required {
                border-color: #dc3545;
            }

            .form-control:invalid:required:focus {
                box-shadow: 0 0 0 0.2rem rgba(220,53,69,.25);
            }
            .d-grid {
                margin: 25px;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg" style="background-color: #007bff;">
            <div class="container-fluid">
                <a class="navbar-brand text-white fw-bold" href="#">healthTime</a>
            </div>
        </nav>
        <div class="main-container">
            <!-- Role Selection -->
            <div class="container-box">
                <div>
                    <div class="title">Inscrivez-vous</div>
                    <div class="subtitle">Veuillez choisir votre rôle<br>Rejoignez notre communauté</div>

                    <!-- Médecin Card -->
                    <div class="role-card d-flex align-items-center" onclick="showForm('medecin')">
                        <img src="img/doctor.png" alt="Soignant">
                        <div>
                            <h5>Soignant</h5>
                            <p>Offrir des soins de qualité et faciliter la gestion de vos rendez-vous avec vos patients</p>
                        </div>
                    </div>

                    <!-- Patient Card -->
                    <div class="role-card d-flex align-items-center" onclick="showForm('patient')">
                        <img src="img/patient.png" alt="Patient">
                        <div>
                            <h5>Patient</h5>
                            <p>Accéder facilement à vos rendez-vous et bénéficier d’un suivi médical personnalisé</p>
                        </div>
                    </div>
                    <div class="footer-link mt-3">
                        Vous avez un compte ? <a href="login.jsp">s'authentifier</a><br>
                        <a href="index.html">Acceuil</a>
                    </div>
                </div>


            </div>

            <!-- Signup Forms -->
            <div class="form-box">
                <!-- Form Patient -->
                <form id="formPatient" class="form-section active" action="SignupServlet" method="post">
                    <h5 class="text-primary mb-3 text-center">Inscription Patient</h5>
                    <input type="hidden" name="role" value="patient">
                    <div class="row mb-2">
                        <div class="col-md-6">
                            <label class="form-label">Nom</label>
                            <input type="text" class="form-control" name="nom" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Prénom</label>
                            <input type="text" class="form-control" name="prenom" required>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="col-md-6">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Téléphone</label>
                            <input type="text" class="form-control" name="telephone" required>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="col-md-6">
                            <label class="form-label">Mot de passe</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Confirmation de mot de passe</label>
                            <input type="password" class="form-control" name="confirmPassword" required>
                        </div>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">S'inscrire</button>
                    </div>
                </form>

                <!-- Form Medecin -->
                <form id="formMedecin" class="form-section" action="SignupServlet" method="post">
                    <h5 class="text-primary mb-3 text-center">Inscription Médecin</h5>
                    <input type="hidden" name="role" value="medecin">
                    <div class="row mb-2">
                        <div class="col-md-6">
                            <label class="form-label">Nom</label>
                            <input type="text" class="form-control" name="nom" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Prénom</label>
                            <input type="text" class="form-control" name="prenom" required>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="col-md-6">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Spécialité</label>
                            <input type="text" class="form-control" name="specialite" required>
                        </div>
                    </div>
                    <div class="row mb-2">
                        <div class="col-md-6">
                            <label class="form-label">Mot de passe</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Confirmation de mot de passe</label>
                            <input type="password" class="form-control" name="confirmPassword" required>
                        </div>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">S'inscrire</button>
                    </div>
                </form>
            </div>
        </div>

        <footer class="text-white text-center py-3" style="background-color: #007bff;">
            &copy; 2025 healthTime. Tous droits réservés.
        </footer>


        <script>
            function showForm(type) {
                document.getElementById("formPatient").classList.remove("active");
                document.getElementById("formMedecin").classList.remove("active");

                if (type === 'medecin') {
                    document.getElementById("formMedecin").classList.add("active");
                } else {
                    document.getElementById("formPatient").classList.add("active");
                }
            }
        </script>

    </body>
</html>
