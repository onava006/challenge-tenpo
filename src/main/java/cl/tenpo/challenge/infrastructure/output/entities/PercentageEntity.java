package cl.tenpo.challenge.infrastructure.output.entities;

public class PercentageEntity {

    Double percent;

    public PercentageEntity(){}

    public PercentageEntity(Double percent ){
        this.percent = percent;
    }

    public double getPercent() {
        return this.percent;
    }

    public void setPercent(double percent){
        this.percent  = percent;
    }
}
