package chess.domain.chesspiece;

public record Score(double value) {

    public Score sum(Score score) {
        return new Score(value + score.value);
    }

    public double getScore() {
        return value;
    }
}
