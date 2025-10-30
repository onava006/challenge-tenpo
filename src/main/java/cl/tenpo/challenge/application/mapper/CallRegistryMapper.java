package cl.tenpo.challenge.application.mapper;

import cl.tenpo.challenge.domain.model.CallRegistry;
import cl.tenpo.challenge.domain.model.CallStatus;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryRequest;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallRegistryResponse;
import cl.tenpo.challenge.infrastructure.adapters.in.rest.model.CallStatusApi;
import cl.tenpo.challenge.infrastructure.output.entities.CallRegistryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CallRegistryMapper {

    public CallRegistry toDomainFromRequest(CallRegistryRequest request) {
        return new CallRegistry(
                request.getDate(),
                request.getEndpoint(),
                request.getParameters(),
                toDomain(request.getCallstatus())
        );
    }

    public CallRegistryEntity toEntity(CallRegistry domain) {
        return CallRegistryEntity.builder()
                .date(domain.date())
                .endpoint(domain.endpoint())
                .parameters(domain.parameters())
                .callstatus(domain.callstatus())
                .build();
    }

    public CallRegistry toDomainFromEntity(CallRegistryEntity entity) {
        return new CallRegistry(
                entity.getDate(),
                entity.getEndpoint(),
                entity.getParameters(),
                entity.getCallstatus()
        );
    }

    public CallRegistryResponse toResponse(CallRegistry domain) {
        CallRegistryResponse response = new CallRegistryResponse();
        response.setDate(domain.date());
        response.setEndpoint(domain.endpoint());
        response.setParameters(domain.parameters());
        response.setCallstatus(toApi(domain.callstatus()));
        return response;
    }

    public List<CallRegistry> toDomainList(List<CallRegistryEntity> entities) {
        return entities.stream()
                .map(this::toDomainFromEntity)
                .toList();
    }

    public List<CallRegistryResponse> toResponseList(List<CallRegistry> domains) {
        return domains.stream()
                .map(this::toResponse)
                .toList();
    }

    private CallStatusApi toApi(CallStatus domain) {
        if (domain == null) return null;
        return switch (domain) {
            case RECEIVED -> CallStatusApi.RECEIVED;
            case ERROR -> CallStatusApi.ERROR;
            case REDIRECTED -> CallStatusApi.REDIRECTED;
        };
    }

    private CallStatus toDomain(CallStatusApi api) {
        if (api == null) return null;
        return switch (api) {
            case RECEIVED -> CallStatus.RECEIVED;
            case ERROR -> CallStatus.ERROR;
            case REDIRECTED -> CallStatus.REDIRECTED;
        };
    }
}
