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

		$('html, body').animate({
			scrollTop: $("#sTop").offset().top
		},
			500);

	}

	function hideData() {
		let data = document.getElementById("data");
		data.style.display = "none";
		document.getElementById("name").value = ""
		document.getElementById("email").value = ""
		document.getElementById("petName").value = ""
		document.getElementById("breed").value = ""
		document.getElementById("tel").value = ""
		document.getElementById("comment").value = ""
	}

	function showData() {
		let data = document.getElementById("data");
		data.style.display = "block";
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
		showTable:showTable
	}
})();