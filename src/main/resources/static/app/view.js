var view = (function() {

	function hidePrincipal() {
		let principal = document.getElementById("principal");
		principal.style.display = "none";
	}

	function showPrincipal() {
		let principal = document.getElementById("principal");
		hideData();
		hideServices();
		hideShops();
		hideTable();
		principal.style.display = "block";
		quitarColores();
		$('html, body').animate({
			scrollTop: $("#sTop").offset().top
		},
			500);
		app.disconnect();
	}
	
	function quitarColores() {
		let name = document.getElementById("name");
		let email = document.getElementById("email");
		let petName = document.getElementById("petName");
		let raza = document.getElementById("breed");
		let telefono = document.getElementById("tel");
		let comentario = document.getElementById("comment");
		name.style.borderColor = "";
		email.style.borderColor = "";
		petName.style.borderColor = "";
		raza.style.borderColor = "";
		telefono.style.borderColor = "";
		comentario.style.borderColor = "";
	}

	function hideData() {
		let data = document.getElementById("data");
		let btn = document.getElementById("atras");
		data.style.display = "none";
		btn.style.display = "none";
		document.getElementById("name").value = ""
		document.getElementById("email").value = ""
		document.getElementById("petName").value = ""
		document.getElementById("breed").value = ""
		document.getElementById("tel").value = ""
		document.getElementById("comment").value = ""
	}

	function showData() {
		let data = document.getElementById("data");
		let btn = document.getElementById("atras");
		data.style.display = "block";
		btn.style.display = "block";
	}

	function hideQuery() {
		let query = document.getElementById("query");
		query.style.display = "none";
	}

	function showQuery() {
		let query = document.getElementById("query");
		query.style.display = "block";
	}

	function showServices() {
		let menu = document.getElementById("servicesMenu");
		menu.style.display = "block";
		hidePrincipal();
		showData();
		$('html, body').animate({
			scrollTop: $("#servicesMenu").offset().top
		},
			500);
	}

	function hideServices() {
		let menu = document.getElementById("servicesMenu");
		menu.style.display = "none";
		document.getElementById("fechaServicio").value = ""
		hideData();
	}

	function showShops() {
		let menu = document.getElementById("shopsMenu");
		menu.style.display = "block";
		hidePrincipal();
		$('html, body').animate({
			scrollTop: $("#shopsMenu").offset().top
		},
			500);
		showData();
	}

	function hideShops() {
		let menu = document.getElementById("shopsMenu");
		menu.style.display = "none";
		document.getElementById("fechaShop").value = ""
		hideData();
	}

	function showTable(){
        let data = document.getElementById("tabla");
        data.style.display = "block";
    }

    function hideTable (){
        let data = document.getElementById("tabla");
        data.style.display = "none";
    }

	return {
		showServices: showServices,
		hideServices: hideServices,
		showShops: showShops,
		hideShops: hideShops,
		showQuery: showQuery,
		hideQuery: hideQuery,
		showPrincipal: showPrincipal,
		showTable:showTable,
		quitarColores:quitarColores
	}
})();