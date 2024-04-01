package chess.domain.attribute;

import java.util.Objects;

public class Score {

    private final double value;

    public Score(double value) {
        this.value = value;
    }

    public Score add(double score) {
        return new Score(this.value + score);
    }

    public Score add(Score score) {
        return new Score(this.value + score.value);
    }

    public double getValue() {
        return value;
    }

    public boolean isLowerThan(Score score) {
        return this.value < score.value;
    }

    public boolean isHigherThan(Score score) {
        return this.value > score.value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Score score = (Score) object;
        return Double.compare(getValue(), score.getValue()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
