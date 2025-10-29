package cl.tenpo.challenge.application.adapters.input;

import cl.tenpo.challenge.application.dto.response.TotalSumResponse;
import cl.tenpo.challenge.application.mapper.TotalSumMapper;
import cl.tenpo.challenge.application.port.input.GenerateTotalSumUseCase;
import cl.tenpo.challenge.domain.model.TotalSum;
import cl.tenpo.challenge.domain.ports.input.CalculateTotalSumUseCase;
import cl.tenpo.challenge.domain.ports.output.PercentageRateCachePort;
import cl.tenpo.challenge.domain.ports.output.PercentageRatePort;

public class GenerateTotalSumUseCaseImpl implements GenerateTotalSumUseCase {

    PercentageRateCachePort percentageRateCachePort;
    PercentageRatePort percentageRatePort;
    CalculateTotalSumUseCase totalSumUseCase;

    public GenerateTotalSumUseCaseImpl(    PercentageRateCachePort percentageRateCachePort,
    PercentageRatePort percentageRatePort,
    CalculateTotalSumUseCase totalSumUseCase){
        this.percentageRateCachePort = percentageRateCachePort;
        this.percentageRatePort = percentageRatePort;
        this.totalSumUseCase = totalSumUseCase;
    }

    @Override
    public TotalSumResponse get(int firstValue, int secondValue) {
        Integer percentageRate;
        Integer percentageCacheRate;
        TotalSum total;

        try {
            percentageRate = percentageRatePort.getPercentageRate();
            percentageRateCachePort.cachePercentageRate(percentageRate);
            total = totalSumUseCase.get(firstValue, secondValue, percentageRate);
        }
        //mejorar definicion de exception para que sea mas precisa segun servicio a consultar
        catch(Exception e){
            percentageCacheRate = percentageRateCachePort.getPercentageRate();
            total = totalSumUseCase.get(firstValue, secondValue,percentageCacheRate);
        }
        return TotalSumMapper.toResponse(total);

    }
}
