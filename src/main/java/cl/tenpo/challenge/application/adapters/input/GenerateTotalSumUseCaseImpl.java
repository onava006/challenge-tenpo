package cl.tenpo.challenge.application.adapters.input;

import cl.tenpo.challenge.application.dto.response.TotalSumDto;
import cl.tenpo.challenge.application.mapper.TotalSumMapper;
import cl.tenpo.challenge.application.port.input.GenerateTotalSumUseCase;
import cl.tenpo.challenge.domain.model.Percentage;
import cl.tenpo.challenge.domain.model.TotalSum;
import cl.tenpo.challenge.domain.ports.input.CalculateTotalSumUseCase;
import cl.tenpo.challenge.domain.ports.output.PercentageRateCachePort;
import cl.tenpo.challenge.domain.ports.output.PercentageRatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;


@Service
public class GenerateTotalSumUseCaseImpl implements GenerateTotalSumUseCase {

    PercentageRateCachePort percentageRateCachePort;
    PercentageRatePort percentageRatePort;
    CalculateTotalSumUseCase totalSumUseCase;

    private static final Logger logger = LoggerFactory.getLogger(GenerateTotalSumUseCaseImpl.class);

    public GenerateTotalSumUseCaseImpl(    PercentageRateCachePort percentageRateCachePort,
    PercentageRatePort percentageRatePort,
    CalculateTotalSumUseCase totalSumUseCase){
        this.percentageRateCachePort = percentageRateCachePort;
        this.percentageRatePort = percentageRatePort;
        this.totalSumUseCase = totalSumUseCase;
    }

    @Override
    public TotalSumDto get(int firstValue, int secondValue) {

        Integer percentageRate;
        Integer percentageCacheRate;
        TotalSum total;

        try {
            percentageRate = percentageRatePort.getPercentageRate();
            percentageRateCachePort.cachePercentageRate(new Percentage(percentageRate));
            total = totalSumUseCase.get(firstValue, secondValue, percentageRate);
        }

        catch(ResourceAccessException e){
            logger.info("Error in Api request. Performing a cache request");
            percentageCacheRate = percentageRateCachePort.getPercentageRate().getPercetage();
            total = totalSumUseCase.get(firstValue, secondValue, percentageCacheRate);
        }
        return TotalSumMapper.toResponse(total);

    }
}
