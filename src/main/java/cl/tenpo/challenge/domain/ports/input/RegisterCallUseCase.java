package cl.tenpo.challenge.domain.ports.input;

import cl.tenpo.challenge.domain.model.CallRegistry;

import java.util.List;

public interface RegisterCallUseCase {

    public void register(CallRegistry callRegistry);
}
