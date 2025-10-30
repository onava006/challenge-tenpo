package cl.tenpo.challenge.domain.ports.output;

import cl.tenpo.challenge.domain.model.CallRegistry;

import java.util.List;

public interface CallRegistryRepository {

    public void saveCallRegistry(CallRegistry callRegistry);

    public List<CallRegistry> getPhoneCalls();
}
