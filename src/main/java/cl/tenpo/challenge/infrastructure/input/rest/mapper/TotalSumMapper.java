package cl.tenpo.challenge.infrastructure.input.rest.mapper;

import cl.hf.solutions.prueba.infrastructure.adapters.in.rest.model.TotalSumResponse;
import cl.tenpo.challenge.application.dto.response.TotalSumDto;

import java.math.BigDecimal;

public class TotalSumMapper {

    public static TotalSumDto toDto(TotalSumResponse response) {
        if (response == null) {
            return null;
        }

        return new TotalSumDto(
                response.getSuma() != null ? response.getSuma().floatValue() : 0f,
                response.getPorcentaje() != null ? response.getPorcentaje() : 0f,
                response.getTotal() != null ? response.getTotal().floatValue() : 0f
        );
    }

    public static TotalSumResponse toEntity(TotalSumDto dto) {
        if (dto == null) {
            return null;
        }

        TotalSumResponse response = new TotalSumResponse();
        response.setSuma(BigDecimal.valueOf(dto.sum()));
        response.setPorcentaje(dto.percentage());
        response.setTotal(BigDecimal.valueOf(dto.total()));
        return response;
    }
}

