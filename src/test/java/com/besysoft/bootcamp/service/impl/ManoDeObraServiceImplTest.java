package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;
import com.besysoft.bootcamp.exception.ManoDeObraException;
import com.besysoft.bootcamp.repository.IManoDeObraRepository;
import com.besysoft.bootcamp.util.ManoDeObraTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManoDeObraServiceImplTest {

    @MockBean
    private IManoDeObraRepository manoDeObraRepository;

    @Autowired
    private ManoDeObraServiceImpl manoDeObraService;

    @Test
    void crear_RetornaManoDeObra() {
        //GIVEN
        ManoDeObra manoDeObra = ManoDeObraTestUtil.generarManoDeObraSinId();
        ManoDeObra esperado = ManoDeObraTestUtil.generarManoDeObraConId();

        when(this.manoDeObraRepository.save(any(ManoDeObra.class))).thenReturn(esperado);

        //WHEN
        ManoDeObra actual = this.manoDeObraService.crear(manoDeObra);

        //THEN
        assertEquals(esperado, actual);
        verify(this.manoDeObraRepository).save(any(ManoDeObra.class));
    }

    @Test
    void crear_RetornaExcepcion_CuandoManoDeObraEsNula() {
        //GIVEN
        ManoDeObra manoDeObra = null;

        //WHEN Y THEN
        assertThrows(ManoDeObraException.class, ()-> {
            this.manoDeObraService.crear(manoDeObra);
        });
        verify(this.manoDeObraRepository, never()).save(any(ManoDeObra.class));
    }

    @Test
    void buscarPorId_RetornaOptionalConManoDeObra() {
        //GIVEN
        Long id = 1L;
        Optional<ManoDeObra> esperado = Optional.of(ManoDeObraTestUtil.generarManoDeObraConId());

        when(this.manoDeObraRepository.findById(anyLong())).thenReturn(esperado);

        //WHEN
        Optional<ManoDeObra> actual = this.manoDeObraService.buscarPorId(id);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado, actual);
        verify(this.manoDeObraRepository).findById(anyLong());
    }

    @Test
    void buscarPorId_RetornaOptionalVacio() {
        //GIVEN
        Long id = 1L;
        Optional<ManoDeObra> esperado = Optional.empty();

        when(this.manoDeObraRepository.findById(anyLong())).thenReturn(esperado);

        //WHEN
        Optional<ManoDeObra> actual = this.manoDeObraService.buscarPorId(id);

        //THEN
        assertTrue(actual.isEmpty());
        assertEquals(esperado, actual);
        verify(this.manoDeObraRepository).findById(anyLong());
    }

}