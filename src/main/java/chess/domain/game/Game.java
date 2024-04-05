package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardStatus;
import chess.domain.piece.Side;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public class Game {

    private final Board board;
    private BoardStatus boardStatus;

    public Game(GameCreator gameCreator) {
        this.board = Board.from(gameCreator);
        this.boardStatus = gameCreator.createBoardStatus();
    }

    public void proceedTurn(Position source, Position target) {
        checkTurn(source);
        boardStatus = board.move(source, target);
    }

    private void checkTurn(Position source) {
        Piece piece = board.findPiece(source);
        if (boardStatus.isWhiteTurn() && piece.isWhite() || boardStatus.isBlackTurn() && piece.isBlack()) {
            return;
        }
        throw new IllegalArgumentException("해당 진영의 차례가 아닙니다.");
    }

    public boolean isNotKingDead() {
        return !boardStatus.isKingDead();
    }

    public Score calculateScore() {
        double blackScore = calculateSideScore(Side.BLACK);
        double whiteScore = calculateSideScore(Side.WHITE);
        return new Score(blackScore, whiteScore);
    }

    private double calculateSideScore(Side side) {
        return Arrays.stream(File.values())
                .mapToDouble(file -> board.calculateSameSidePiecesScoreInFile(side, file))
                .sum();
    }

    public List<Piece> board() {
        return board.pieces();
    }

    public BoardStatus boardStatus() {
        return boardStatus;
    }
}
