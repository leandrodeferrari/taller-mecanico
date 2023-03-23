package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClienteVehiculoInDto {

    //Datos del Vehiculo
    @NotBlank(message = "La patente del Vehículo, no puede ser nula o vacía")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "La patente solamente puede contener letras mayúsculas y números")
    private String patente;

    @Min(message = "Año inválido. El año mínimo es 1876 (según Ley de Patentes N° 1.947, de Argentina)", value = 1876)
    private Integer anio;

    @NotEmpty(message = "El color, no puede estar vacío")
    private String color;

    @NotEmpty(message = "La marca, no puede estar vacía")
    private String marca;

    @NotEmpty(message = "El modelo, no puede estar vacío")
    private String modelo;

    //Datos del Cliente
    @NotBlank(message = "El correo electronico del Cliente, no puede ser nulo o vacío")
    private String correoElectronico;

    @NotEmpty(message = "Los nombres, no pueden estar vacíos")
    @Size(message = "Los nombres no puede ser mayor a 100 carácteres", max = 100)
    private String nombres;

    @NotEmpty(message = "El apellido, no puede estar vacío")
    @Size(message = "El apellido no puede ser mayor a 80 carácteres", max = 80)
    private String apellido;

    @NotEmpty(message = "El celular, no puede estar vacío")
    @Pattern(regexp = "^[0-9-]+$", message = "El celular solamente puede contener números y guiones")
    private String celular;

    @NotEmpty(message = "El telefono de linea, no puede estar vacío")
    @Pattern(regexp = "^[0-9-]+$", message = "El teléfono de linea solamente puede contener números y guiones")
    private String telefonoDeLinea;

    @NotEmpty(message = "La calle, no puede estar vacía")
    private String calle;

    @NotEmpty(message = "El codigo postal, no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El código postal solamente puede contener números")
    private String codigoPostal;

    @NotEmpty(message = "El departamento, no puede estar vacío")
    private String departamento;

    @NotEmpty(message = "La localidad, no puede estar vacía")
    private String localidad;

    @NotEmpty(message = "El número, de la calle, no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El número, de la calle, solamente puede contener números")
    private String numero;

    @NotEmpty(message = "El piso, no puede estar vacío")
    @Pattern(regexp = "^[0-9]+$", message = "El piso solamente puede contener números")
    private String piso;

}
