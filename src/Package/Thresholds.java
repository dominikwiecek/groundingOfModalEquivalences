package Package;

/**
 * @author Dominik
 */


public class Thresholds {
    Double MinPos;
    Double MaxPos;
    Double MinBel;
    Double MaxBel;
    Double MinKnow;
    Double epsilon;

    public Thresholds(Double MinPos, Double MaxPos, Double MinBel, Double MaxBel, Double MinKnow, Double epsilon) {
        this.MinPos = (double) Math.round(MinPos * 100) / 100;
        this.MaxPos = (double) Math.round(MaxPos * 100) / 100;
        this.MinBel = (double) Math.round(MinBel * 100) / 100;
        this.MaxBel = (double) Math.round(MaxBel * 100) / 100;
        this.MinKnow = (double) Math.round(MinKnow * 100) / 100;
        this.epsilon = (double) Math.round(epsilon * 100) / 100;
    }

    Boolean getPos(double lambda) {
        return true;
//        return this.MinPos <= lambda && lambda <= this.MaxPos;
    }

    Boolean getBel(double lambda) {
        return this.MinBel <= lambda && lambda <= this.MaxBel;
    }

    Boolean getKnow(double lambda) {
        return this.MinKnow <= lambda;
    }


}
