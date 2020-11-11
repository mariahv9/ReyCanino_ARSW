var api = (function () {
  function reserva(
    cliente,
    serviceSelected,
    shop,
    email,
    petName,
    raza,
    telefono,
    horario,
    comentario
  ) {
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
        raza: raza,
      },
    };
    $.ajax({
      data: JSON.stringify(param),
      contentType: "application/json",
      url: "/reyCanino/reservar",
      type: "post",
      success: function (response) {
        crearMensaje(response);
      },
    });
  }

  function crearMensaje(response) {
    let codigo = response.substring(9, 17);
    Swal.fire({
      title: "Creacion correcta",
      text: "Revisa tu correo para confirmar la reserva.",
      icon: "success",
      confirmButtonColor: "#3085d6",
      confirmButtonText: "¡Ok!",
    }).then((result) => {
      if (result.value) {
        location.assign("../index.html");
      }
    });
  }

  function consultar(date, tienda, servicio) {
    let valores = date.split("-");
    let newDate = new Date(
      valores[0],
      parseInt(valores[1], 10) - 1,
      valores[2]
    );
    let param = {
      fechaConsulta: newDate,
      servicio: servicio,
      tiendaCanina: tienda,
    };
    $.ajax({
      data: JSON.stringify(param),
      contentType: "application/json",
      url: "/reyCanino/consultar",
      type: "post",
      success: function (response) {
        app.asignData(response);
        app.mostrarTabla();
        app.connect();
      },
    });
  }

  function consultarReserva(id) {
    $.ajax({
      url: "/reyCanino/consultar/" + id,
      type: "get",
      success: function (response) {
        if (response.reserva != null) {
          mostrarResumen(response);
        } else {
          mostrarError();
        }
      },
      error: function (xhr, status, errorThrown) {
        mostrarError();
      },
    });
  }

  function cancelarReserva(id) {
    $.ajax({
      url: "/reyCanino/cancelar/" + id,
      type: "get",
      success: function (response) {
        if (response == "Éxito") {
          confirmarCancelación(response);
        } else {
          mostrarError();
        }
      },
      error: function (xhr, status, errorThrown) {
        mostrarError();
      },
    });
  }

  function mostrarResumen(response) {
    let horaIni = response.fi.split("T")[1];
    horaIni = horaIni.substring(0, horaIni.length - 9);
    let fechaIni = response.fi.split("T")[0];
    Swal.fire({
      title: response.reserva.cliente + ", así quedo la reserva",
      text:
        "Fecha: " +
        fechaIni +
        ", hora: " +
        horaIni +
        " y para el servicio de " +
        response.servicio,
      icon: "success",
      confirmButtonColor: "#3085d6",
      confirmButtonText: "¡Ok!",
    }).then((result) => {
      if (result.value) {
        location.assign("../index.html");
      }
    });
  }

  function confirmarCancelación(response) {
    Swal.fire({
      title: "Cancelación exitosa",
      text: "Esperamos atenderte pronto.",
      icon: "success",
      confirmButtonColor: "#3085d6",
      confirmButtonText: "Gracias",
    }).then((result) => {
      if (result.value) {
        location.assign("../index.html");
      }
    });
  }

  function mostrarError() {
    Swal.fire({
      title: "¡Error!",
      text: "Por favor confirme el código de la reserva.",
      icon: "warning",
      confirmButtonColor: "#3085d6",
      confirmButtonText: "Ok...",
    }).then((result) => {
      if (result.value) {
        location.assign("../index.html");
      }
    });
  }

  return {
    reserva: reserva,
    consultar: consultar,
    consultarReserva: consultarReserva,
    cancelarReserva: cancelarReserva,
  };
})();
