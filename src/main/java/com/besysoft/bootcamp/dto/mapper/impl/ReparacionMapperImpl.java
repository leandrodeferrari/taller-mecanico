package com.besysoft.bootcamp.dto.mapper.impl;

import com.besysoft.bootcamp.domain.entity.ManoDeObra;
import com.besysoft.bootcamp.domain.entity.Repuesto;
import com.besysoft.bootcamp.dto.mapper.IManoDeObraMapper;
import com.besysoft.bootcamp.dto.mapper.IReparacionMapper;
import com.besysoft.bootcamp.dto.mapper.IRepuestoMapper;
import com.besysoft.bootcamp.dto.response.ReparacionOutDto;
import com.besysoft.bootcamp.dto.response.RepuestoOutDto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReparacionMapperImpl implements IReparacionMapper {

    private final IManoDeObraMapper manoDeObraMapper;
    private final IRepuestoMapper repuestoMapper;

    public ReparacionMapperImpl(IManoDeObraMapper manoDeObraMapper,
                                IRepuestoMapper repuestoMapper) {
        this.manoDeObraMapper = manoDeObraMapper;
        this.repuestoMapper = repuestoMapper;
    }

    @Override
    public ReparacionOutDto mapToDto(ManoDeObra manoDeObra,
                                     List<Repuesto> repuestos, LocalDateTime fechaFinDeReparacion) {

        ReparacionOutDto dto = new ReparacionOutDto();

        dto.setManoDeObra(this.manoDeObraMapper.mapToDto(manoDeObra));

        List< RepuestoOutDto> repuestoOutDtos = new ArrayList<>();

        repuestos.forEach(r -> {
                repuestoOutDtos.add(this.repuestoMapper.mapToDto(r));
        });

        dto.setRepuestos(repuestoOutDtos);
        dto.setFinDeReparacion(fechaFinDeReparacion.toString());

        return dto;

    }

}
