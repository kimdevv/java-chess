package chess.dao;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;

import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.Bishop;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.chesspiece.slidingPiece.Queen;
import chess.domain.chesspiece.slidingPiece.Rook;
import java.util.Arrays;

public enum PieceMapper {
    WHITE_KING("KING", "WHITE", new King(WHITE)),
    BLACK_KING("KING", "BLACK", new King(BLACK)),
    WHITE_QUEEN("QUEEN", "WHITE", new Queen(WHITE)),
    BLACK_QUEEN("QUEEN", "BLACK", new Queen(BLACK)),
    WHITE_ROOK("ROOK", "WHITE", new Rook(WHITE)),
    BLACK_ROOK("ROOK", "BLACK", new Rook(BLACK)),
    WHITE_BISHOP("BISHOP", "WHITE", new Bishop(WHITE)),
    BLACK_BISHOP("BISHOP", "BLACK", new Bishop(BLACK)),
    WHITE_KNIGHT("KNIGHT", "WHITE", new Knight(WHITE)),
    BLACK_KNIGHT("KNIGHT", "BLACK", new Knight(BLACK)),
    WHITE_PAWN("PAWN", "WHITE", new WhitePawn()),
    BLACK_PAWN("PAWN", "BLACK", new BlackPawn());

    private final String type;
    private final String team;
    private final Piece piece;

    PieceMapper(String type, String team, Piece piece) {
        this.type = type;
        this.team = team;
        this.piece = piece;
    }

    public static PieceMapper from(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceMapper -> pieceMapper.piece.equals(piece))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("해당 기물이 DB에 존재하지 않습니다."));
    }

    public static Piece mapToPiece(String type, String team) {
        return Arrays.stream(values())
                .filter(pieceMapper -> pieceMapper.type.equals(type))
                .filter(pieceMapper -> pieceMapper.team.equals(team))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("해당 기물이 DB에 존재하지 않습니다."))
                .piece;
    }

    public String getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }

}
