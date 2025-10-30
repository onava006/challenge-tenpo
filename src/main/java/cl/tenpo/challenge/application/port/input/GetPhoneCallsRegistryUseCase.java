package cl.tenpo.challenge.application.port.input;

import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryResponse;

import java.util.List;

public interface GetPhoneCallsRegistryUseCase {

    public List<CallRegistryResponse> execute();
}
