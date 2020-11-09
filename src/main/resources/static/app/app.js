var app = (function() {

	var serviceSelected;
	var shopSelected;
	var enviaServicio = false;

	function validarFecha(date) {
		let valores = date.split("-")
		let fechaReal = new Date(valores[0], parseInt(valores[1], 10) - 1, valores[2])
		let now = new Date()
		now.setHours(0, 0, 0, 0);
		if (fechaReal < now || valores.length < 3) {
			toastr["warning"]("La fecha es invalida vacía", "Oops! escoge una fecha valida");
			valid = false
		}
		return (fechaReal < now || valores.length < 3)
	}

	function validarEmail(email) {
		emailRegex = /^[-\w.%+]{1,64}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/i;
		return emailRegex.test(email);
	}

	function validarDatos() {
		let name = document.getElementById("name");
		let email = document.getElementById("email");
		let petName = document.getElementById("petName");
		let raza = document.getElementById("breed");
		let telefono = document.getElementById("tel");
		let comentario = document.getElementById("comment");
		let valid = true
		view.quitarColores();

		if (name.value === null || name.value == "") {
			toastr["warning"]("El nombre de usuario no puede ser vacio", "Oops! escoge tu nombre");
			name.style.borderColor = "red";
			valid = false
		}
		else if (email.value === null || email.value == "") {
			toastr["warning"]("El email no puede ser vacio", "Oops! escribe tu email");
			email.style.borderColor = "red";
			valid = false
		}
		else if (!validarEmail(email.value)) {
			toastr["warning"]("Email inválido", "Oops! escribe tu email");
			email.style.borderColor = "red";
			valid = false
		}
		else if (petName.value === null || petName.value == "") {
			toastr["warning"]("La el nombre de tu mascota no puede ser vacio", "Oops! escribe el nombre de tu mascota");
			petName.style.borderColor = "red";
			valid = false
		}
		else if (raza.value === null || raza.value == "") {
			toastr["warning"]("La raza de tu mascota no puede ser vacio", "Oops! escribe la raza de tu mascota");
			raza.style.borderColor = "red";
			valid = false
		}
		else if (telefono.value === null || telefono.value == "") {
			toastr["warning"]("El telefono no puede ser vacio", "Oops! escribe tu numero de telefono");
			telefono.style.borderColor = "red";
			valid = false
		}
		else if (comentario.value === null || comentario.value == "") {
			toastr["warning"]("El comentario no puede ser vacio", "Oops! escribe un comentario de la reserva");
			comentario.style.borderColor = "red";
			valid = false
		}

		return valid
	}

	function reservarShop(horario) {
	    let name = document.getElementById("name").value;
		let date = document.getElementById("fechaShop").value
		let shop = document.getElementById("shopSelected").value
		let email = document.getElementById("email").value
		let petName = document.getElementById("petName").value
		let raza = document.getElementById("breed").value
		let telefono = document.getElementById("tel").value
		let comentario = document.getElementById("comment").value
		let valid = true
		valid = !validarFecha(date)
		valid = valid && validarDatos()
        if(valid){
            api.reserva(name, serviceSelected, shop, email, petName, raza, telefono, horario, comentario)
        }
	}

	function reservarService(horario) {
	    let name = document.getElementById("name").value;
		let date = document.getElementById("fechaServicio").value
		let service = document.getElementById("serviceSelected").value
		let email = document.getElementById("email").value
		let petName = document.getElementById("petName").value
		let raza = document.getElementById("breed").value
		let telefono = document.getElementById("tel").value
		let comentario = document.getElementById("comment").value
		let valid = true
		valid = !validarFecha(date)
		valid = valid && validarDatos()
		if(valid){
			api.reserva(name, service, shopSelected, email, petName, raza, telefono, horario, comentario)
		}
	}

	function consultarTienda() {
		enviaServicio = false
		let date = document.getElementById("fechaShop").value
		let tienda = document.getElementById("shopSelected").value
		let valid = true
		valid = !validarFecha(date)
		if (valid) {
			api.consultar(date, tienda, serviceSelected)
		}
	}

	function consultarServicio() {
		enviaServicio = true
		let date = document.getElementById("fechaServicio").value
		let servicio = document.getElementById("serviceSelected").value
		let valid = true
		valid = !validarFecha(date)
		if (valid) {
			api.consultar(date, shopSelected, servicio)
		}
	}

	function mostrarTabla(data) {
		let tabla = document.getElementById("tabla");
		let table = document.getElementById("table");
		let heading = document.getElementById("noHorarios");
		tabla.style.display = "block";
		heading.style.display = "none";
		table.style.display = "none";
		if(data.length > 0){
			table.style.display = "";
			$("#filas").empty();
			data.map(function(element) {
				let onclick = "";
				let fechaIni = element.fi.split("T")[1];
				fechaIni = fechaIni.substring(0, fechaIni.length - 9);
				let fechaFin = element.ff.split("T")[1];
				fechaFin = fechaFin.substring(0, fechaFin.length - 9);
				if (enviaServicio) {
					onclick = "app.reservarService(\"" + element.id + "\")";
				}
				else {
					onclick = "app.reservarShop(\"" + element.id + "\")";
				}
				let boton = "<input type='button' value='reservar' onclick='" + onclick + "'></input>";
				let markup = "<tr> <td>" + fechaIni + " - " + fechaFin + "</td> <td> " + boton + "</td> </tr>";
				$("#filas").append(markup)
			});
		}
		else
		{
			heading.style.display = "block";
		}
	}

	function consultarReserva() {
        let codigo = document.getElementById("codigo").value;
        api.consultarReserva(codigo);
    }

    function cancelarReserva(){
        let codigo = document.getElementById("codigo1").value;
        api.cancelarReserva(codigo);
    }

	return {
		reservarShop: reservarShop,
		reservarService: reservarService,
		selectService: function(service) {
			serviceSelected = service;
		},
		selectShop: function(shop) {
			shopSelected = shop;
		},
		consultarServicio: consultarServicio,
		consultarTienda: consultarTienda,
		mostrarTabla: mostrarTabla,
		consultarReserva: consultarReserva,
		cancelarReserva: cancelarReserva
	}
})();