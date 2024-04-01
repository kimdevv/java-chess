package chess.domain;

import java.util.Map;

public class ChessGameResult {
    private final Map<Color, Double> result;

    private ChessGameResult(Map<Color, Double> result) {
        this.result = result;
    }

    public static ChessGameResult of(double whiteScore, double blackScore) {
        return new ChessGameResult(Map.of(Color.WHITE, whiteScore, Color.BLACK, blackScore));
    }

    public Map<Color, Double> getResult() {
        return result;
    }

    public Color getWinner() {
        double whiteScore = result.get(Color.WHITE);
        double blackScore = result.get(Color.BLACK);

        if (whiteScore == blackScore) {
            return Color.NONE;
        }
        if (whiteScore > blackScore) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
