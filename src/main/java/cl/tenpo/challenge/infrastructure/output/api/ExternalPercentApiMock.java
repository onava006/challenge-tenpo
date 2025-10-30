package cl.tenpo.challenge.infrastructure.output.api;

import cl.tenpo.challenge.domain.ports.output.PercentageRatePort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

@Component
public class ExternalPercentApiMock implements PercentageRatePort {

    //rango para establecer ejercicio. definir depsues por property
    double range = 20.0f;

    @Override
    public Integer getPercentageRate() throws ResourceAccessException {

        //pseudo request, si el valor obtenido es mayor a 17 se simula una falla del servicio para llamar al caché
        Double requestedPercent = requestPercentage();
        if(requestedPercent > 17){
            throw new ResourceAccessException("Error de conexión con el servicio externo");
        }

        return requestedPercent.intValue();

    }

    private Double requestPercentage() {
        double random = Math.random();
        return random * range + 1;

    }
}
