package cl.tenpo.challenge.application.port.input;

import cl.tenpo.challenge.application.dto.response.TotalSumResponse;

public interface GenerateTotalSumUseCase {

    public TotalSumResponse get(int firstValue, int secondValue);
}
