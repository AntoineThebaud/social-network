<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<form class="navbar-form navbar-left" role="connexion" method="post" action="connexion">
					  <% if(request.getAttribute("erreur") != null && request.getAttribute("erreur").equals("Identifiant ou mot de passe incorrect")) {%>
					  	<div class="form-group has-error has-feedback">
					  <%}else {%>
					  	<div class="form-group">
					  <%}%>
					  <% if(request.getAttribute("erreur") != null && request.getAttribute("erreur").equals("Identifiant ou mot de passe incorrect")) {%>
				  		<input type="email" class="form-control" name="mail" value="<%= request.getParameter("mail")%>" required>
				  	  <%}else {%>
				    	<input type="email" class="form-control" name="mail" placeholder="Email" required>
				      <%}%>
						<input type="password" class="form-control" placeholder="Mot de passe" name="pw" required>
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
		 		<% if(request.getAttribute("erreur") != null) {%>
		 		<div class="col-sm-10">
				    <div class="alert alert-danger" role="alert" align="center">
				    	<%=request.getAttribute("erreur")%>
				    </div>
				</div>
				<%}%>
				<form class="form-horizontal" method="post" action="Inscription">
				  <div class="form-group">
				    <div class="col-sm-5">
				    <% if(request.getAttribute("erreur") != null && !request.getAttribute("erreur").equals("Identifiant ou mot de passe incorrect")) {%>
				  		<input type="text" class="form-control" name="prenom" value="<%= request.getParameter("prenom")%>" required>
				  	<%}else {%>
				    	<input type="text" class="form-control" name="prenom" placeholder="Prénom" required>
				    <%}%>
				    </div>
				    <div class="col-sm-5">
				      <% if(request.getAttribute("erreur") != null && !request.getAttribute("erreur").equals("Identifiant ou mot de passe incorrect")) {%>
				  		<input type="text" class="form-control" name="nom" value="<%= request.getParameter("nom")%>" required>
				  	  <%}else {%>
				    	<input type="text" class="form-control" name="nom" placeholder="Nom" required>
				      <%}%>
				    </div>
				  </div>
				  <% if(request.getAttribute("erreur") != null && request.getAttribute("erreur").equals("L'adresse mail existe déjà !")) {%>
				  	<div class="form-group has-error has-feedback">
				  <%}else {%>
				  	<div class="form-group">
				  <%}%>
				    <div class="col-sm-10">
				    <% if(request.getAttribute("erreur") != null && !request.getAttribute("erreur").equals("Identifiant ou mot de passe incorrect")) {%>
				  		<input type="email" class="form-control" name="mail" value="<%= request.getParameter("mail")%>" required>
				  	  <%}else {%>
				    	<input type="email" class="form-control" name="mail" placeholder="Email" required>
				      <%}%>
				    </div>
				  </div>
				  <% if(request.getAttribute("erreur") != null && request.getAttribute("erreur").equals("Les deux mots de passe ne sont pas identiques !")) {%>
				  	<div class="form-group has-error has-feedback">
				  <%}else {%>
				  	<div class="form-group">
				  <%}%>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" name="mdp" placeholder="Mot de passe" required>
				    </div>
				  </div>
				  <% if(request.getAttribute("erreur") != null && request.getAttribute("erreur").equals("Les deux mots de passe ne sont pas identiques !")) {%>
				  	<div class="form-group has-error has-feedback">
				  <%}else {%>
				  	<div class="form-group">
				  <%}%>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" name="mdp2" placeholder="Confirmation du mot de passe" required>
				    </div>
				  </div>
				  <div class="form-group">
				  	<div class="col-sm-12">
						<label class="control-label">Date de naissance</label>
					</div>
					<div class="col-sm-1">
						<select name="jour">
							<c:forEach var="i" begin="1" end="31">
								<option>${i}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-sm-1">
						<select name="mois">
								<c:forEach var="i" begin="1" end="12">
									<option>${i}</option>
								</c:forEach>
						</select>
					</div>
					<div class="col-sm-1">
						<select name="annees">
								<c:forEach var="i" begin="1900" end="2016">
									<option>${i}</option>
								</c:forEach>
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
