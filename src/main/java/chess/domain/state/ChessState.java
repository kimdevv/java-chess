package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.score.Score;
import chess.domain.score.ScoreManager;
import java.util.Map;
import java.util.Set;

public abstract class ChessState {
    protected Board board;


    protected ChessState(Board board) {
        this.board = board;
    }

    public abstract void move(Color turnColor, Positions positions);

    public final ChessState changeStrategy(Position from) {
        return board.getChessState(from);
    }

    protected final void checkTurnOf(Piece currentPiece, Color turnColor) {
        if (!currentPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("상대 말은 이동할 수 없습니다.");
        }
    }

    protected final boolean isNotAllBlankPath(Set<Position> path) {
        return !board.isAllBlank(path);
    }

    public final boolean isKingCaptured() {
        return !board.hasTwoKing();
    }

    public final Color announceCapturedKingColor() {
        return board.getRemainKingColor()
                .findOppositeColor();
    }

    public final Score calculateScore(Color color) {
        ScoreManager scoreManager = new ScoreManager(board);
        return scoreManager.calculateScore(color);
    }

    public final Map<Position, PieceType> collectBoard() {
        return board.collectBoard();
    }
}
