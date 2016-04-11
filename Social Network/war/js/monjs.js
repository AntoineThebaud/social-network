$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
    });
});


/***********************
 *   Nouvel intérêt
 ***********************/

//Submit :
$('#formNewTag').submit(function () {
	input = $('#tagInput').val();
	//cas erreur 1 : champ vide
	if (input == "") {
		alert("erreur : veuillez remplir le champ correctement");
		return false;
	}
	var valid = true;
	//cas erreur 2 : champ existant
	$('#list-interet').children("a").each(function(index) {
		if($(this).text() == "#"+input) {
			alert("vous avez déjà cet intérêt");
			valid = false;
			return false;
		}
	});
	if(valid) {
		//Ajax :
		$.post("/ajoutInteret", {inputKey:input}).done(function(data) {
			var newtag = "<a href=\"#\" class=\"list-group-item\">#"+input+"</a>";
			$('#list-interet').append(newtag);
		})
	}
	//reset field value
	$('#tagInput').val("");
	return false;//= no refresh
});


/***********************
 * Nouvelle publication
 ***********************/

//affichage de la textaera :
function showFull() {
	document.getElementById("btn_pub").removeAttribute("hidden");
	document.getElementById("pub_aera").style["height"]='140px';
}
function showReduce() {
	if($("#submit_pub").data("mouseDown") != true){
		console.log("HIDE ALL");
	  	document.getElementById("btn_pub").setAttribute("hidden", true);
		document.getElementById("pub_aera").style["height"]='35px';
	}
}
//Submit : ces 3 fonctions sont appellées dans l'ordre
//(astuce pour ne pas que l'event onblur se déclenche avant l'event onclic) :
$("#submit_pub").on("mousedown", function(e){
	console.log("MOUSEDOWN");
    $("#submit_pub").data("mouseDown", true);
});
$("#submit_pub").on("mouseup", function(e){
	console.log("MOUSEUP");
    $("#submit_pub").data("mouseDown", false);
});
$('#formNewPub').submit(function () {
	console.log("new pub submitted");
	//TODO : Ajax pour creation de la nouvelle publication
	// ...
	showReduce();
	$('#pub_aera').val("");
	return false;//= no refresh
});
