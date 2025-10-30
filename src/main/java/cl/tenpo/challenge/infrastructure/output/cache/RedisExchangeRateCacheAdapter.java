package cl.tenpo.challenge.infrastructure.output.cache;

import cl.tenpo.challenge.application.exception.PercentageNotFoundException;
import cl.tenpo.challenge.domain.model.Percentage;
import cl.tenpo.challenge.domain.ports.output.PercentageRateCachePort;
import cl.tenpo.challenge.infrastructure.output.entities.PercentageEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class RedisExchangeRateCacheAdapter implements PercentageRateCachePort {

    private final RedisTemplate<String, PercentageEntity> redisTemplate;

    private static final long TTL_MINUTES = 30;

    @Value("${cl.tenpo.challenge.cache.key-name}")
    public String key;

    public RedisExchangeRateCacheAdapter(RedisTemplate<String, PercentageEntity> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public Optional<PercentageEntity> getCachedRate() {
        PercentageEntity cached = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(cached);
    }

    public void cacheRate(PercentageEntity rate) {

        Optional<PercentageEntity>  previousPercentage = getCachedRate();
        if(previousPercentage.isEmpty()){
            redisTemplate.opsForValue().set(key, rate,TTL_MINUTES, TimeUnit.MINUTES);
        }

        else if(!previousPercentage.isEmpty() && previousPercentage.get().getPercent() != rate.getPercent()){
            evictRate();
            redisTemplate.opsForValue().set(key, rate,TTL_MINUTES, TimeUnit.MINUTES);
        }
    }

    public void evictRate() {
        redisTemplate.delete(key);
    }

    @Override
    public Percentage getPercentageRate() {

            Optional<PercentageEntity> cached = getCachedRate();
             if(cached.isEmpty()){
                  throw new PercentageNotFoundException();
             }
             double percent = cached.get().getPercent();
             return new Percentage((int) percent);
    }

    @Override
    public void cachePercentageRate(Percentage percentageRate) {

            PercentageEntity percentageEntity = new PercentageEntity((double) percentageRate.getPercetage());
            cacheRate(percentageEntity);
    }


}
