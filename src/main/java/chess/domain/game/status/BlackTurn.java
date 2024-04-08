package chess.domain.game.status;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class BlackTurn extends Running {
    public BlackTurn(final Board board) {
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
        if (sourcePiece.isSameTeamColor(Color.WHITE)) {
            throw new IllegalArgumentException("검정팀 차례입니다. 이동이 불가능합니다.");
        }
    }

    private GameStatus checkKingKilled() {
        if (board().isKingKilled()) {
            return new Finished();
        }
        return new WhiteTurn(board());
    }

    @Override
    public Color getTurn() {
        return Color.BLACK;
    }

    @Override
    public Board getBoard() {
        return board();
    }
}
