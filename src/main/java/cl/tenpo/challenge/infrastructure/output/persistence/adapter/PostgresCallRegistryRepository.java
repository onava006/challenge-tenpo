package cl.tenpo.challenge.infrastructure.output.persistence.adapter;

import cl.tenpo.challenge.application.mapper.CallRegistryMapper;
import cl.tenpo.challenge.domain.model.CallRegistry;
import cl.tenpo.challenge.domain.ports.output.CallRegistryRepository;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryResponse;
import cl.tenpo.challenge.infrastructure.output.entities.CallRegistryEntity;
import cl.tenpo.challenge.infrastructure.output.persistence.repository.JpaPhoneCallRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

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

        List<CallRegistryEntity> phoneCalls = repository.findAll();
        return phoneCalls.stream().map(callRegistryMapper::toDomainFromEntity).toList();
    }
}
