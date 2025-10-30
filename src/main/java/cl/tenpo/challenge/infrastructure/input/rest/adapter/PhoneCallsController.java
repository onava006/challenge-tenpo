package cl.tenpo.challenge.infrastructure.input.rest.adapter;

import cl.tenpo.challenge.application.port.input.GetPhoneCallsRegistryUseCase;
import cl.tenpo.challenge.application.port.input.RegisterPhoneCallsUseCase;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.api.CallRegistryApi;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.AsyncResponse;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryRequest;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class PhoneCallsController implements CallRegistryApi {

    RegisterPhoneCallsUseCase registerPhoneCallsUseCase;

    GetPhoneCallsRegistryUseCase getPhoneCallsRegistryUseCase;

    public PhoneCallsController(RegisterPhoneCallsUseCase registerPhoneCallsUseCase, GetPhoneCallsRegistryUseCase getPhoneCallsRegistryUseCase){
        this.registerPhoneCallsUseCase =    registerPhoneCallsUseCase;
        this.getPhoneCallsRegistryUseCase = getPhoneCallsRegistryUseCase;
    }


    @Override
    public ResponseEntity<AsyncResponse> registerPhoneCalls(List<CallRegistryRequest> callRegistryRequest) {
        UUID requestId = UUID.randomUUID();
        registerPhoneCallsUseCase.register(callRegistryRequest, requestId.toString());

        String status = String.format("[%s] : Solicitud recibida, Total: %d", LocalDateTime.now(),
               callRegistryRequest.size());

        AsyncResponse response = new AsyncResponse(status, requestId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<CallRegistryResponse>> retrievePhoneCalls() {

        List<CallRegistryResponse> callRegistryResponse =  getPhoneCallsRegistryUseCase.execute();
        return new ResponseEntity<>(callRegistryResponse, HttpStatus.OK);

    }
}
