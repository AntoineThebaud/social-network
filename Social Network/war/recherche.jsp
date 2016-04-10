<%
	if (session.getAttribute("Valide") == null){
		response.sendRedirect("/social_network");
	}
%>
	<%@page isELIgnored ="false" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="java.util.List" %>
	<%@page import="social.network.Personne" %>
	<%@page import="social.network.Interet" %>

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
					<li><a href="#">A propos</a></li>
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
		 	<!-- Colonne de gauche affichant le profile et les tendances -->
			<div class="col-md-3">
				<!-- Thumbnail affichant le profil du user -->
				<div class="thumbnail border shadow">
					<div class="full_size">
						<img class=" images_test" src="images/aigle.jpg" alt="Image">
					</div>
					<div>
						<img src="images/minions.jpg" alt="Photo de profile" class="img-circle image_profile">
					</div>
					<div class="caption text-center">
						<h3><%= session.getAttribute("Prenom")%> <%= session.getAttribute("Nom")%></h3>
						<p><%= session.getAttribute("Slogan")%></p>
						<hr>
					</div>
					<div class="row">
						<div class="col-md-offset-3 col-md-2 text-center">
							<p>#Tags</p>
							<p><span class="badge">56</span></p>
						</div>
						<div class="col-md-offset-1 col-md-2  text-center divider-vertical">
							<p>Contacts</p>
							<p><span class="badge">236</span></p>
						</div>
					</div>
				</div>
				<div class="thumbnail border shadow">
					<div class="tendance">
						<h3 align="center">Tendances</h3>
						 <div class="list-group">
							<a href="#" class="list-group-item active">#First item</a>
							<a href="#" class="list-group-item" style="color:#337AB7">#Second item</a>
							<a href="#" class="list-group-item" style="color:#337AB7">#Third item</a>
						</div>
					</div>
				</div>
				<!-- Fin thumbnail profil -->
			</div>
			<!-- Fin colonne de gauche -->
			<!-- Colonne du milieu affichant une zone de text et le flux des autres personness -->
			<div class="col-md-6">
				<h3 align="center">Résultat de la recherche "<%= request.getParameter("recherche") %>"</h3>
				<!-- Debut zone d'affichage du flux -->

				<!-- Model 2 -->
				<%  List<Personne> listPersonne = (List<Personne>)request.getAttribute("resultatPersonne");
					List<Interet> listInteret = (List<Interet>)request.getAttribute("resultatInteret");
					if(listPersonne.size() != 0 || listInteret.size() != 0){ %>
					<c:forEach items="${resultatPersonne}" var="v">
					<div class="thumbnail border shadow padding_top">
						<div class="row">
						<div class="col-md-3">
							<img src="images/minions.jpg" alt="Avatar du post" class="img-thumbnail height-105">
						</div>
						<div class="col-md-9">
							<p class="float-left"><h4><a href="/affichageProfil?id=<c:out value="${v.id}"/>"><c:out value="${v.prenom}"/> <c:out value="${v.nom}"/></a></h4></p>
						</div>
						<div class=" col-md-9">
							<span><c:out value="${v.slogan}"/></span>
						</div>
						</div>
					</div>
					</c:forEach>
					<c:forEach items="${resultatInteret}" var="v">
					<div class="thumbnail border shadow padding_top">
						<div class="row">
						<div class="col-md-3">
							<img src="images/minions.jpg" alt="Avatar du post" class="img-thumbnail height-105">
						</div>
						<div class="col-md-9">
							<p class="float-left"><h4><a href="/affichageProfil"><c:out value="${v.nom}"/></a></h4></p>
						</div>
						<div class=" col-md-9">
							<span>Description ?</span>
						</div>
						</div>
					</div>
					</c:forEach>
				<% }
					else{%>
						<h5 align="center">Aucun résultat.</h5>
				<%}%>
				<!-- Fin Model 2 -->
			</div>
			<div class="col-md-3">
				<!--<h1>Colonne 3</h1>-->
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
