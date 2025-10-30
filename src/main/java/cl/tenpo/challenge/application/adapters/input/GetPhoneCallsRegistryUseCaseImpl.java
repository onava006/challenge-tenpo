package cl.tenpo.challenge.application.adapters.input;

import cl.tenpo.challenge.application.mapper.CallRegistryMapper;
import cl.tenpo.challenge.application.port.input.GetPhoneCallsRegistryUseCase;
import cl.tenpo.challenge.domain.ports.output.CallRegistryRepository;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetPhoneCallsRegistryUseCaseImpl implements GetPhoneCallsRegistryUseCase {

    CallRegistryRepository callRegistryRepository;
    CallRegistryMapper callRegistryMapper;


    public GetPhoneCallsRegistryUseCaseImpl(CallRegistryRepository callRegistryRepository,
                                            CallRegistryMapper callRegistryMapper) {
        this.callRegistryRepository = callRegistryRepository;
        this.callRegistryMapper = callRegistryMapper;
    }

    @Override
    public List<CallRegistryResponse> execute() {
        return callRegistryRepository.getPhoneCalls().stream().map(callRegistryMapper::toResponse).toList();
    }
}
