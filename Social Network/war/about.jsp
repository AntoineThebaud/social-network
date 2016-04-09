<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Social Network</title>
<link rel="icon" href="favicon.ico" />

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/moncss.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="/affichageProfil"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Accueil</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="/about.jsp">A propos</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form navbar-left" role="search" method="post" action="recherche">
						<div class="form-group">
							<input type="text" class="form-control" name="recherche" placeholder="Rechercher">
						</div>
						<button type="submit" class="btn btn-primary">Envoyer</button>
					</form>
					<li><a href="/profil"><img src="images/minions.jpg" alt="Moi" class="image_post size32">&nbsp;&nbsp;Mon profil</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Options <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Paramètres</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="/deconnexion">Se deconnecter</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container">
		<div class="row">
      <div class="col-md-6">

        <div class="panel panel-default">
          <div class="panel-heading"><h3>Présentation du groupe</h3></div>
          <table class="table">
            <thead>
              <tr>
                <th>#</th>
                <th>Nom</th>
                <th>Prénom</th>
              </tr> </thead>
              <tbody>
                <tr>
                  <th scope="row">1</th>
                  <td>Monnet-Paquet</td>
                  <td>Aurélien</td>
                </tr> <tr>
                <th scope="row">2</th>
                <td>Rakotomanga</td>
                <td>Mary-Andréa</td>
              </tr>
              <tr>
                <th scope="row">3</th>
                <td>Thébaud</td>
                <td>Antoine</td>
              </tr>
            </tbody>
          </table>
        </div>
        <h3>Quelles sont les technologies utlisées ?</h3>
        <ul>
          <li>Html 5</li>
          <li>CSS avec le Framework Bootstrap de Twitter</li>
          <li>JSP</li>
          <li>Servlet</li>
          <li>Javascript</li>
        </ul>
        <ul>
          <li>Google Cloud APP Engine</li>
          <li>Google Datastore</li>
          <li>Objectify / DAO</li>
        </ul>
        <h3>Quelles sont les fonctionnalitées ?</h3>
        <ul>
          <li>Inscription / Connexion / Déconnexion</li>
          <li>Modification du profil</li>
          <li>Recherche d'interêts et d'utilisateurs</li>
          <li>Ajout d'interêts et d'amis</li>
        </ul>
      </div>
    </div>
  </div>
</body>
</html>
