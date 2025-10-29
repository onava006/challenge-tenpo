package cl.tenpo.challenge.application.configuration;


import cl.tenpo.challenge.domain.ports.input.CalculateTotalSumUseCase;
import cl.tenpo.challenge.domain.service.CalculateTotalSumUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculateTotalSumConfiguration {

    @Bean
    CalculateTotalSumUseCase configureTotalSumCalculator(){

        return new CalculateTotalSumUseCaseImpl();
    }

}
