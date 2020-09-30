var api = (function() {

	function reserva(date, serviceSelected, shop, email, petName, raza, telefono, horario, comentario) {

		let valores = date.split("-")
		let newDate = new Date(valores[0], parseInt(valores[1], 10) - 1, valores[2])

		let param = {
			fecha: newDate,
			comentario: comentario,
			horario: {
				service: serviceSelected,
				petshop: shop,
				id: horario
			},
			cliente: {
				nombreMascota: petName,
				razaMascota: raza,
				telefono: telefono,
				correo: email
			}
		}

		console.log(JSON.stringify(param))
		$.ajax({
			data: JSON.stringify(param),
			contentType: "application/json",
			url: '/reyCanino/reservar',
			type: 'post',
			success: function(response) {
				crearMensaje(response)
			}
		});
	}

	function crearMensaje(response) {
		Swal.fire({
			title: "Creacion correcta",
			text: 'Se ha creado su reserva con código '+response,
			icon: 'success',
			confirmButtonColor: '#3085d6',
			confirmButtonText: 'Ok!'
		}).then((result) => {
			if (result.value) {
				location.assign("../index.html");
			}
		})
	}

	function consultar (date, tienda, servicio){
	    let valores = date.split("-")
        let newDate = new Date(valores[0], parseInt(valores[1], 10) - 1, valores[2])

        let param = {
            fecha: newDate,
            horario: {
                service: servicio,
                petshop: tienda,
            }
        }

        console.log(JSON.stringify(param))
        $.ajax({
            data: JSON.stringify(param),
            contentType: "application/json",
            url: '/reyCanino/consultar',
            type: 'post',
            success: function(response) {
                app.mostrarTabla(response)
            }
        });
	}

	return {
		reserva: reserva,
		consultar:consultar
	}
})();