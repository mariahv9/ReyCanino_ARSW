var api = (function () {

    function reserva(date, serviceSelected, shop, email, petName, raza, telefono, horario) {
        console.log("api crear sala recibido")
		console.log(date, serviceSelected, shop, email, petName, raza, telefono)
        let param = {
			fecha:date,
			horario:{
				service:serviceSelected,
				petshop:shop,
				id:horario				
			},
			cliente:{
				nombreMascota: petName,
				razaMascota:raza,
				telefono:telefono,
				correo:email	
			}		
		}
		
		console.log(JSON.stringify(param))
		 $.ajax({
                data: JSON.stringify(param),
				contentType: "application/json",
                url:   '/reyCanino/reservar', //archivo que recibe la peticion
                type:  'post', //método de envio
                beforeSend: function () {
                        console.log("Enviando c:")
                },
                success:  function (response) { //una vez que el archivo recibe el request lo procesa y lo devuelve
                        console.log("Se envio c:")
                }
        });
    }

	return{
        reserva:reserva
    }
})();