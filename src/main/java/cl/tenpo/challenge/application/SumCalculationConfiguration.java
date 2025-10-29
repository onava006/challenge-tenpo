package cl.tenpo.challenge.application;


import cl.tenpo.challenge.domain.ports.input.CalculateTotalSumUseCase;
import cl.tenpo.challenge.domain.service.CalculateTotalSumUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.JavaBean;

@Configuration
public class SumCalculationConfiguration {


    @Bean
    CalculateTotalSumUseCase configureTotalSumCalculator(){

        return new CalculateTotalSumUseCaseImpl();
    }


}
