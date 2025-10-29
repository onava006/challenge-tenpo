package cl.tenpo.challenge.domain.ports.input;

import cl.tenpo.challenge.domain.model.TotalSum;

public interface CalculateTotalSumUseCase {

    TotalSum get(int firstValue, int secondValue, int percentage);
}
