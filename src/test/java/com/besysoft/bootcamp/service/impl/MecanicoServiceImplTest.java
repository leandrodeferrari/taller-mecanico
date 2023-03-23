package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;
import com.besysoft.bootcamp.domain.entity.Mecanico;
import com.besysoft.bootcamp.domain.enums.ActivoEnum;
import com.besysoft.bootcamp.dto.mapper.IManoDeObraMapper;
import com.besysoft.bootcamp.dto.mapper.IMecanicoMapper;
import com.besysoft.bootcamp.dto.request.MecanicoInDto;
import com.besysoft.bootcamp.dto.response.ManoDeObraOutDto;
import com.besysoft.bootcamp.dto.response.MecanicoOutDto;
import com.besysoft.bootcamp.exception.MecanicoException;
import com.besysoft.bootcamp.exception.OrdenDeTrabajoException;
import com.besysoft.bootcamp.repository.IMecanicoRepository;
import com.besysoft.bootcamp.service.IManoDeObraService;
import com.besysoft.bootcamp.service.IOrdenDeTrabajoService;
import com.besysoft.bootcamp.util.ManoDeObraTestUtil;
import com.besysoft.bootcamp.util.MecanicoTestUtil;
import com.besysoft.bootcamp.util.OrdenDeTrabajoTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MecanicoServiceImplTest {

    @MockBean
    private IManoDeObraService manoDeObraService;

    @Autowired
    private IMecanicoMapper mecanicoMapper;

    @MockBean
    private IMecanicoRepository mecanicoRepository;

    @MockBean
    private IOrdenDeTrabajoService ordenDeTrabajoService;

    @Autowired
    private IManoDeObraMapper manoDeObraMapper;

    @Autowired
    private MecanicoServiceImpl mecanicoService;

    @Test
    void generarManoDeObra_RetornaManoDeObraOutDto() {
        //GIVEN
        Long mecanicoId = 1L;
        Long ordenDeTrabajoId = 1L;
        ManoDeObra manoDeObra = ManoDeObraTestUtil.generarManoDeObraConId();
        ManoDeObraOutDto esperado = this.manoDeObraMapper.mapToDto(manoDeObra);

        when(this.mecanicoRepository.findById(anyLong()))
                .thenReturn(Optional.of(MecanicoTestUtil.generarMecanico()));
        when(this.ordenDeTrabajoService.buscarPorId(anyLong()))
                .thenReturn(Optional.of(OrdenDeTrabajoTestUtil.generarOrdenDeTrabajoFacturada()));
        when(this.manoDeObraService.crear(any(ManoDeObra.class))).thenReturn(manoDeObra);

        //WHEN
        ManoDeObraOutDto actual = this.mecanicoService.generarManoDeObra(mecanicoId, ordenDeTrabajoId);

        //THEN
        assertEquals(esperado, actual);
        verify(this.mecanicoRepository).findById(anyLong());
        verify(this.ordenDeTrabajoService).buscarPorId(anyLong());
        verify(this.manoDeObraService).crear(any(ManoDeObra.class));
    }

    @Test
    void generarManoDeObra_RetornaExcepcion_CuandoMecanicoNoExiste() {
        //GIVEN
        Long mecanicoId = 1L;
        Long ordenDeTrabajoId = 1L;

        when(this.mecanicoRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        //WHEN Y THEN
        assertThrows(MecanicoException.class, ()-> {
            this.mecanicoService.generarManoDeObra(mecanicoId, ordenDeTrabajoId);
        });
        verify(this.mecanicoRepository).findById(anyLong());
        verify(this.ordenDeTrabajoService, never()).buscarPorId(anyLong());
        verify(this.manoDeObraService, never()).crear(any(ManoDeObra.class));
    }

    @Test
    void generarManoDeObra_RetornaExcepcion_CuandoMecanicoNoEstaActivo() {
        //GIVEN
        Long mecanicoId = 1L;
        Long ordenDeTrabajoId = 1L;
        Optional<Mecanico> optionalMecanico = Optional.of(MecanicoTestUtil.generarMecanicoInactivo());

        when(this.mecanicoRepository.findById(anyLong()))
                .thenReturn(optionalMecanico);

        //WHEN Y THEN
        assertThrows(MecanicoException.class, ()-> {
            this.mecanicoService.generarManoDeObra(mecanicoId, ordenDeTrabajoId);
        });
        assertEquals(ActivoEnum.FALSO.valor, optionalMecanico.get().getActivo());
        verify(this.mecanicoRepository).findById(anyLong());
        verify(this.ordenDeTrabajoService, never()).buscarPorId(anyLong());
        verify(this.manoDeObraService, never()).crear(any(ManoDeObra.class));
    }

    @Test
    void generarManoDeObra_RetornaExcepcion_CuandoOrdenDeTrabajoNoExiste() {
        //GIVEN
        Long mecanicoId = 1L;
        Long ordenDeTrabajoId = 1L;

        when(this.mecanicoRepository.findById(anyLong()))
                .thenReturn(Optional.of(MecanicoTestUtil.generarMecanico()));
        when(this.ordenDeTrabajoService.buscarPorId(anyLong()))
                .thenReturn(Optional.empty());

        //WHEN Y THEN
        assertThrows(OrdenDeTrabajoException.class, ()-> {
            this.mecanicoService.generarManoDeObra(mecanicoId, ordenDeTrabajoId);
        });
        verify(this.mecanicoRepository).findById(anyLong());
        verify(this.ordenDeTrabajoService).buscarPorId(anyLong());
        verify(this.manoDeObraService, never()).crear(any(ManoDeObra.class));
    }

    @Test
    void crear_RetornaMecanicoOutDto() {
        //GIVEN
        Mecanico mecanico = MecanicoTestUtil.generarMecanico();
        MecanicoInDto dto = MecanicoTestUtil.generarMecanicoInDto();
        MecanicoOutDto esperado = this.mecanicoMapper.mapToDto(mecanico);

        when(this.mecanicoRepository.save(any(Mecanico.class))).thenReturn(mecanico);

        //WHEN
        MecanicoOutDto actual = this.mecanicoService.crear(dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.mecanicoRepository).save(any(Mecanico.class));
    }

    @Test
    void crear_RetornaExcepcion_CuandoActivoTieneUnValorInvalido() {
        //GIVEN
        MecanicoInDto dto = MecanicoTestUtil.generarMecanicoInDto();
        dto.setActivo('D');

        //WHEN Y THEN
        assertThrows(MecanicoException.class, ()->{
            this.mecanicoService.crear(dto);
        });
        verify(this.mecanicoRepository, never()).save(any(Mecanico.class));
    }

}