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