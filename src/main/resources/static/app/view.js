var view = (function(){
	
	function hidePrincipal(){
		let principal = document.getElementById("principal");
		principal.style.display = "none";
	}
	
	function showPrincipal(){
		let principal = document.getElementById("principal");
		hideData();
		hideServices();
		hideShops();
		hideQuery();
		principal.style.display = "block";
	}
	
	function hideData(){
		let data = document.getElementById("data");
		data.style.display = "none";
	}
	
	function showData(){
		let data = document.getElementById("data");
		data.style.display = "block";
	}
	
	function hideQuery(){
		let query = document.getElementById("query");
		query.style.display = "none";
	}
	
	function showQuery(){
		let query = document.getElementById("query");
		query.style.display = "block";
	}
	
	function showServices(){
		let menu = document.getElementById("servicesMenu");
		menu.style.display = "block";
		hidePrincipal();
		showData();
	}
	
	function hideServices(){
		let menu = document.getElementById("servicesMenu");
		menu.style.display = "none";
		hideData();
	}
	
	function showShops(){
		let menu = document.getElementById("shopsMenu");
		menu.style.display = "block";
		hidePrincipal();
		showData();
	}
	
	function hideShops(){
		let menu = document.getElementById("shopsMenu");
		menu.style.display = "none";
		hideData();
	}
	
	return{
		showServices:showServices,
		hideServices:hideServices,
		showShops:showShops,
		hideShops:hideShops,
		showQuery:showQuery,
		hideQuery:hideQuery,
		showPrincipal:showPrincipal
	}
})();