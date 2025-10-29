package cl.tenpo.challenge.infrastructure.output.api;

import cl.tenpo.challenge.domain.ports.output.PercentageRatePort;
import org.springframework.stereotype.Component;

@Component
public class ExternalPercentApiMock implements PercentageRatePort {

    //definir depsues por property
    double range = 20.0f;


    @Override
    public int getPercentageRate() {
            double random = Math.random();
            double total =random * range + 1;
            return (int) total;

    }
}
