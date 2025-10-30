package cl.tenpo.challenge.application.adapters.input;

import cl.tenpo.challenge.application.port.input.GetPhoneCallsRegistryUseCase;
import cl.tenpo.challenge.domain.ports.output.CallRegistryRepository;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPhoneCallsRegistryUseCaseImpl implements GetPhoneCallsRegistryUseCase {

    CallRegistryRepository callRegistryRepository;


    public GetPhoneCallsRegistryUseCaseImpl(CallRegistryRepository callRegistryRepository){
        this.callRegistryRepository = callRegistryRepository;
    }

    @Override
    public List<CallRegistryResponse> execute(){
return null;
    }
}
