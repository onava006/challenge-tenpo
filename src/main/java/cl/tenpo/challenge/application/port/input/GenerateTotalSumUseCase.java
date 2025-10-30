package cl.tenpo.challenge.application.port.input;

import cl.tenpo.challenge.application.dto.response.TotalSumDto;

public interface GenerateTotalSumUseCase {

    TotalSumDto get(int firstValue, int secondValue);
}
