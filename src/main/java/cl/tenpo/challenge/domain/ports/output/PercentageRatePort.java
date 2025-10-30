package cl.tenpo.challenge.domain.ports.output;

import cl.tenpo.challenge.domain.model.Percentage;
import org.springframework.web.client.ResourceAccessException;

public interface PercentageRatePort {

    Integer getPercentageRate() throws ResourceAccessException;


}
