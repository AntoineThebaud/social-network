<%
	if (session.getAttribute("Valide") == null){
		response.sendRedirect("/social_network");
	}
%>
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
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Rechercher">
						</div>
						<button type="submit" class="btn btn-primary">Envoyer</button>
					</form>
					<li><a href="#"><img src="images/minions.jpg" alt="Moi" class="image_post size32">&nbsp;&nbsp;Mon profil</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Options <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Paramètres</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Se deconnecter</a></li>
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
		 	<!-- Colonne de gauche affichant le profile et les tandences -->
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
						<h3>Maurice D.</h3>
						<p>Une description, un slogan !</p>
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
			 	<!-- Zone de text -->
				<div class="thumbnail border shadow padding_top">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#message">Message</a></li>
						<li><a href="#image">Image</a></li>
						<li><a href="#video">Vidéo</a></li>
					</ul>
					<div class="tab-content">
					  <!-- Tab message -->
					  <div id="message" class="tab-pane fade in active">
					  	<div id="message_base">
						    <br/>
							<form class="form-horizontal" role="form">
							  <div class="form-group">
							    <label class="control-label col-sm-2" for="email"><img src="images/minions.jpg" alt="Moi" class="image_post size32"></label>
							    <div class="col-sm-10">
							      <input onfocus="cacheMoi()" type="text" class="form-control" id="text" placeholder="Quoi de neuf ?">
							    </div>
							  </div>
							</form>
						</div>
						<div id="message_full" hidden>
							<br/>
						  	<form class="form-horizontal" role="form">
						  <div class="form-group">
						    <label class="control-label col-sm-2" ><img src="images/minions.jpg" alt="Moi" class="image_post size32"></label>
						    <div class="col-sm-10">
						      <textarea onblur="montreMoi()" class="form-control vertical-resize" rows="4" id="area" placeholder="Quoi de neuf ?"></textarea>
						    </div>
						  </div>
						  <div class="form-group">     
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-primary">Publie !</button>
						    </div>
						  </div>
						</form>
						</div>
					  </div>
					  <!-- fin Tab message -->
					  <!-- Tab image -->
					  <div id="image" class="tab-pane fade">
					  <br/>
						<form class="form-horizontal" role="form">
						  <div class="form-group">
						    <label class="control-label col-sm-2" ><img src="images/minions.jpg" alt="Moi" class="image_post size32"></label>
						    <div class="col-sm-10">
						      <textarea  onfocus="cacheMoi()" class="form-control vertical-resize" rows="4" id="comment" placeholder="Quoi de neuf ?"></textarea>
						    </div>
						  </div>
						  <div class="form-group">     
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-primary">Publie !</button>
						    </div>
						  </div>
						</form>	
					  </div>
					   <!-- Fin Tab image -->
					   <!-- Tab video -->
					  <div id="video" class="tab-pane fade">
				  		<h3>Menu 2</h3>
				    	<p>Some content in menu 2.</p>
					  </div>
					   <!-- Fin Tab video -->
					</div>
				</div>
				<!-- Fin zone de text -->
				<!-- Debut zone d'affichage du flux -->
				
				<!-- Model 2 -->
				<div class="thumbnail border shadow padding_top">
					<div class="row">
						<div class="col-md-3">
							<img src="images/minions.jpg" alt="Avatar du post" class="img-thumbnail height-105">
						</div>
						<div class="col-md-9">
							<p class="float-left">Maurice D.</p>
							<p class="float-right">Il y a 10 min</p>
						</div>
						<div class=" col-md-9">
							<span>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mollis justo odio, at vestibulum arcu cursus sed. Nam id lectus justo. Mauris dapibus, ex ut scelerisque. </span>
						</div>
					</div>
				</div>
				<div class="thumbnail border shadow padding_top">
					<div class="row">
						<div class="col-md-3">
							<img src="images/minions.jpg" alt="Avatar du post" class="img-thumbnail height-105">
						</div>
						<div class="col-md-9">
							<p class="float-left">Maurice D.</p>
							<p class="float-right">Il y a 10 min</p>
						</div>
						<div class=" col-md-9">
							<span>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mollis justo odio, at vestibulum arcu cursus sed. Nam id lectus justo. Mauris dapibus, ex ut scelerisque. </span>
						</div>
					</div>
				</div>
				<!-- Fin Model 2 -->
			</div>
			<div class="col-md-3">
				<h1>Colonne 3</h1>
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