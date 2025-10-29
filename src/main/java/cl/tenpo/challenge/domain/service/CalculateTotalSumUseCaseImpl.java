package cl.tenpo.challenge.domain.service;

import cl.tenpo.challenge.domain.ports.input.CalculateTotalSumUseCase;

public class CalculateTotalSumUseCaseImpl implements CalculateTotalSumUseCase {


    @Override
    public int get(int firstValue, int secondValue, int percentage) {
        int sum =  firstValue + secondValue;
        int decimalPercentage = percentage / 100;
        int total = sum + (sum * decimalPercentage);



        return total;
    }
}
