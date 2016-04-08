$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
    });
});

function cacheMoi() {
	document.getElementById("message_full").removeAttribute("hidden");
	document.getElementById("message_base").setAttribute("hidden", true);
	document.getElementById("area").focus();
}

function montreMoi() {
	document.getElementById("message_base").removeAttribute("hidden");
	document.getElementById("message_full").setAttribute("hidden", true);
}

//ajout d'un interet
$('#formNewTag').submit(function () {
	input = $('#tagInput').val();
	//cas erreur 1 : champ vide
	if (input == "") {
		alert("erreur : veuillez remplir le champ correctement");
		return;
	}
	var valid = true;
	//cas erreur 2 : champ deja existant
	$('#list-interet').children("a").each(function(index) {
		if($(this).text() == "#"+input) {
			alert("vous avez déjà cet intérêt");
			valid = false;
			return;
		}
	});
	if(valid) {
		//Ajax :
//		$.post("AjaxpostloginServlet.java", function() {
//             name:"kevin",
//             pass:"Duckburg"
//        });
//		$.post("ServletInteret", input).done(function(data) {
//             alert("name: " + data);
//        })
		
		var newtag = "<a href=\"#\" class=\"list-group-item\">#"+input+"</a>";
		$('#list-interet').append(newtag);
	}
	//reset field value
	$('#tagInput').val("");
	return false;//dont refresh
});