var api = (function() {

	function reserva(cliente, serviceSelected, shop, email, petName, raza, telefono, horario, comentario) {
		let param = {
		    id: horario,
		    servicio: serviceSelected,
		    tiendaCanina: shop,
			reserva: {
			    cliente: cliente,
			    correo: email,
			    mascota: petName,
			    comentario: comentario,
			    telefono: telefono,
			    raza: raza
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
	    let codigo = response.substring (9, 17);
		Swal.fire({
			title: "Creacion correcta",
			text: 'Revisa tu correo para confirmar la reserva.',
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
            fechaConsulta: newDate,
            servicio: servicio,
			tiendaCanina: tienda
        }
        $.ajax({
            data: JSON.stringify(param),
            contentType: "application/json",
            url: '/reyCanino/consultar',
            type: 'post',
            success: function(response) {
				app.asignData(response);
                app.mostrarTabla();
				app.connect();				
            }
        });
	}
	return {
		reserva: reserva,
		consultar:consultar
	}
})();