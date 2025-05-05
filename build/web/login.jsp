<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Connexion - MediLab</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- SweetAlert2 -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            body {
                background: linear-gradient(135deg, #007bff 0%, #3399ff 100%);
                min-height: 100vh;
            }

            .card {
                border: none;
                border-radius: 15px;
            }

            .form-label {
                color: #004080;
            }

            .btn-primary {
                background-color: #0069d9;
                border-color: #0062cc;
            }

            .btn-primary:hover {
                background-color: #0056b3;
            }

            .navbar {
                background-color: #ffffffcc;
            }

            footer {
                background-color: #ffffffcc;
            }
        </style>
    </head>
    <body>

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg shadow-sm">
            <div class="container">
                <a class="navbar-brand text-primary fw-bold" href="index.jsp">MediLab</a>
            </div>
        </nav>

        <!-- Login Form Section -->
        <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
            <div class="col-md-6 col-lg-5">
                <div class="card shadow-lg p-4">
                    <h3 class="text-center text-primary mb-4">Connexion</h3>
<!--                    <form action="LoginServlet" method="post">-->
                    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">Adresse e-mail</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="exemple@domaine.com" required>
                        </div>

                        <div class="mb-1">
                            <label for="password" class="form-label">Mot de passe</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
                        </div>

                        <!-- Mot de passe oublié -->
                        <div class="mb-3 text-end text-center">
                            <a href="motDePasseOublie.jsp" class="text-decoration-none text-primary small">Mot de passe oublié ?</a>
                        </div>

                        <div class="d-grid mb-3">
                            <button type="submit" class="btn btn-primary">Se connecter</button>
                        </div>
                    </form>


                    <p class="text-center mt-3">
                        Pas encore de compte ? <a href="signup.jsp" class="text-decoration-none text-primary">S'inscrire</a>
                    </p>
                    <!-- Retour à l'accueil -->
                    <div class="text-center">
                        <a href="index.html" class="text-decoration-none text-primary">Retour à l'accueil</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- SweetAlert2 error handler -->
        <% if (request.getAttribute("error") != null) {%>
        <script>
            Swal.fire({
                icon: 'error',
                title: 'Erreur de connexion',
                text: '<%= request.getAttribute("error")%>',
                confirmButtonColor: '#0069d9'
            });
        </script>
        <% }%>

        <!-- Footer -->
        <footer class="text-center py-3">
            <p class="mb-0 text-primary">&copy; 2025 MediLab. Tous droits réservés.</p>
        </footer>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>