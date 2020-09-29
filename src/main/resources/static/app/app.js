var app = (function () {
	
	var serviceSelected;
	var shopSelected;
	
	function validarFecha(date){
		let valores = date.split("-")
		let fechaReal = new Date(valores[0], parseInt(valores[1],10) - 1, valores[2])
		let now  =  new Date()
		now.setHours(0,0,0,0);
		
		return (fechaReal < now || valores.length < 3)
	}
	
	function reservarShop(horario){
		let date =  document.getElementById("fechaShop").value
		let shop =  document.getElementById("shopSelected").value
		let name =  document.getElementById("name").value
		let email =  document.getElementById("email").value
		let petName =  document.getElementById("petName").value
		let raza =  document.getElementById("breed").value
		let telefono =  document.getElementById("tel").value
		let valid = true
		if (validarFecha(date)){
			toastr["warning"]("La fecha es invalida vacía","Oops! escoge una fecha valida");
			valid = false
		}
		if (shop === null || shop == ""){
			toastr["warning"]("La pet shop no puede ser vacía","Oops! escoge una petshop para reservar");
			valid = false
		}
		if (name === null || name == ""){
			toastr["warning"]("El nombre de usuario no puede ser vacio","Oops! escoge tu nombre");
			valid = false
		}
		if (email === null || email == ""){
			toastr["warning"]("El email no puede ser vacio","Oops! escribe tu email");
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
		
		if(valid){
			api.reserva(date, serviceSelected, shop, email, petName, raza, telefono, horario)
		}
								
	}
	
	return{
		reservarShop:reservarShop,
		selectService:function(service){
			serviceSelected = service;
		},
		selectShop:function(shop){
			shopSelected = shop;
		}
	}
})();
