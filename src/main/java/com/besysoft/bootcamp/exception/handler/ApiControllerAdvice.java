package com.besysoft.bootcamp.exception.handler;

import com.besysoft.bootcamp.dto.response.ExcepcionDto;
import com.besysoft.bootcamp.exception.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto validException(MethodArgumentNotValidException ex){

        List<FieldError> errores = ex.getBindingResult().getFieldErrors();

        Map<String, String> detalle = new HashMap<>();

        log.info("Ocurrio una validacion");

        errores.forEach(error -> {
            log.info("Atributo: " + error.getField() + " - Validacion: " + error.getDefaultMessage());
            detalle.put(error.getField(), error.getDefaultMessage());
        });

        return new ExcepcionDto(HttpStatus.BAD_REQUEST.value(), "Validaciones", detalle);

    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto ordenDeTrabajoException(OrdenDeTrabajoException ex){
        log.info("Ocurrio una validacion de Orden de Trabajo: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto mecanicoException(MecanicoException ex){
        log.info("Ocurrio una validacion de Mecanico: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto clienteException(ClienteException ex){
        log.info("Ocurrio una validacion de Cliente: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto clienteVehiculoException(ClienteVehiculoException ex){
        log.info("Ocurrio una validacion de Cliente-Vehiculo: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto idException(IdException ex){
        log.info("Ocurrio una validacion de ID: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto vehiculoException(VehiculoException ex){
        log.info("Ocurrio una validacion de Vehiculo: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto manoDeObraException(ManoDeObraException ex){
        log.info("Ocurrio una validacion de Mano De Obra: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto repuestoException(RepuestoException ex){
        log.info("Ocurrio una validacion de Repuesto: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto formatoException(DateTimeParseException ex){
        log.info("Ocurrio una validacion de formato de hora o fecha: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                "Formato inválido. Formato de fecha: yyyy-MM-dd. Formato de hora: hh:mm:ss",
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto activoException(HttpMessageNotReadableException ex){
        log.info("Ocurrio una validacion de Mecanico, del atributo activo: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                "Activo tiene que ser solamente un caracter. F o V",
                null
        );
    }

}
