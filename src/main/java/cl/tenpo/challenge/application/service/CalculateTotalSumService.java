package cl.tenpo.challenge.application.service;

import cl.tenpo.challenge.domain.model.TotalSum;
import cl.tenpo.challenge.domain.ports.input.CalculateTotalSumUseCase;
import org.springframework.stereotype.Service;

@Service
public class CalculateTotalSumService implements CalculateTotalSumUseCase {


    @Override
    public TotalSum get(int firstValue, int secondValue, int percentage) {
        int sum =  firstValue + secondValue;
        double decimalPercentage = percentage / 100.0;
        double factorToSum = sum * decimalPercentage;
        int total = sum + (int)factorToSum;

        return new TotalSum(sum, percentage, total);
    }
}
