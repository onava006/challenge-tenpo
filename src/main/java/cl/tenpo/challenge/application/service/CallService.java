package cl.tenpo.challenge.application.service;

import cl.tenpo.challenge.domain.model.CallRegistry;
import cl.tenpo.challenge.domain.ports.input.GetCallRegistryUseCase;
import cl.tenpo.challenge.domain.ports.input.RegisterCallUseCase;

public class CallService implements RegisterCallUseCase, GetCallRegistryUseCase {

    @Override
    public void register(CallRegistry callRegistry) {

    }
}
