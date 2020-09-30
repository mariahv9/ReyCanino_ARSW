var app = (function () {
	
	var serviceSelected;
	var shopSelected;
	var enviaServicio = false;
	
	function validarFecha(date){
		let valores = date.split("-")
		let fechaReal = new Date(valores[0], parseInt(valores[1],10) - 1, valores[2])
		let now  =  new Date()
		now.setHours(0,0,0,0);
		console.log(fechaReal)
		if (fechaReal < now || valores.length < 3){
			toastr["warning"]("La fecha es invalida vacía","Oops! escoge una fecha valida");
			valid = false
		}
		
		return (fechaReal < now || valores.length < 3)
	}
	
	function validarDatos(){
		let name =  document.getElementById("name").value
		let email =  document.getElementById("email").value
		let petName =  document.getElementById("petName").value
		let raza =  document.getElementById("breed").value
		let telefono =  document.getElementById("tel").value
		let comentario =  document.getElementById("comment").value
		let valid = true
		
		if (name === null || name == ""){
			toastr["warning"]("El nombre de usuario no puede ser vacio","Oops! escoge tu nombre");
			valid = false
		}
		if (email === null || email == ""){
			toastr["warning"]("El email no puede ser vacio","Oops! escribe tu email");
			valid = false
		}
		if (petName === null || petName == ""){
			toastr["warning"]("La el nombre de tu mascota no puede ser vacio","Oops! escribe el nombre de tu mascota");
			valid = false
		}
		if (raza === null || raza == ""){
			toastr["warning"]("La raza de tu mascota no puede ser vacio","Oops! escribe la raza de tu mascota");
			valid = false
		}
		if (telefono === null || telefono == ""){
			toastr["warning"]("El telefono no puede ser vacio","Oops! escribe tu numero de telefono");
			valid = false
		}
		if (comentario === null || comentario == ""){
			toastr["warning"]("El comentario no puede ser vacio","Oops! escribe un comentario de la reserva");
			valid = false
		}
		
		return valid		
	}
	
	function reservarShop(horario){
		let date = document.getElementById("fechaShop").value
		let shop = document.getElementById("shopSelected").value
		let email =  document.getElementById("email").value
		let petName =  document.getElementById("petName").value
		let raza =  document.getElementById("breed").value
		let telefono =  document.getElementById("tel").value
		let comentario =  document.getElementById("comment").value
		let valid = true
		console.log(date)
		valid = !validarFecha(date)
		console.log("Fecha valida " + valid)
		valid = valid && validarDatos()
		console.log("Fecha valida y datos validos" + valid)
		 		
		if(valid){
			api.reserva(date, serviceSelected, shop, email, petName, raza, telefono, horario, comentario)
		}
								
	}
	
	function reservarService(horario){
		let date =  document.getElementById("fechaServicio").value
		let service = document.getElementById("serviceSelected").value
		let email =  document.getElementById("email").value
		let petName =  document.getElementById("petName").value
		let raza =  document.getElementById("breed").value
		let telefono =  document.getElementById("tel").value
		let comentario =  document.getElementById("comment").value
		let valid = true
		
		valid = !validarFecha(date)
		console.log("Fecha valida " + valid)
		valid = valid && validarDatos() 
		console.log("Fecha valida y datos validos" + valid)
		
		if(valid){
			api.reserva(date, service, shopSelected, email, petName, raza, telefono, horario, comentario)
		}
	}

	function consultarTienda (){
	    enviaServicio = false
	    let date = document.getElementById("fechaShop").value
	    let tienda = document.getElementById("shopSelected").value
	    let valid = true
	    valid = !validarFecha(date)
	    if (valid){
	        api.consultar(date, tienda, serviceSelected)
	    }
	}

	function consultarServicio (){
	    enviaServicio = true
	    let date = document.getElementById("fechaServicio").value
        let servicio = document.getElementById("serviceSelected").value
        let valid = true
        valid = !validarFecha(date)
        if (valid){
            api.consultar(date, shopSelected, servicio)
        }
	}

	function mostrarTabla (data){
	    let tabla = document.getElementById("tabla")
        tabla.style.display = "block";
        $("#filas").empty();
        data.map(function(element){
            let onclick = "";
            if(enviaServicio){
                onclick = "app.reservarService("+element.id+")";
            }
            else{
                onclick = "app.reservarShop("+element.id+")";
            }

            let boton = "<input type='button' value='reservar' onclick='" + onclick + "'></input>";
            let markup = "<tr> <td>"+ element.timeStart + "</td> <td> " + boton + "</td> </tr>";
            $("#filas").append(markup)
        });
	}
	
	return{
		reservarShop:reservarShop,
		reservarService:reservarService,
		selectService:function(service){
			serviceSelected = service;
		},
		selectShop:function(shop){
			shopSelected = shop;
		},
	    consultarServicio:consultarServicio,
	    consultarTienda:consultarTienda,
	    mostrarTabla:mostrarTabla
	}
})();
