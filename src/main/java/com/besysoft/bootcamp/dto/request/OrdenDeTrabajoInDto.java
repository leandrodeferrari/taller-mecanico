package com.besysoft.bootcamp.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class OrdenDeTrabajoInDto {

    @NotBlank(message = "El nivel del combustible no puede ser nulo o vacío")
    @Size(message = "El nivel del combustible no puede ser mayor a 255 carácteres", max = 255)
    private String nivelDelCombustible;

    @Min(message = "El kilometraje no puede ser menor a 0", value = 0)
    private Long kilometraje;

    @NotBlank(message = "El detalle de la falla no puede ser nulo o vacío")
    @Size(message = "El detalle de la falla no puede ser mayor a 255 carácteres", max = 255)
    private String detalleDeLaFalla;

}
