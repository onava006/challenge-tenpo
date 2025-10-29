package cl.tenpo.challenge.domain.ports.output;

public interface PercentageRateCachePort {

   public int getPercentageRate();


   //recibe parametro
    //valida si existe
    //valida tiempo de validez de version anterior
    //si es mayor al ttl o no hay se guarda
   public void cachePercentageRate(int percentageRate);
}
