package cl.tenpo.challenge.infrastructure.output.api;

import cl.tenpo.challenge.domain.ports.output.PercentageRatePort;
import cl.tenpo.challenge.infrastructure.output.api.entities.Percentage;
import org.springframework.stereotype.Component;

@Component
public class ExternalPercentApiMock implements PercentageRatePort {

    //rango para establecer ejercicio. definir depsues por property
    double range = 20.0f;

    @Override
    public int getPercentageRate() {

        //pseudo request
        Percentage requestedPercent = requestPercentage();
        return (int) requestedPercent.getPercent();
    }

    private Percentage requestPercentage(){
        return new Percentage(range);
    }
}
