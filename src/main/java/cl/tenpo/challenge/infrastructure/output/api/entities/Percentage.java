package cl.tenpo.challenge.infrastructure.output.api.entities;

public class Percentage {

    Double percent;

    public Percentage(Double range ){
        double random = Math.random();
        double total = random * range + 1;

        this.percent = total;
    }


    public double getPercent() {
        return this.percent;
    }

}
