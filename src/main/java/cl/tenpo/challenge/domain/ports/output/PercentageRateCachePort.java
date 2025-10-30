package cl.tenpo.challenge.domain.ports.output;

import cl.tenpo.challenge.domain.model.Percentage;

public interface PercentageRateCachePort {

   public Percentage getPercentageRate();

   //recibe parametro
    //valida si existe
    //valida tiempo de validez de version anterior
    //si es mayor al ttl o no hay se guarda
   void cachePercentageRate(Percentage percentageRate);
}
