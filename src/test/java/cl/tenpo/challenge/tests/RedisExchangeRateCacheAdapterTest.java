package cl.tenpo.challenge.tests;

import cl.tenpo.challenge.application.exception.PercentageNotFoundException;
import cl.tenpo.challenge.domain.model.Percentage;
import cl.tenpo.challenge.infrastructure.output.cache.RedisExchangeRateCacheAdapter;
import cl.tenpo.challenge.infrastructure.output.entities.PercentageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RedisExchangeRateCacheAdapterTest {

    @Mock
    private RedisTemplate<String, PercentageEntity> redisTemplate;

    @Mock
    private ValueOperations<String, PercentageEntity> valueOperations;

    private RedisExchangeRateCacheAdapter cacheAdapter;

    private final String TEST_KEY = "test-key";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cacheAdapter = new RedisExchangeRateCacheAdapter(redisTemplate);
        cacheAdapter.key = TEST_KEY;
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void shouldReturnEmptyOptionalWhenCacheIsEmpty() {
        when(valueOperations.get(TEST_KEY)).thenReturn(null);

        Optional<PercentageEntity> result = cacheAdapter.getCachedRate();

        assertTrue(result.isEmpty());
        verify(valueOperations).get(TEST_KEY);
    }

    @Test
    void shouldReturnCachedValueWhenExists() {
        PercentageEntity entity = new PercentageEntity(15.0);
        when(valueOperations.get(TEST_KEY)).thenReturn(entity);

        Optional<PercentageEntity> result = cacheAdapter.getCachedRate();

        assertTrue(result.isPresent());
        assertEquals(15.0, result.get().getPercent());
    }

    @Test
    void shouldCacheRateWhenEmpty() {
        when(valueOperations.get(TEST_KEY)).thenReturn(null);

        PercentageEntity newRate = new PercentageEntity(10.0);
        cacheAdapter.cacheRate(newRate);

        verify(valueOperations).set(TEST_KEY, newRate, 30L, TimeUnit.MINUTES);
    }

    @Test
    void shouldEvictAndCacheNewRateWhenDifferent() {
        PercentageEntity existing = new PercentageEntity(10.0);
        when(valueOperations.get(TEST_KEY)).thenReturn(existing);

        PercentageEntity newRate = new PercentageEntity(20.0);
        cacheAdapter.cacheRate(newRate);

        verify(redisTemplate).delete(TEST_KEY);
        verify(valueOperations).set(TEST_KEY, newRate, 30L, TimeUnit.MINUTES);
    }

    @Test
    void shouldNotCacheWhenRateIsSame() {
        PercentageEntity existing = new PercentageEntity(15.0);
        when(valueOperations.get(TEST_KEY)).thenReturn(existing);

        PercentageEntity same = new PercentageEntity(15.0);
        cacheAdapter.cacheRate(same);

        verify(redisTemplate, never()).delete(TEST_KEY);
        verify(valueOperations, never()).set(any(), any(), anyLong(), any());
    }

    @Test
    void shouldThrowExceptionWhenNoCachedPercentage() {
        when(valueOperations.get(TEST_KEY)).thenReturn(null);
        assertThrows(PercentageNotFoundException.class, () -> cacheAdapter.getPercentageRate());
    }

    @Test
    void shouldReturnPercentageRateFromCache() {
        PercentageEntity cached = new PercentageEntity(25.0);
        when(valueOperations.get(TEST_KEY)).thenReturn(cached);

        Percentage result = cacheAdapter.getPercentageRate();

        assertEquals(25, result.getPercetage());
    }

    @Test
    void shouldCachePercentageRate() {
        Percentage percentage = new Percentage(30);

        when(valueOperations.get(TEST_KEY)).thenReturn(null);

        cacheAdapter.cachePercentageRate(percentage);

        verify(valueOperations).set(eq(TEST_KEY), any(PercentageEntity.class), eq(30L), eq(TimeUnit.MINUTES));
    }
}