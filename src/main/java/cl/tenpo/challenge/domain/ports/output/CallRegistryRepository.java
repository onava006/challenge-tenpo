package cl.tenpo.challenge.domain.ports.output;

import cl.tenpo.challenge.domain.model.CallRegistry;

public interface CallRegistryRepository {

    public void saveCallRegistry(CallRegistry callRegistry);

}
