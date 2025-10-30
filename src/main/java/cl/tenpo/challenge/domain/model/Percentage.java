package cl.tenpo.challenge.domain.model;

import java.util.Objects;

public class Percentage {

    int percetage;

    public Percentage(int percentage){
        this.percetage = percentage;
    }

    public int getPercetage(){
        return percetage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Percentage that = (Percentage) o;
        return percetage == that.percetage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(percetage);
    }

}
