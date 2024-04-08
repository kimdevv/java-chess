package chess.domain.game.status;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class WhiteTurn extends Running {
    public WhiteTurn(final Board board) {
        super(board);
    }

    @Override
    public GameStatus move(final Position source, final Position target) {
        checkTurn(source);
        if (board().tryMove(source, target)) {
            return checkKingKilled();
        }

        throw new IllegalArgumentException("해당 위치로 이동이 불가능합니다.");
    }

    private void checkTurn(final Position source) {
        Piece sourcePiece = board().get(source);
        if (sourcePiece.isSameTeamColor(Color.BLACK)) {
            throw new IllegalArgumentException("화이트팀 차례입니다. 이동이 불가능합니다.");
        }
    }

    private GameStatus checkKingKilled() {
        if (board().isKingKilled()) {
            return new Finished();
        }
        return new BlackTurn(board());
    }

    @Override
    public Color getTurn() {
        return Color.WHITE;
    }

    @Override
    public Board getBoard() {
        return board();
    }
}
