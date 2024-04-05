package chess.view.dto;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Position;

public class ChessboardDto {

    private static final String EMPTY = ".";

    private final StringBuilder chessboard;

    public ChessboardDto(final Chessboard chessboard) {
        StringBuilder squares = new StringBuilder();
        for (final Rank rank : Rank.values()) {
            appendPieceCharacters(chessboard, rank, squares);
        }
        this.chessboard = squares;
    }

    private void appendPieceCharacters(
            final Chessboard chessboard,
            final Rank rank,
            final StringBuilder squares
    ) {
        for (final File file : File.values()) {
            Position position = Position.of(file, rank);
            appendPieceCharacter(chessboard, squares, position);
        }
        squares.append('\n');
    }

    private void appendPieceCharacter(
            final Chessboard chessboard,
            final StringBuilder squares,
            final Position position
    ) {
        if (chessboard.isEmpty(position)) {
            squares.append(EMPTY);
            return;
        }
        Piece piece = chessboard.get(position);
        squares.append(PieceCharacterMapper.toCharacter(piece));
    }

    public String getChessboard() {
        return chessboard.toString();
    }
}
