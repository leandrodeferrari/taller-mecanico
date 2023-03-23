package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MecanicoInDto {

    @NotNull(message = "Activo no debe ser nulo")
    private Character activo;

    @NotEmpty(message = "El celular, no puede estar vacío")
    @Pattern(regexp = "^[0-9-]+$", message = "El celular solamente puede contener números y guiones")
    private String celular;

    private String apellido;

    private String especialidad;

    private String nombres;

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
