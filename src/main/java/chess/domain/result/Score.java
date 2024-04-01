package chess.domain.result;

import java.util.Objects;

public class Score {
    
    private final double value;

    public Score(final double value) {
        validatePositive(value);
        this.value = value;
    }

    private void validatePositive(final double value) {
        if (value < 0) {
            throw new IllegalArgumentException("음수로 점수를 초기화 할 수 없습니다.");
        }
    }

    public Score add(final Score score) {
        return new Score(value + score.value);
    }

    public Score subtract(final Score score) {
        return new Score(value - score.value);
    }

    public Score multiply(final double ratio) {
        return new Score(value * ratio);
    }

    public Boolean isGraterThan(final Score score) {
        return value > score.value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return Double.compare(value, score.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

