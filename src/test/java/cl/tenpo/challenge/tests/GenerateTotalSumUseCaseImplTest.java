package cl.tenpo.challenge.tests;

import cl.tenpo.challenge.application.adapters.input.GenerateTotalSumUseCaseImpl;
import cl.tenpo.challenge.application.dto.response.TotalSumDto;
import cl.tenpo.challenge.domain.model.Percentage;
import cl.tenpo.challenge.domain.model.TotalSum;
import cl.tenpo.challenge.domain.ports.input.CalculateTotalSumUseCase;
import cl.tenpo.challenge.domain.ports.output.PercentageRateCachePort;
import cl.tenpo.challenge.domain.ports.output.PercentageRatePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenerateTotalSumUseCaseImplTest {

    @Mock
    private PercentageRateCachePort percentageRateCachePort;

    @Mock
    private PercentageRatePort percentageRatePort;

    @Mock
    private CalculateTotalSumUseCase totalSumUseCase;

    private GenerateTotalSumUseCaseImpl generateTotalSumUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        generateTotalSumUseCase = new GenerateTotalSumUseCaseImpl(
                percentageRateCachePort,
                percentageRatePort,
                totalSumUseCase
        );
    }

    @Test
    void shouldUsePercentageFromApiAndCacheIt() {
        // given
        int first = 10;
        int second = 20;
        int percentage = 5;
        TotalSum totalSum = new TotalSum(30, 1, 31);

        when(percentageRatePort.getPercentageRate()).thenReturn(percentage);
        when(totalSumUseCase.get(first, second, percentage)).thenReturn(totalSum);

        // when
        TotalSumDto result = generateTotalSumUseCase.get(first, second);

        // then
        verify(percentageRatePort).getPercentageRate();
        verify(percentageRateCachePort).cachePercentageRate(new Percentage(percentage));
        verify(totalSumUseCase).get(first, second, percentage);

        assertEquals(totalSum.sum(), result.sum());
        assertEquals(totalSum.percentage(), result.percentage());
        assertEquals(totalSum.total(), result.total());
    }

    @Test
    void shouldUseCacheWhenApiFails() {
        // given
        int first = 10;
        int second = 20;
        int cachePercentage = 8;
        TotalSum totalSum = new TotalSum(30, 2, 32);

        when(percentageRatePort.getPercentageRate()).thenThrow(new ResourceAccessException("API down"));
        when(percentageRateCachePort.getPercentageRate()).thenReturn(new Percentage(cachePercentage));
        when(totalSumUseCase.get(first, second, cachePercentage)).thenReturn(totalSum);

        // when
        TotalSumDto result = generateTotalSumUseCase.get(first, second);

        // then
        verify(percentageRatePort).getPercentageRate();
        verify(percentageRateCachePort).getPercentageRate();
        verify(totalSumUseCase).get(first, second, cachePercentage);

        assertEquals(totalSum.sum(), result.sum());
        assertEquals(totalSum.percentage(), result.percentage());
        assertEquals(totalSum.total(), result.total());
    }
}