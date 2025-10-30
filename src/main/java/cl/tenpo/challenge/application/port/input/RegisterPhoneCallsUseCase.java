package cl.tenpo.challenge.application.port.input;

import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryRequest;

import java.util.List;

public interface RegisterPhoneCallsUseCase {


    void register(List<CallRegistryRequest> phoneCallsList, String requestId);
}
