package cl.tenpo.challenge.application.adapters.input;

import cl.tenpo.challenge.application.exception.EmptyPhoneRegistryException;
import cl.tenpo.challenge.application.mapper.CallRegistryMapper;
import cl.tenpo.challenge.application.port.input.RegisterPhoneCallsUseCase;
import cl.tenpo.challenge.domain.ports.output.CallRegistryRepository;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterPhoneCallsUseCaseImpl implements RegisterPhoneCallsUseCase {

    CallRegistryRepository callRegistryRepository;
    CallRegistryMapper callRegistryMapper;

    private static final Logger logger = LoggerFactory.getLogger(RegisterPhoneCallsUseCaseImpl.class);

    public RegisterPhoneCallsUseCaseImpl(CallRegistryRepository callRegistryRepository, CallRegistryMapper callRegistryMapper){
        this.callRegistryRepository = callRegistryRepository;
        this.callRegistryMapper = callRegistryMapper;
    }

    @Async
    @Override
    public void register(List<CallRegistryRequest> phoneCallsList, String requestId) {

        if(phoneCallsList.size() ==0 ){
            throw new EmptyPhoneRegistryException("La lista de llamadas se encuentra vacia");
        }

        logger.info("Solicitud recibida - RequestId: {}, Total: {}",
                requestId, phoneCallsList.size());

        //solucion temporal, cambiar por una que agregue la lista de una sola vez
        for(CallRegistryRequest cr: phoneCallsList) {
            callRegistryRepository.saveCallRegistry(callRegistryMapper.toDomainFromRequest(cr));
        }

    }
}
