package cl.tenpo.challenge.infrastructure.output.persistence.adapter;

import cl.tenpo.challenge.application.mapper.CallRegistryMapper;
import cl.tenpo.challenge.domain.model.CallRegistry;
import cl.tenpo.challenge.domain.ports.output.CallRegistryRepository;
import cl.tenpo.challenge.infrastructure.output.entities.CallRegistryEntity;
import cl.tenpo.challenge.infrastructure.output.persistence.repository.JpaPhoneCallRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostgresCallRegistryRepository implements CallRegistryRepository {

    JpaPhoneCallRegistryRepository repository;

    CallRegistryMapper callRegistryMapper;

    public PostgresCallRegistryRepository(JpaPhoneCallRegistryRepository repository, CallRegistryMapper callRegistryMapper){
        this.repository = repository;
        this.callRegistryMapper = callRegistryMapper;

    }

    @Override
    public void saveCallRegistry(CallRegistry callRegistry) {

        CallRegistryEntity registryEntity = callRegistryMapper.toEntity(callRegistry);
        repository.save(registryEntity);
    }

    @Override
    public List<CallRegistry> getPhoneCalls() {
        return List.of();
    }
}
