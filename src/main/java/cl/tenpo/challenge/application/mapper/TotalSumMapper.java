package cl.tenpo.challenge.application.mapper;

import cl.tenpo.challenge.application.dto.response.TotalSumResponse;
import cl.tenpo.challenge.domain.model.TotalSum;

public class TotalSumMapper {

    public static TotalSumResponse toResponse(TotalSum domain) {
        if (domain == null) {
            return null;
        }

        return new TotalSumResponse(
                domain.sum(),
                domain.percentage(),
                domain.total()
        );
    }

    public static TotalSum toDomain(TotalSumResponse response) {
        if (response == null) {
            return null;
        }

        return new TotalSum(
                response.sum(),
                response.percentage(),
                response.total()
        );
    }}
