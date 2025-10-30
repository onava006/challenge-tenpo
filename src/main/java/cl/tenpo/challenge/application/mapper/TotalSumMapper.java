package cl.tenpo.challenge.application.mapper;

import cl.tenpo.challenge.application.dto.response.TotalSumDto;
import cl.tenpo.challenge.domain.model.TotalSum;

public class TotalSumMapper {

    public static TotalSumDto toResponse(TotalSum domain) {
        if (domain == null) {
            return null;
        }

        return new TotalSumDto(
                domain.sum(),
                domain.percentage(),
                domain.total()
        );
    }

    public static TotalSum toDomain(TotalSumDto dto) {
        if (dto == null) {
            return null;
        }

        return new TotalSum(
                dto.sum(),
                dto.percentage(),
                dto.total()
        );
    }}
