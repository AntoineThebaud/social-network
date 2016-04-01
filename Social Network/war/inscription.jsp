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
				<a class="navbar-brand" href="/">Accueil</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="#">A propos</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form navbar-left" role="connexion" method="post" action="connexion">
						<div class="form-group">
							<input type="email" class="form-control" placeholder="Email" name="mail">
							<input type="password" class="form-control" placeholder="Mot de passe" name="pw">
						</div>
						<button type="submit" class="btn btn-success">Se connecter</button>
					</form>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container">
		<div class="row">
		 	<div class="col-md-6">
		 		<br/>
		 		<br/>
		 		<div class="thumbnail border shadow">
					<img src="images/image_accueil.jpg" alt="Bienvenue" class="">
				</div>
		 	</div>
		 	<div class="col-md-6">
		 		<h2>Inscription</h2>
		 		
		 		
		 		<div class="alert alert-danger" role="alert">Les mots de passe ne sont pas identiques</div>
		 		<% if(request.getAttribute("erreur") != null) {%>
				    <div class="error">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
				<%}%>
				
				
				<form class="form-horizontal" method="post" action="Inscription">
				  <div class="form-group">
				    <div class="col-sm-5">
				      <input type="text" class="form-control" name="prenom" placeholder="Prénom" required>
				    </div>
				    <div class="col-sm-5">
				      <input type="text" class="form-control" name="nom" placeholder="Nom" required>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10">
				      <input type="email" class="form-control" name="mail" placeholder="Email" required>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10">
				      <input type="password" class="form-control" name="mdp" placeholder="Mot de passe" required>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10">
				      <input type="password" class="form-control" name="mdp2" placeholder="Confirmation du mot de passe" required>
				    </div>
				  </div>
				  <div class="form-group">
				  	<div class="col-sm-12">
						<label class="control-label">Date de naissance</label>
					</div>
					<div class="col-sm-2">
						<select class="form-control" name="jour">
							<option>1</option>
							<option>2</option>
							<option>3</option>
							<option>24</option>
						</select>
					</div>
					<div class="col-sm-3">
						<select class="form-control" name="mois">
							<option>01</option>
							<option>02</option>
							<option>03</option>
							<option>04</option>
						</select>
					</div>
					<div class="col-sm-3">
						<select class="form-control" name="annees">
							<option>2000</option>
							<option>1999</option>
							<option>1998</option>
							<option>1997</option>
						</select>
					</div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10">
				      	<div class="radio">
						  <label>
						    <input type="radio" name="radio" id="id_homme" value="Homme" checked>
						    Homme
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <input type="radio" name="radio" id="id_femme" value="Femme">
						    Femme
						  </label>
						</div>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-10">
				      <button type="submit" class="btn btn-success btn-lg btn-block">Inscription</button>
				    </div>
				  </div>
				</form>
		 	</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/monjs.js"></script>
</body>
</html>