package cl.tenpo.challenge.infrastructure.output.persistence.repository;

import cl.tenpo.challenge.infrastructure.output.entities.CallRegistryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPhoneCallRegistryRepository extends JpaRepository<CallRegistryEntity, Long> {
}
